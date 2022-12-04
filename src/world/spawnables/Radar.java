package world.spawnables;

import world.spawnables.BaseSpawnable;

/**
 * Radar
 */
public class Radar extends BaseSpawnable {

    public Radar(float spawn_x, float spawn_y, int id, String symbol) {
        super(spawn_x, spawn_y, id, symbol);
    }

    protected boolean _move(float speed, float angle) {
        System.out.println("Radar is not movable!");
        return false;
    }

    // TODO:
    // Ideally subclass (or better compose (?)) this class to implement different
    // noisy estimates.
    public float[] senseObject(BaseSpawnable object) {
        float[] object_pos = object.getXYPosition();
        float object_x = object_pos[0];
        float object_y = object_pos[1];
        float distance = (float) Math
                .sqrt((float) Math.pow((object_x - this.x), 2.0f) + (float) Math.pow((object_y - this.y), 2.0f));
        float angle = (float) Math.atan2((float) (object_y - this.y), (float) (object_x - this.x));
        return new float[] { distance, angle };
    }

}
