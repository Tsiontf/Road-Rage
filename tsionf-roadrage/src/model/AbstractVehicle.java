package model;

/**
This is an abstract class representing a vehicle in a grid.
It implements the {@link Vehicle} interface.
@author Tsion Fufa
@version 2023 winter!
*/

import java.util.Map;
import java.util.Random;

public abstract class AbstractVehicle implements Vehicle{

    private int origX;
    private int origY;
    private int X;
    private int Y;
    private Direction direction;
    protected int deathTime;
    protected static Random random = new Random();
    
    /**
     * Constructs an AbstractVehicle with the given X, Y and direction.
     * 
     * @param X the X position of the vehicle
     * @param Y the Y position of the vehicle
     * @param direction the direction of the vehicle
     */

    public AbstractVehicle(int X, int Y, Direction direction) {
        this.X = X;
        this.Y = Y;
        this.direction = direction;
        this.deathTime = -1;
        origX = X;
        origY = Y;
    }
    
    /**
     * Returns true if this vehicle can pass the given terrain and light, false otherwise.
     * 
     * @param theTerrain the terrain to pass
     * @param theLight the light state
     * 
     * @return true if this vehicle can pass the terrain and light, false otherwise
     */
    @Override
    public abstract boolean canPass(Terrain theTerrain, Light theLight);

    /**
     * Chooses the direction for this vehicle based on the given neighbors.
     * 
     * @param theNeighbors the neighbors in all directions
     * 
     * @return the chosen direction for this vehicle
     */

    @Override
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);

    /**
     * Makes this vehicle collide with the given vehicle.
     * 
     * @param theOther the other vehicle involved in the collision
     */
    @Override
    public abstract void collide(Vehicle theOther);

    /**
     * Returns the time of death for this vehicle.
     * 
     * @return the time of death for this vehicle
     */
    @Override
    public abstract int getDeathTime();

    /**
     * Returns the image file name for this vehicle.
     * 
     * @return the image file name for this vehicle
     */ 
    @Override
    public abstract String getImageFileName();

    /**
     * Gets the current direction of the vehicle.
     *
     * @return The current direction of the vehicle.
     */
    @Override
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gets the current X coordinate of the vehicle.
     *
     * @return The current X coordinate of the vehicle.
     */
    @Override
    public int getX() {
        return X;
    }

    /**
     * Gets the current Y coordinate of the vehicle.
     *
     * @return The current Y coordinate of the vehicle.
     */
    @Override
    public int getY() {
        return Y;
    }

    /**
     * Determines if the vehicle is alive.
     *
     * @return True if the vehicle is alive, false otherwise.
     */
    @Override
    public boolean isAlive() {
        return deathTime == -1;
    }

    /**
     * This method increases the `deathTime` of the vehicle by 1 if it is not dead.
     * If the `deathTime` is equal to or greater than `getDeathTime()`, then it resets the vehicle.
     */
    @Override
    public void poke() {
        if(deathTime >= 0 && deathTime < getDeathTime())
            deathTime++;
        if(deathTime >= getDeathTime())
            reset();
    }

    /**
     * This method resets the vehicle's position to its original position, sets its direction to a random value, and sets its `deathTime` to -1.
     */ 
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        this.X = origX;
        this.Y = origY;
        this.direction = direction.random();
        this.deathTime = -1;
    }

    /**
     * This method sets the direction of the vehicle.
     * @param theDir The new direction for the vehicle.
     */ 
    @Override
    public void setDirection(Direction theDir) {
        this.direction = theDir;
    }

    /**
     * This method sets the X-coordinate of the vehicle.
     * @param theX The new X-coordinate for the vehicle.
     */
    @Override
    public void setX(int theX) {
        this.X = theX;
    }

    /**
     * This method sets the Y-coordinate of the vehicle.
     * @param theY The new Y-coordinate for the vehicle.
     */ 
    @Override
    public void setY(int theY) {
        this.Y = theY;
    }
    
    /**
     * This method returns a string representation of the vehicle's position and direction in the format `"(X, Y) going direction"`.
     * @return The string representation of the vehicle's position and direction.
     */
    public String toString() {
        return "("+X+","+Y+") going "+direction;
    }
}