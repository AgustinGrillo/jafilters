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
        BaseSpawnable radar = new Radar(random.nextFloat(6), random.nextFloat(2), 2,
                                        Color.ANSI_YELLOW + "□" + Color.ANSI_RESET);
        ArrayList<BaseSpawnable> items = new ArrayList<BaseSpawnable>(Arrays.asList(robot, radar));

        // Create world
        World world = new World(items);

    }
}
