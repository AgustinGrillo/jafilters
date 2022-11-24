package world.spawnables;

import world.spawnables.Base;


/**
 * Radar
 */
public class Radar extends Base{

    Radar(float spawn_x, float spawn_y, int id, String symbol){
        super(spawn_x, spawn_y, id, symbol);
    }

    protected bool _move(float speed, float angle){
        System.out.println("Radar is not movable!");
        return false;
    }

}

