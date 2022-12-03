package world.interfaces;

import world.spawnables.BaseSpawnable;


/**
 * Observer interface
 */
public interface Observer {

    public void update(BaseSpawnable observable);

}
