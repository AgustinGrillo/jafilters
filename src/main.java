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

        robots.add(new Robot(random.nextFloat(10), random.nextFloat(6), random.nextFloat(6.3f)));
        for (int i = 0; i < num_radars; i++) {
            radars.add(new Radar(random.nextFloat(10), random.nextFloat(6)));
        }
        for (int i = 0; i < num_particles; i++) {
            particles.add(new Particle(random.nextFloat(10), random.nextFloat(6)));
        }

        // Create world
        World world = new World(robots, radars, particles);
        world.setSimTimeStep(deltaT);

        // // Initialize filter
        // ParticleFilter filter = new ParticleFilter();


        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep((int) (1000 * deltaT));
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            robots.get(0).move(0.5f, random.nextFloat(-1.0f, 1.0f));
        }

    }
}
