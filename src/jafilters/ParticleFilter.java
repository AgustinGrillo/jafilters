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

    public ParticleFilter(ArrayList<BaseSpawnable> initial_robots, ArrayList<BaseSpawnable> initial_radars,
            ArrayList<BaseSpawnable> initial_particles) {

        // TODO: Create copy of arrays
        this.robots = initial_robots;
        this.radars = initial_radars;
        this.particles = initial_particles;

        int num_particles = this.particles.size();

        particles_weight = new double[this.particles.size()];
        Arrays.fill(particles_weight, 1 / (double) num_particles);

    }

    public void update(float commanded_linear_speed, float commanded_angular_speed) {
        // Importance Sampling over each particle

        DiscreteProbabilityCollectionSampler particle_sampler = new DiscreteProbabilityCollectionSampler(rng,
                this.particles, particles_weight);

        for (int idx = 0; idx < this.particles.size(); idx++) {
            // Propagate particle
            BaseSpawnable particle = this.particles.get(idx);
            BaseSpawnable reference_particle = (BaseSpawnable) particle_sampler.sample();
            // Get current particle state
            float current_particle_x = reference_particle.getXYPosition()[0];
            float current_particle_y = reference_particle.getXYPosition()[1];
            float current_particle_theta = reference_particle.getOrientation();
            // Sample next particle state from normal distribution
            float delta_t = 0.1f;
            float next_particle_x = (float) this.random
                    .nextGaussian(
                            current_particle_x + commanded_linear_speed * delta_t * Math.cos(current_particle_theta),
                            0.0f);
            float next_particle_y = (float) this.random
                    .nextGaussian(
                            current_particle_y + commanded_linear_speed * delta_t * Math.sin(current_particle_theta),
                            0.0f);
            float next_particle_theta = (float) this.random
                    .nextGaussian(
                            current_particle_theta + commanded_angular_speed * delta_t,
                            0.0f);
            // Update particle state
            particle.move(next_particle_x, next_particle_y, next_particle_theta);
            particle.constrainMovement(0, 10, 0, 6);

            // Update weight
        }

    }
}
