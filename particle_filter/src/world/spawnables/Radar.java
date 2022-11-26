package world.spawnables;

import world.spawnables.BaseSpawnable;


/**
 * Radar
 */
public class Radar extends BaseSpawnable{

    Radar(float spawn_x, float spawn_y, int id, String symbol){
        super(spawn_x, spawn_y, id, symbol);
    }

    protected boolean _move(float speed, float angle){
        System.out.println("Radar is not movable!");
        return false;
    }

}

