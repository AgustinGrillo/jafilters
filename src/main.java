import world.World;
import java.util.ArrayList;
import java.util.Arrays;
import world.spawnables.*;
import world.util.Color;
import java.util.Random;


public class main {

    public static void main(String[] args) {
        Random random = new Random();

        // Create items to spawn in world
        BaseSpawnable robot = new Robot(random.nextFloat(6), random.nextFloat(2), 1,
                                        Color.ANSI_GREEN + "•" + Color.ANSI_RESET);
        BaseSpawnable radar1 = new Radar(random.nextFloat(6), random.nextFloat(2), 2,
                                        Color.ANSI_YELLOW + "□" + Color.ANSI_RESET);
        BaseSpawnable radar2 = new Radar(random.nextFloat(6), random.nextFloat(2), 3,
                                        Color.ANSI_YELLOW + "□" + Color.ANSI_RESET);
        ArrayList<BaseSpawnable> robots = new ArrayList<BaseSpawnable>(Arrays.asList(robot));
        ArrayList<BaseSpawnable> radars = new ArrayList<BaseSpawnable>(Arrays.asList(radar1, radar2));

        // Create world
        World world = new World(robots, radars);

        for (int i = 0; i < 100; i++) {
            try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}    
            robot.move(0.5f, random.nextFloat(-1.0f, 1.0f));
        }

    }
}
