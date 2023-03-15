/**
The AtvTest class tests the functionality of the Atv class.
It checks if the Atv can pass certain terrains with different lights,
if it can choose the correct direction, if it can collide with other objects and what happens after,
if it returns the correct image file name, and if it returns the correct death time.
 @author Tsion Fufa
 @version 2023 winter!
*/
package tests;
import org.junit.Test;
import model.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AtvTest {

    /**
     * Tests if the Atv can pass certain terrains with different lights.
     */
    @Test
    public void canPass() {
        Atv atv = new Atv(10,10, Direction.random());
        assertTrue(atv.canPass(Terrain.CROSSWALK, Light.RED));
        assertTrue(atv.canPass(Terrain.TRAIL, Light.RED));
        assertTrue(atv.canPass(Terrain.GRASS, Light.RED));
        assertTrue(atv.canPass(Terrain.STREET, Light.GREEN));
        assertFalse(atv.canPass(Terrain.WALL, Light.RED));
    }

    /**
     * Tests if the Atv can choose the correct direction.
     */
    @Test
    public void chooseDirection() {
        Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.WALL);
        neighbors.put(Direction.SOUTH, Terrain.WALL);

        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;

        Atv atv = new Atv(0, 0, Direction.random());

        for (int count = 0; count < 10; count++) {
            final Direction d = atv.chooseDirection(neighbors);

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
     * Tests if the Atv can collide with other objects and what happens after.
     */
    @Test
    public void collide() {
        Atv atv = new Atv(1,2, Direction.random());
        assertTrue(atv.isAlive());
        atv.collide(new Truck(0,0, Direction.random()));
        assertFalse(atv.isAlive());
        atv.reset();
        assertTrue(atv.isAlive());
        atv.collide(new Car(1,1, Direction.random()));
        assertFalse(atv.isAlive());
        atv.reset();
        assertTrue(atv.isAlive());
        atv.collide(new Human(1,1, Direction.random()));
        assertTrue(atv.isAlive());
    }

    /**

    Test method for Atv#getImageFileName().
    Tests the image file name returned by the method and verifies if it changes after a collision occurs.
    */
    @Test
    public void getImageFileName() {
        Atv atv = new Atv(1,2, Direction.random());
        assertTrue(atv.isAlive());
        assertEquals("atv.gif", atv.getImageFileName());
        atv.collide(new Car(1,1, Direction.random()));
        assertFalse(atv.isAlive());
        assertEquals("atv_dead.gif", atv.getImageFileName());
    }

    /**

    Test method for Atv#getDeathTime().
    Tests the value of death time returned by the method.
    */
    @Test
    public void getDeathTime() {
        Atv atv = new Atv(1,1, Direction.random());
        assertEquals(25, atv.getDeathTime());
    }
}