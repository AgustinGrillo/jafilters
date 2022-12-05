package world;

import world.interfaces.Observer;
import world.interfaces.Observable;
import world.spawnables.*;
import world.util.graphics.WorldGraphics;
import world.util.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

/**
 * World
 * 
 * Coordinates:
 * y Origin (○) at lower left corner.
 * ↑
 * ○ → x
 *
 */
public class World implements Observer {

    private float width = 10; // Coordinate
    private float height = 6; // Coordinate
    private float time_step = 0.1f; // Sim time step
    private ArrayList<BaseSpawnable> robots = new ArrayList<BaseSpawnable>();
    private ArrayList<BaseSpawnable> radars = new ArrayList<BaseSpawnable>();
    private ArrayList<BaseSpawnable> estimations = new ArrayList<BaseSpawnable>();
    WorldGraphics world_renderer;

    public World(ArrayList<BaseSpawnable> initial_robots, ArrayList<BaseSpawnable> initial_radars) {

        for (BaseSpawnable robot : initial_robots) {
            robots.add(robot);
            robot.registerObserver(this);
        }
        for (BaseSpawnable radar : initial_radars) {
            radars.add(radar);
            radar.registerObserver(this);
        }

        this.setSimTimeStep(this.time_step);
        world_renderer = new WorldGraphics(width, height, robots, radars);
    }

    public void setSimTimeStep(float time_step) {
        this.time_step = time_step;

        for (BaseSpawnable robot : this.robots) {
            robot.setTimeStep(this.time_step);
        }
        for (BaseSpawnable radar : this.radars) {
            radar.setTimeStep(this.time_step);
        }
    }

    public void update(BaseSpawnable observable) {
        // Check collision -> constrain item
        observable.constrainMovement(0, width, 0, height);
        // Update item position in world
        this.updateItemRendering(observable);
    }

    private void updateItemRendering(BaseSpawnable item) {
        world_renderer.plotWorld();
    }

}
