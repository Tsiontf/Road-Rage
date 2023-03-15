package model;

import java.util.Map;

/**
Represents an All Terrain Vehicle (ATV) in the simulation.
Extends the AbstractVehicle class.
@author Tsion Fufa
@version 2023 winter!
@see AbstractVehicle
*/
public class Atv extends AbstractVehicle{
    
    /**
     * Constructs an ATV object at the given X and Y position, facing the given direction
     * @param X The X coordinate of the ATV's position
     * @param Y The Y coordinate of the ATV's position
     * @param direction The direction that the ATV is facing
     */
    public Atv(int X, int Y, Direction direction) {
        super(X,Y,direction);
    }
    
    /**

    Determines whether the ATV can pass through the given terrain and light.
    @param theTerrain the terrain the ATV is trying to pass
    @param theLight the light at the location the ATV is trying to pass
    @return true if the ATV can pass the terrain and light, false otherwise
    */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if(theTerrain == Terrain.WALL)
            return false;
        return true;
    }

    /**

    Chooses a direction for the ATV to move based on the given map of neighbors.

    @param theNeighbors a map of the terrain in each direction relative to the ATV

    @return the chosen direction for the ATV to move
    */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction d = Direction.random();
        Terrain t = theNeighbors.get(d);
        
        while(t == Terrain.WALL) {
            d = Direction.random();
            t = theNeighbors.get(d);
        }
        
        return d;
    }
    
    /**

    Handles the collision between the ATV and another vehicle.
    @param theOther the vehicle the ATV has collided with
    */
    @Override
    public void collide(Vehicle theOther) {
        if(theOther instanceof Truck ||
            theOther instanceof Taxi ||
            theOther instanceof Car) {
             deathTime = 0;
         }
    }

    /**

    Gets the file name of the image to display for the ATV.
    @return the file name of the image to display for the ATV
    */
    @Override
    public String getImageFileName() {
        if(isAlive())
            return "atv.gif";
        return "atv_dead.gif";
    }

    /**

    Gets the death time of the ATV.
    @return the death time of the ATV
    */ 
    @Override
    public int getDeathTime() {
        // TODO Auto-generated method stub
        return 25;
    }
    
    /**

    Returns a string representation of the ATV.
    @return a string representation of the ATV
    */
    public String toString() {
        return "All Terrain Vehicle "+super.toString();
    }
}