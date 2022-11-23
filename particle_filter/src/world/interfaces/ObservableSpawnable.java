package world.interfaces;

import world.interfaces.Observable;
import world.interfaces.Observer;


/**
 * Observable interface
 */
public interface ObservableSpawnable extends Observable {

    public float[] getXYPosition();
    public String getID();
    public String getSymbol();
}
