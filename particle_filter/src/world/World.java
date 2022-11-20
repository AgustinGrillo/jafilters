package world;

import world.assets.*;
import java.util.Random;


/**
 * World
 */
public class World {

    private int width = 20;
    private int height = 20;

    Random randomizer = new Random();
    Robot robot;
    Tower tower1, tower2;

    public World(){
        robot = new Robot(randomizer.nextInt(width), randomizer.nextInt(height));

        int tow_x, tow_y;
        do {
            tow_x = randomizer.nextInt(width);
            tow_y = randomizer.nextInt(height);
        } while (tow_x == robot.x && tow_y == robot.y);
        tower1 = new Tower(tow_x, tow_y);
        do {
            tow_x = randomizer.nextInt(width);
            tow_y = randomizer.nextInt(height);
        } while ((tow_x == robot.x && tow_y == robot.y) || (tow_x == tower1.x && tow_y == tower1.y));
        tower2 = new Tower(tow_x, tow_y);

    }

    public void plot(){
        String c;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == robot.y && j == robot.x) {
                    c = "<>";
                } else if ((i == tower1.y && j == tower1.x) || (i == tower2.y && j == tower2.x)) {
                    c = "[]";
                } else {
                    c = "░░";
                }
                System.out.print(c);
            }
            System.out.println("");
        }
    }
    
}
