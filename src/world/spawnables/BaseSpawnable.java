package world.spawnables;

import world.interfaces.Observable;
import world.interfaces.Spawnable;
import world.interfaces.Observer;
import java.util.ArrayList;

/**
 * Base Abstract Class: BaseSpawnable
 */
abstract public class BaseSpawnable implements Observable, Spawnable {
    // Attributes
    // Spawnable related attributes
    protected float x;
    protected float y;
    protected float theta;
    protected float previous_x;
    protected float previous_y;
    protected float delta_t = 0.1f;
    private int id;
    // Observable related attributes
    private ArrayList<Observer> observers;

    // Methods
    // Spawnable related methods
    public BaseSpawnable(float spawn_x, float spawn_y, float spawn_theta, int id) {
        this.x = spawn_x;
        this.y = spawn_y;
        this.theta = spawn_theta;
        this.previous_x = spawn_x;
        this.previous_y = spawn_y;
        this.id = id;

        observers = new ArrayList<Observer>();
    }

    public BaseSpawnable(float spawn_x, float spawn_y, float spawn_theta) {
        this(spawn_x, spawn_y, spawn_theta, 0);
    }

    public BaseSpawnable(float spawn_x, float spawn_y, int id) {
        this(spawn_x, spawn_y, 0.0f, id);
    }

    public BaseSpawnable(float spawn_x, float spawn_y) {
        this(spawn_x, spawn_y, 0.0f, 0);
    }

    abstract protected boolean _move(float speed, float angle);

    // TODO: Oberload method to accept different arguments
    public void move(float speed, float angle) {
        this.previous_x = this.x;
        this.previous_y = this.y;
        if (_move(speed, angle)) {
            notifyObservers();
        }
    }

    // TODO: BaseSpawnable should be sub-classed
    public float[] senseObject(BaseSpawnable object) {
        return new float[] { 0, 0 };
    }

    public void setTimeStep(float time_step) {
        this.delta_t = time_step;
    }

    public float getTimeStep() {
        return this.delta_t;
    }

    public float[] getXYPosition() {
        float[] pos = { this.x, this.y };
        return pos;
    }

    public float getOrientation() {
        return this.theta;
    }

    public float[] getPreviousXYPosition() {
        float[] pos = { this.previous_x, this.previous_y };
        return pos;
    }

    public void constrainMovement(float x_low, float x_high, float y_low, float y_high) {
        // Collision -> Constrain movement to rectangle (wip)
        // TODO: Extend this to multiple geometries.

        this.x = Math.max(x_low, Math.min(x_high, this.x));
        this.y = Math.max(y_low, Math.min(y_high, this.y));
    }

    public int getID() {
        return this.id;
    }

    // Observable related methods
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observer o) {
        int idx = this.observers.indexOf(o);
        if (idx >= 0) {
            this.observers.remove(idx);
        }
    }

    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(this);
        }
    }

}
