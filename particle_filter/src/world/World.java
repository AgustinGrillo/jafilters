package world;

import world.interfaces.Observer;
import world.interfaces.Observable;
import world.spawnables.*;
import world.util.Color;
import java.util.ArrayList;
import java.lang.Math;


/**
 * World
 */
public class World implements Observer{
    // TODO: Observer
    // - Create array to store observables (items)
    // - Implement update method

    private float width = 6;        // Coordinate
    private float height = 2;       // Coordinate
    private int render_rows = 20;   // Cell
    private int render_cols = 60;   // Cell
    private ArrayList<BaseSpawnable> items = new ArrayList<BaseSpawnable>();
    BaseSpawnable[][] empty_world = new BaseSpawnable[render_rows][render_cols];
    BaseSpawnable[][] world;


    public World(ArrayList<BaseSpawnable> initial_items){
        // Create empty world
        createEmptyWorld();
        world = empty_world;

        for (BaseSpawnable item : initial_items) {
            items.add(item);
            item.registerObserver(this);
            int[] item_cell = coord2Cell(item.getXYPosition());
            world[item_cell[0]][item_cell[1]] = item;
        }
        this.plot();
    }

    public void update(Observable o){
        // o.getXYPosition();
    }

    private void createEmptyWorld(){
        // Overkill??
        float[] floor_coord;
        int floor_id = 0;
        String floor_symbol = Color.ANSI_GREY + "·" + Color.ANSI_RESET;

        for (int i = 0; i < empty_world.length; i++) {
            for (int j = 0; j < empty_world[i].length; j++) {
                floor_coord = cell2Coord(new int[]{i, j});
                empty_world[i][j] = new Floor(floor_coord[0], floor_coord[1], floor_id, floor_symbol);
            }
        }
    }

    private float[] cell2Coord(int[] cell){
        int cell_row = cell[0];
        int cell_col = cell[1];

        float coord_x = cell_col * width / render_cols; 
        float coord_y = cell_row * height / render_rows; 

        return new float[]{coord_x, coord_y};
    }

    private int[] coord2Cell(float[] coord){
        float coord_x = coord[0];
        float coord_y = coord[1];

        int cell_col = Math.round(coord_x * render_cols / width); 
        int cell_row = Math.round(coord_y * render_rows / height); 

        return new int[]{cell_row, cell_col};

    }

    public void plot(){
        String formatted_string = "";
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                formatted_string += world[i][j].toString();
            }
            formatted_string += "\n";
        }
        formatted_string += "\n";

        System.out.print(formatted_string);
    }
    
}
