/**

The {@code TruckTest} class is a JUnit test class for the {@link Truck} class.
It tests the functionality of the {@link Truck} class.
@author Tsion Fufa
@version 1.0
@since 2023-02-04
*/
package tests;
import org.junit.Test;
import model.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TruckTest {

    /**
     * Tests the {@link Truck#canPass(Terrain, Light)} method.
     * It tests if the method returns the expected output for different inputs.
     */
    @Test
    public void canPass() {
        Truck truck = new Truck(10,10, Direction.random());
        assertFalse(truck.canPass(Terrain.CROSSWALK, Light.RED));
        assertTrue(truck.canPass(Terrain.LIGHT, Light.RED));
        assertFalse(truck.canPass(Terrain.GRASS, Light.RED));
        assertTrue(truck.canPass(Terrain.STREET, Light.GREEN));
        assertFalse(truck.canPass(Terrain.WALL, Light.RED));
    }

    /**
     * Tests the {@link Truck#chooseDirection(Map)} method.
     * It tests if the method returns the expected direction based on the terrain of the neighbors.
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
     * Tests the {@link Truck#collide(Actor)} method.
     * It tests if the method updates the {@link Truck} object's state correctly after a collision.
     */
    @Test
    public void collide() {
        Truck truck = new Truck(1,2, Direction.random());
        assertTrue(truck.isAlive());
        truck.collide(new Truck(0,0, Direction.random()));
        assertTrue(truck.isAlive());
        truck.reset();
        assertTrue(truck.isAlive());
        truck.collide(new Car(1,1, Direction.random()));
        assertTrue(truck.isAlive());
        truck.reset();
        assertTrue(truck.isAlive());
        truck.collide(new Human(1,1, Direction.random()));
        assertTrue(truck.isAlive());
    }

    /**
    Tests the {@link Truck#getImageFileName()} method.
    Verifies if the method returns the correct image file name for the truck.
    */
    @Test
    public void getImageFileName() {
        Truck truck = new Truck(1,2, Direction.random());
        assertTrue(truck.isAlive());
        assertEquals("truck.gif", truck.getImageFileName());
    }

    /**
    Tests the {@link Truck#getDeathTime()} method.
    Verifies if the method returns the correct death time for the truck.
    */
    @Test
    public void getDeathTime() {
        Truck truck = new Truck(1,1, Direction.random());
        assertEquals(0, truck.getDeathTime());
    }

}