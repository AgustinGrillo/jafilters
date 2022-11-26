package world.spawnables;

import world.interfaces.Observable;
import world.interfaces.Spawnable;
import world.interfaces.Observer;
import java.util.ArrayList;


/**
 * Base Abstract Class: BaseSpawnable
 */
abstract public class BaseSpawnable implements Observable, Spawnable{
    // Attributes
    // Spawnable related attributes
    protected float x;
    protected float y;
    private int id;
    private String symbol;
    // Observable related attributes
    private ArrayList<Observer> observers;

    // Methods
    // Spawnable related methods
    public BaseSpawnable(float spawn_x, float spawn_y, int id, String symbol){
        this.x = spawn_x;
        this.y = spawn_y;
        this.id = id;
        this.symbol = symbol;

        observers = new ArrayList<Observer> ();
    }

    abstract protected boolean _move(float speed, float angle);

    // TODO: Oberload method to accept different arguments
    public void move(float speed, float angle){
        if (_move(speed, angle)) {
            notifyObservers();
        }
    }
    
    public float[] getXYPosition(){
        float[] pos = {this.x, this.y};
        return pos;
    }

    public int getID(){
        return this.id;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public String toString() {
        return this.symbol;
    }
    
    // Observable related methods
    public void registerObserver(Observer o){
        this.observers.add(o);
    }
    public void removeObserver(Observer o){
        int idx = this.observers.indexOf(o);
        if (idx >= 0) {
            this.observers.remove(idx);
        }
    }
    public void notifyObservers(){
        for (Observer observer : this.observers) {
            observer.update(this);
        }
    }
    
}
