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
    protected float previous_x;
    protected float previous_y;
    private int id;
    private String symbol;
    // Observable related attributes
    private ArrayList<Observer> observers;

    // Methods
    // Spawnable related methods
    public BaseSpawnable(float spawn_x, float spawn_y, int id, String symbol){
        this.x = spawn_x;
        this.y = spawn_y;
        this.previous_x = spawn_x;
        this.previous_y = spawn_y;
        this.id = id;
        this.symbol = symbol;

        observers = new ArrayList<Observer> ();
    }

    abstract protected boolean _move(float speed, float angle);

    // TODO: Oberload method to accept different arguments
    public void move(float speed, float angle){
        this.previous_x = this.x;
        this.previous_y = this.y;
        if (_move(speed, angle)) {
            notifyObservers();
        }
    }
    
    public float[] getXYPosition(){
        float[] pos = {this.x, this.y};
        return pos;
    }

    public float[] getPreviousXYPosition(){
        float[] pos = {this.previous_x, this.previous_y};
        return pos;
    }

    public void constrainMovement(float x_low, float x_high, float y_low, float y_high){
        // Collision -> Constrain movement to rectangle (wip)
        // TODO: Extend this to multiple geometries.

        if (this.x < x_low) {
            this.x = (2 * x_low - this.x);
        } else if (this.x > x_high){
            this.x = (2 * x_high - this.x);
        }
        if (this.y < y_low) {
            this.y = (2 * y_low - this.y);
        } else if (this.y > y_high){
            this.y = (2 * y_high - this.y);
        }
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