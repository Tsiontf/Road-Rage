/**
 * Class to test the behavior of the Car class.
 * The class checks the methods of the Car class for correct implementation.
 *@author Tsion Fufa
 *@version 2023 winter!
 */
package tests;
import org.junit.Test;
import model.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CarTest {

    /**
     * Test the canPass method of the Car class.
     * The method tests if the car can pass a given terrain and traffic light.
     */
    @Test
    public void canPass() {
        Car car = new Car(10,10, Direction.random());
        assertFalse(car.canPass(Terrain.CROSSWALK, Light.RED));
        assertFalse(car.canPass(Terrain.LIGHT, Light.RED));
        assertFalse(car.canPass(Terrain.GRASS, Light.RED));
        assertTrue(car.canPass(Terrain.STREET, Light.GREEN));
        assertFalse(car.canPass(Terrain.WALL, Light.RED));
    }

    /**
     * Test the chooseDirection method of the Car class.
     * The method tests if the car can choose a direction based on the given terrain.
     */
    @Test
    public void chooseDirection() {
        Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.WALL);
        neighbors.put(Direction.SOUTH, Terrain.WALL);

        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;

        Car car = new Car(0, 0, Direction.random());

        for (int count = 0; count < 10; count++) {
            final Direction d = car.chooseDirection(neighbors);

            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) {
                seenSouth = true;
            }
        }

        assertTrue(!seenWest && seenNorth && !seenEast && !seenSouth);

    }

    /**
     * Test the collide method of the Car class.
     * The method tests if the car can collide with other entities.
     */
    @Test
    public void collide() {
        Car car = new Car(1,2, Direction.random());
        assertTrue(car.isAlive());
        car.collide(new Truck(0,0, Direction.random()));
        assertFalse(car.isAlive());
        car.reset();
        assertTrue(car.isAlive());
        car.collide(new Car(1,1, Direction.random()));
        assertTrue(car.isAlive());
        car.reset();
        assertTrue(car.isAlive());
        car.collide(new Human(1,1, Direction.random()));
        assertTrue(car.isAlive());
    }

    /**
     * Test the functionality of getImageFileName() method of Car class.
     * Creates a Car object and checks if it is alive and its image file name is "car.gif".
     * Then collides the Car object with a Truck object and checks if it is dead and its image file name is "car_dead.gif".
     */
    @Test
    public void getImageFileName() {
        Car car = new Car(1,2, Direction.random());
        assertTrue(car.isAlive());
        assertEquals("car.gif", car.getImageFileName());
        car.collide(new Truck(1,1, Direction.random()));
        assertFalse(car.isAlive());
        assertEquals("car_dead.gif", car.getImageFileName());
    }

    /**
     * Test the functionality of getDeathTime() method of Car class.
     * Creates a Car object and checks if its death time is 15.
     */
    @Test
    public void getDeathTime() {
        Car car = new Car(1,1, Direction.random());
        assertEquals(15, car.getDeathTime());
    }
}