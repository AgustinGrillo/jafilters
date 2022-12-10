package jafilters;

import world.spawnables.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.lang.Math;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.rng.sampling.*;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;

/**
 * Particle Filter
 */
public class ParticleFilter {

    private ArrayList<BaseSpawnable> robots = new ArrayList<BaseSpawnable>();
    private ArrayList<BaseSpawnable> radars = new ArrayList<BaseSpawnable>();
    private ArrayList<BaseSpawnable> particles = new ArrayList<BaseSpawnable>();
    private double[] particles_weight;

    // Random gen
    Random random = new Random();
    UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();

    // Epsilon value to avoid division by 0
    double eps = 1e-7;

    public ParticleFilter(ArrayList<BaseSpawnable> initial_robots, ArrayList<BaseSpawnable> initial_radars,
            ArrayList<BaseSpawnable> initial_particles) {

        // TODO: Create copy of arrays
        this.robots = initial_robots;
        this.radars = initial_radars;
        this.particles = initial_particles;

        int num_particles = this.particles.size();

        this.particles_weight = new double[this.particles.size()];
        Arrays.fill(this.particles_weight, 1 / (double) num_particles);

    }

    public void update(float commanded_linear_speed, float commanded_angular_speed) {
        // Importance Sampling over each particle

        //Deep Copy particles 
        ArrayList<BaseSpawnable> particles_clone = new ArrayList<BaseSpawnable>();
        for (BaseSpawnable particle : this.particles) {
            particles_clone.add(new Particle(particle.getXYPosition()[0], particle.getXYPosition()[1], particle.getOrientation()));
        }
        DiscreteProbabilityCollectionSampler particle_sampler = new DiscreteProbabilityCollectionSampler(rng,
               particles_clone, this.particles_weight.clone());

        for (int idx = 0; idx < this.particles.size(); idx++) {
            // Propagate particle
            BaseSpawnable particle = this.particles.get(idx);
            BaseSpawnable reference_particle = (BaseSpawnable) particle_sampler.sample();
            // Get current particle state
            float particle_current_x = reference_particle.getXYPosition()[0];
            float particle_current_y = reference_particle.getXYPosition()[1];
            float particle_current_theta = reference_particle.getOrientation();
            // Sample next particle state from normal distribution
            float delta_t = 0.1f;  // NOTE: Hardcoded
            float particle_next_x = (float) this.random
                    .nextGaussian(
                            particle_current_x + commanded_linear_speed * delta_t * Math.cos(particle_current_theta),
                            0.2f);
            float particle_next_y = (float) this.random
                    .nextGaussian(
                            particle_current_y + commanded_linear_speed * delta_t * Math.sin(particle_current_theta),
                            0.2f);
            float particle_next_theta = (float) this.random
                    .nextGaussian(
                            particle_current_theta + commanded_angular_speed * delta_t,
                            0.5f);
            // Update particle state
            particle.move(particle_next_x, particle_next_y, particle_next_theta);
            particle.constrainMovement(0, 10, 0, 6);

            // Update weight
            double weight_correction = 1.0;
            for (BaseSpawnable radar : this.radars) {
                // Get current measurement
                float[] measurement = radar.senseObject(this.robots.get(0));
                double measured_distance = (double) measurement[0];
                double measured_angle = (double) measurement[1];
                double[] measurement_mean = { measured_distance, measured_angle };
                double[][] sensor_cov = { { 0.2, 0.0 }, { 0.0, 0.2 } };
                MultivariateNormalDistribution sensor_dist = new MultivariateNormalDistribution(measurement_mean,
                        sensor_cov);
                // Calculate expected measurement based on predicted particle state
                float[] radar_pos = radar.getXYPosition();
                double expected_measured_distance = (double) Math
                        .sqrt((float) Math.pow((particle_next_x - radar_pos[0]), 2.0f)
                                + (float) Math.pow((particle_next_y - radar_pos[1]), 2.0f));
                double expected_measured_angle = (double) Math.atan2((float) (particle_next_y - radar_pos[1]),
                        (float) (particle_next_x - radar_pos[0]));
                double[] expected_measurement = { expected_measured_distance, expected_measured_angle };
                double measurement_probability = sensor_dist.density(expected_measurement);
                weight_correction *= measurement_probability;

            }
            weight_correction = Math.max(this.eps, weight_correction);
            this.particles.get(idx).size = 6; //30 * (float) weight_correction;
            this.particles_weight[idx] *= weight_correction;

        }
        // Normalize weights
        double weights_sum = Arrays.stream(this.particles_weight).sum();
        for (int index = 0; index < this.particles_weight.length; index++) {
            this.particles_weight[index] /= weights_sum;
        }

    }
}
