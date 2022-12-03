package world.spawnables;

import world.spawnables.BaseSpawnable;
import java.lang.Math;


/**
 * Robot
 */
public class Robot extends BaseSpawnable{

    public Robot(float spawn_x, float spawn_y, int id, String symbol){
        super(spawn_x, spawn_y, id, symbol);
    }

    protected boolean _move(float speed, float angle){
        this.x +=  (speed / 2) * Math.cos(angle);
        this.y +=  (speed / 2) * Math.sin(angle);
        return true;
    }

}

