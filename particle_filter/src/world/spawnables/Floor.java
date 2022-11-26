package world.spawnables;

import world.spawnables.BaseSpawnable;


/**
 * Floor
 */
public class Floor extends BaseSpawnable{

    Floor(float spawn_x, float spawn_y, int id, String symbol){
        super(spawn_x, spawn_y, id, symbol);
    }

    protected boolean _move(float speed, float angle){
        System.out.println("Floor is not movable!");
        return false;
    }

}

