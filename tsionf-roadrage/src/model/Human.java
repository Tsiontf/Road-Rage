/**

The Human class represents a human vehicle in a simulation game.
It extends the {@link AbstractVehicle} class and overrides the methods defined in the {@link Vehicle} interface.
@author Tsion Fufa
@version 2023 winter!
@see AbstractVehicle
@see Vehicle
*/
package model;

import java.util.Map;

public class Human extends AbstractVehicle{
    
    /**
    * Constructs a new Human object with the given x-coordinate, y-coordinate and direction.
    * @param X The x-coordinate of the Human object.
    * @param Y The y-coordinate of the Human object.
    * @param direction The direction of the Human object.
    */
    public Human(int X, int Y, Direction direction) {
        super(X,Y,direction);
    }
    
    /**
     * Determines if the Human object can pass through the given terrain and light condition. 
     * @param theTerrain The terrain type the Human object is trying to pass.
     * @param theLight The light condition at the terrain the Human object is trying to pass.
     * @return {@code true} if the Human object can pass the terrain and light condition, {@code false} otherwise.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if(theTerrain == Terrain.CROSSWALK) {
            if(theLight == Light.GREEN)
                return false;
            return true;
        }else if(theTerrain == Terrain.GRASS)
            return true;
        return false;
    }

    /**
     * Chooses a direction for the Human object to move based on the neighboring terrains.
     * 
     * @param theNeighbors A map of directions to neighboring terrains.
     * @return The direction the Human object should move.
     */ 
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction direction = getDirection();
        Direction d = direction;
        if(theNeighbors.get(d) == Terrain.CROSSWALK)
            return d;
        d = direction.left();
        if(theNeighbors.get(d) == Terrain.CROSSWALK)
            return d;  
        d = direction.right();
        if(theNeighbors.get(d) == Terrain.CROSSWALK)
            return d; 
        

        int start = random.nextInt(3);
        int curr = start;
        boolean found = false;
        Direction[] directions = new Direction[]{direction.left(), direction.right(),direction}; 
        
        while(!found){
            d = directions[curr];
            if(theNeighbors.get(d) == Terrain.GRASS ||
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
     * Handles the collision between a human and another vehicle.
     * 
     * @param theOther the other vehicle involved in the collision
     */ 
    @Override
    public void collide(Vehicle theOther) {
        if(!(theOther instanceof Human)) {
            deathTime = 0;
        }
    }

    /**
     * Gets the image file name for the human.
     * 
     * @return the image file name
     */ 
    @Override
    public String getImageFileName() {
        if(isAlive())
            return "human.gif";
        return "human_dead.gif";
    }

    /**
    Overrides the abstract method {@link AbstractVehicle#getDeathTime()} to return the death time of a Human object.
    @return an integer value of 45, representing the death time of a Human object.
    */
    @Override
    public int getDeathTime() {
        // TODO Auto-generated method stub
        return 45;
    }
    
    /**
    Returns a string representation of a Human object, including its location and direction information.
    @return a string value that represents the Human object's information.
    */
    public String toString() {
        return "Human "+super.toString();
    }
}