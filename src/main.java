import world.World;
import jafilters.ParticleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import world.spawnables.*;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        Random random = new Random();
        float deltaT = 0.1f; // Seconds
        int num_radars = 3;
        int num_particles = 20;

        // Create items to spawn in world
        ArrayList<BaseSpawnable> robots = new ArrayList<BaseSpawnable>();
        ArrayList<BaseSpawnable> radars = new ArrayList<BaseSpawnable>();
        ArrayList<BaseSpawnable> particles = new ArrayList<BaseSpawnable>();

        robots.add(new Robot(random.nextFloat(10), random.nextFloat(6), random.nextFloat(-3.14f, 3.14f)));
        for (int i = 0; i < num_radars; i++) {
            radars.add(new Radar(random.nextFloat(10), random.nextFloat(6)));
        }
        for (int i = 0; i < num_particles; i++) {
            particles.add(new Particle(random.nextFloat(10), random.nextFloat(6), random.nextFloat(-3.14f, 3.14f)));
        }

        // Create world
        World world = new World(robots, radars, particles);
        world.setSimTimeStep(deltaT);

        // Initialize filter
        ParticleFilter filter = new ParticleFilter(robots, radars, particles);


        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep((int) (1000 * deltaT));
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            float linear_vel = 0.5f;
            float anglular_vel = random.nextFloat(-1.0f, 1.0f);
            robots.get(0).move(linear_vel, anglular_vel);
            filter.update(linear_vel, anglular_vel);
        }

    }
}
