package model;

import java.util.Map;

/**
 * The Bicycle class extends the AbstractVehicle class and implements its methods to
 * simulate the behavior of a Bicycle vehicle in the simulation. 
 * @author Tsion Fufa
 * @version 2023 winter!
 */
public class Bicycle extends AbstractVehicle{
    
    /**
     * Constructor for Bicycle class, creates a new Bicycle vehicle instance with the given x, y coordinates and direction.
     * 
     * @param X The x coordinate of the Bicycle
     * @param Y The y coordinate of the Bicycle
     * @param direction The direction of the Bicycle
     */
    public Bicycle(int X, int Y, Direction direction) {
        super(X,Y,direction);
    }
    
    /**
     * Overrides the canPass method from the AbstractVehicle class and implements a custom logic 
     * to determine if the Bicycle can pass a given terrain and light.
     * 
     * @param theTerrain The terrain type the Bicycle wants to pass
     * @param theLight The light type at the location of the terrain the Bicycle wants to pass
     * @return true if the Bicycle can pass the given terrain and light, false otherwise.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if(theTerrain == Terrain.CROSSWALK || theTerrain == Terrain.LIGHT) {
            if(theLight == Light.GREEN)
                return true;
            return false;
        }
        return true;
    }

    /**
     * Overrides the chooseDirection method from the AbstractVehicle class and implements a custom logic 
     * to determine the next direction for the Bicycle to move.
     * 
     * @param theNeighbors A map of the terrain types of the squares surrounding the Bicycle
     * @return The next direction for the Bicycle to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction direction = getDirection();
        Direction d = direction;
        
        if(theNeighbors.get(d) == Terrain.TRAIL)
            return d;
        d = direction.left();
        if(theNeighbors.get(d) == Terrain.TRAIL)
            return d;  
        d = direction.right();
        if(theNeighbors.get(d) == Terrain.TRAIL)
            return d; 
        
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
    This method is called when a collision occurs between two vehicles. If the colliding vehicle is a truck, taxi, car,
    or ATV, the deathTime of the bicycle is set to 0.
    @param theOther The other vehicle involved in the collision.
    */ 
    @Override
    public void collide(Vehicle theOther) {
        if(theOther instanceof Truck ||
           theOther instanceof Taxi ||
           theOther instanceof Car ||
           theOther instanceof Atv) {
            deathTime = 0;
        }
    }

    /**

    This method returns the name of the image file associated with the bicycle object.
    @return The name of the image file. The file name will be "bicycle.gif" if the bicycle is alive, and "bicycle_dead.gif"
    if the bicycle is dead.
    */ 
    @Override
    public String getImageFileName() {
        if(isAlive())
            return "bicycle.gif";
        return "bicycle_dead.gif";
    }

    /**

    This method returns the death time of the bicycle object.
    @return The death time of the bicycle, which is set to 35.
    */
    @Override
    public int getDeathTime() {
        // TODO Auto-generated method stub
        return 35;
    }
    
    /**

    This method returns a string representation of the Bicycle object.
    @return The string representation of the Bicycle object.
    */
    public String toString() {
        return "Bicycle "+super.toString();
    }
}