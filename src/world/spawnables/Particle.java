package world.spawnables;

import world.spawnables.BaseSpawnable;

/**
 * Particle
 */
public class Particle extends BaseSpawnable {

    public Particle(float spawn_x, float spawn_y, float spawn_theta) {
        super(spawn_x, spawn_y, spawn_theta);
    }

    protected boolean _move(float new_x, float new_y) {
        this.x = new_x;
        this.y = new_y;
        return true;
    }

}
