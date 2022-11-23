package world.interfaces;

import world.interfaces.Observer;


/**
 * Observable interface
 */
public interface Observable {

    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();

}
