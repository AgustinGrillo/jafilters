package world;

import world.interfaces.Observer;
import world.interfaces.Observable;
import world.spawnables.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * World
 */
public class World implements Observer{
    // TODO: Observer
    // - Create array to store observables (items)
    // - Implement update method

    private float width = 6;        // Coordinate
    private float height = 2;       // Coordinate
    private int render_rows = 60;   // Cell
    private int render_cols = 20;   // Cell
    public BaseSpawnable[] radars;
    public BaseSpawnable[] robots;
    BaseSpawnable[][] empty_world = new BaseSpawnable[render_rows][render_cols];
    BaseSpawnable[][] world;

    Random randomizer = new Random();

    // Terminal coloring
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_GREY = "\u001B[2m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";

    public World(){
        // robot = new Robot(randomizer.nextInt(width), randomizer.nextInt(height));
        //
        // int tow_x, tow_y;
        // do {
        //     tow_x = randomizer.nextInt(width);
        //     tow_y = randomizer.nextInt(height);
        // } while (tow_x == robot.x && tow_y == robot.y);
        // tower1 = new Tower(tow_x, tow_y);
        // do {
        //     tow_x = randomizer.nextInt(width);
        //     tow_y = randomizer.nextInt(height);
        // } while ((tow_x == robot.x && tow_y == robot.y) || (tow_x == tower1.x && tow_y == tower1.y));
        // tower2 = new Tower(tow_x, tow_y);
    }

    public void update(Observable o){
        // TODO:

    }

    private void createEmptyWorld(){
        // TODO: Add floor elements
        // Overkill??

    }

    private float[] cell2Coord(int[] cell){
        // TODO:
        return new float[]{0, 0};

    }

    private int[] coord2Cell(float[] coord){
        // TODO:
        return new int[]{0, 0};

    }

    public void plot(){
        // String c;
        // for (int i = 0; i < height; i++) {
        //     for (int j = 0; j < width; j++) {
        //         if (i == robot.y && j == robot.x) {
        //             c = ANSI_GREEN + "º" + ANSI_RESET;
        //         } else if ((i == tower1.y && j == tower1.x) || (i == tower2.y && j == tower2.x)) {
        //             c = ANSI_YELLOW + "×" + ANSI_RESET;
        //         } else {
        //             // c = "•";
        //             c = ANSI_GREY + "·" + ANSI_RESET;
        //         }
        //         System.out.print(c);
        //     }
        //     System.out.println("");
        // }
    }
    
}
