/**
The class Truck represents a truck in the simulation.
@author Tsion Fufa
@version 2023 winter!
*/
package model;

import java.util.Map;

/**

This class extends the AbstractVehicle class and implements the required methods to simulate the behavior of a truck in the simulation.
@see AbstractVehicle
*/
public class Truck extends AbstractVehicle{

    /*
    * Creates a new Truck object with its initial X, Y coordinates, and direction.
    * @param X The initial X coordinate of the truck.
    * @param Y The initial Y coordinate of the truck.
    * @param direction The initial direction of the truck.
    */
    public Truck(int X, int Y, Direction direction) {
        super(X,Y,direction);
    }
    
    /**
     * This method determines if the truck can pass through a certain terrain and traffic light. 
     * @param theTerrain The type of terrain the truck is trying to pass.
     * @param theLight The state of the traffic light the truck is trying to pass.
     * @return A boolean indicating whether the truck can pass or not.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if(theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT)
            return true;
        else if(theTerrain == Terrain.CROSSWALK) {
            if(theLight == Light.RED)
                return false;
            return true;
        }
        return false;
    }

    /**
     * This method chooses the direction for the truck based on the neighbors' terrain.
     * @param theNeighbors A map containing the terrain information for all neighboring cells.
     * @return The direction the truck should move.
     */ 
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        int start = random.nextInt(3);
        int curr = start;
        boolean found = false;
        Direction direction = getDirection();
        Direction[] directions = new Direction[]{direction.left(), direction.right(),direction}; 
        
        while(!found){
            Direction d = directions[curr];
            if(theNeighbors.get(d) == Terrain.STREET ||
               theNeighbors.get(d) == Terrain.LIGHT ||
               theNeighbors.get(d) == Terrain.CROSSWALK){
                return d;
            }
            curr++;
            if(curr > 2)
                curr = 0;
            if(curr == start)
                break;
        }
        return direction.reverse();
    }
    
    /**
     * This method handles the collision between the truck and another vehicle. 
     * @param theOther The other vehicle that the truck is colliding with.
     */
    @Override
    public void collide(Vehicle theOther) {
        // TODO Auto-generated method stub
    }

    /**
    Returns the file name of the image representation of the {@code Truck} object.
    If the {@code Truck} object is alive, it returns "truck.gif".
    If the {@code Truck} object is dead, it returns "truck_dead.gif".
    @return a {@code String} representing the file name of the image
    */ 
    @Override
    public String getImageFileName() {
        if(isAlive())
            return "truck.gif";
        return "truck_dead.gif";
    }

    /**
    Returns the time required for the {@code Truck} object to die.
    @return an {@code int} representing the time required for the {@code Truck} object to die
    */
    @Override
    public int getDeathTime() {
        return 0;
    }
    
    /**
    Returns the {@code String} representation of the {@code Truck} object.
    The format is "Truck (x, y) dir".
    @return a {@code String} representation of the {@code Truck} object
    */
    public String toString() {
        return "Truck "+super.toString();
    }
}