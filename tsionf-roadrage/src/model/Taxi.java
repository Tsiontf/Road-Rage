package model;

import java.util.Map;

/**
 * The `Taxi` class is a type of `Vehicle` that extends the `AbstractVehicle` class.
 * It represents a taxi in a simulation and overrides various methods defined in the 
 * `AbstractVehicle` class. 
 * @author Tsion Fufa
 *@version 2023 winter!
 */
public class Taxi extends AbstractVehicle{
    
    /**
     * Represents the waiting time of the taxi. 
     */
    private int waitTime;
    
    /**
     * Creates a new instance of the `Taxi` class and sets its initial position and direction.
     * 
     * @param X The initial X-coordinate of the taxi.
     * @param Y The initial Y-coordinate of the taxi.
     * @param direction The initial direction of the taxi.
     */
    public Taxi(int X, int Y, Direction direction) {
        super(X,Y,direction);
        waitTime = 0;
    }
    
    /**
     * Determines whether the taxi can pass the given terrain and light conditions.
     * If the terrain is a light, the taxi can pass if the light is green, otherwise it will wait.
     * If the terrain is a crosswalk, the taxi will wait for 3 cycles if the light is red, and then pass.
     * If the terrain is a street, the taxi will pass immediately.
     * 
     * @param theTerrain The terrain type of the cell the taxi wants to move to.
     * @param theLight The light condition of the cell the taxi wants to move to.
     * @return A boolean indicating whether the taxi can pass the cell or not.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if(theTerrain == Terrain.LIGHT) {
            if(theLight == Light.RED)
                return false;
            waitTime = 0;
            return true;
        }
        else if(theTerrain == Terrain.CROSSWALK) {
            if(theLight == Light.RED && waitTime < 3) {
                waitTime++;
                return false;
            }
            waitTime = 0;
            return true;
        }
        if(theTerrain == Terrain.STREET) {
            waitTime = 0;
            return true;
        }
        return false;        
    }

    /**
     * Chooses the direction the taxi should move to, based on the neighbors of its current cell.
     * The taxi will first try to move in its current direction.
     * If that is not possible, it will try to move to the left, and then to the right.
     * If all of these options are not possible, it will reverse its direction.
     * 
     * @param theNeighbors A map representing the terrain type of the cells in all 4 directions around the taxi.
     * @return The direction the taxi should move to.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction direction = getDirection();
        Terrain t = theNeighbors.get(direction);
        if(t == Terrain.STREET || t == Terrain.CROSSWALK || t == Terrain.LIGHT)
            return direction;
        t = theNeighbors.get(direction.left());
        if(t == Terrain.STREET || t == Terrain.CROSSWALK || t == Terrain.LIGHT)
            return direction.left();
        t = theNeighbors.get(direction.right());
        if(t == Terrain.STREET || t == Terrain.CROSSWALK || t == Terrain.LIGHT)
            return direction.right();
        
        return direction.reverse();
    }
    
    /**

    Handles the collision between a Taxi object and another vehicle object.
    If the other vehicle is a truck object, sets the deathTime to 0.
    @param theOther the other vehicle object that this taxi object is colliding with
    */
    @Override
    public void collide(Vehicle theOther) {
        if(theOther instanceof Truck) {
            deathTime = 0;
        }
    }

    /**
    Gets the image file name for the taxi object.
    If the taxi is alive, returns "taxi.gif", else returns "taxi_dead.gif".
    @return the string representation of the image file name for the taxi object
    */
    @Override
    public String getImageFileName() {
        if(isAlive())
            return "taxi.gif";
        return "taxi_dead.gif";
    }

    /**
    Gets the death time for the taxi object.
    @return the integer representation of the death time for the taxi object
    */
    @Override
    public int getDeathTime() {
        // TODO Auto-generated method stub
        return 15;
    }
    
    /**
    Returns a string representation of the taxi object.
    The string representation is "Taxi" followed by the result of calling super.toString().
    @return the string representation of the taxi object
    */
    public String toString() {
        return "Taxi "+super.toString();
    }
}