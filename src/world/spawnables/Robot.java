package world.spawnables;

import world.spawnables.BaseSpawnable;
import java.lang.Math;


/**
 * Robot
 */
public class Robot extends BaseSpawnable{

    public Robot(float spawn_x, float spawn_y, float spawn_theta){
        super(spawn_x, spawn_y, spawn_theta);
    }

    protected boolean _move(float linar_speed, float angular_speed){
        // TODO: Implement noisy commands
        float deltaT = 0.1f;
        this.x +=  (linar_speed * deltaT) * Math.cos(this.theta);
        this.y +=  (linar_speed * deltaT) * Math.sin(this.theta);
        this.theta += angular_speed * deltaT;
        return true;
    }

}

