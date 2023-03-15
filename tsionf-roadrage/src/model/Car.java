package model;
import java.util.Map;

/**
This class represents a Car object in a simulation.
The Car extends the {@link AbstractVehicle} class and overrides its methods to provide a specific behavior for a Car in the simulation.
@author Tsion Fufa
@version 2023 winter!
@see AbstractVehicle
@see Terrain
@see Light
@see Direction
@see Vehicle
*/
public class Car extends AbstractVehicle{
    
    /**
    Constructs a new Car object with the specified X and Y coordinates and direction.
    @param X the X coordinate of the Car
    @param Y the Y coordinate of the Car
    @param direction the direction the Car is facing
    */
    public Car(int X, int Y, Direction direction) {
        super(X,Y,direction);
    }
    
    /**
    Determines whether the Car can pass through a given terrain type and light condition.
    @param theTerrain the type of terrain the Car is currently on
    @param theLight the light condition at the current location of the Car
    @return true if the Car can pass through the terrain and light condition, false otherwise
    */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if(theTerrain == Terrain.LIGHT) {
            if(theLight == Light.RED)
                return false;
            return true;
        }
        else if(theTerrain == Terrain.CROSSWALK) {
            if(theLight == Light.RED || theLight == Light.YELLOW)
                return false;
            return true;
        }
        if(theTerrain == Terrain.STREET)
            return true;
        return false;
    }

    /**

    Determines the direction the Car should move in based on its surroundings.
    @param theNeighbors a map of the terrain types in the four cardinal directions relative to the Car's current location
    @return the direction the Car should move in
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

    Handles a collision between the Car and another Vehicle.
    @param theOther the other Vehicle involved in the collision
    */
    @Override
    public void collide(Vehicle theOther) {
        if(theOther instanceof Truck) {
            deathTime = 0;
        }
    }

    /**
     * Returns the name of the image file representing this car.
     * @return the name of the image file representing this car
     */ 
    @Override
    public String getImageFileName() {
        if(isAlive())
            return "car.gif";
        return "car_dead.gif";
    }

    /**

    Returns the death time of the car object, which is fixed to 15.
    @return int value 15 as the death time of the car.
    */
    @Override
    public int getDeathTime() {
        // TODO Auto-generated method stub
        return 15;
    }
    
    /**

    Returns the string representation of the car object, including its parent class information.
    @return A string representation of the car object, including its parent class information.
    */
    public String toString() {
        return "Car "+super.toString();
    }
}