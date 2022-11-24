package world.spawnables;

import world.spawnables.Base;
import java.lang.Math;


/**
 * Robot
 */
public class Robot extends Base{

    Robot(float spawn_x, float spawn_y, int id, String symbol){
        super(spawn_x, spawn_y, id, symbol);
    }

    protected bool _move(float speed, float angle){
        this.x +=  speed * Math.cos(angle);
        this.y +=  speed * Math.sin(angle);
        return true;
    }

}

