package cs2114.mazesolver;

import student.TestCase;

/**
 * This class tests the location class. It contains methods that run through the
 * code in the location class that try to discover bugs.
 *
 * @author Benjamin Kodres-O'Brien (bkobrien)
 * @version 2013.10.04
 */

public class LocationTest
    extends TestCase
{

    private Location location;


    // ----------------------------------------------------------
    /**
     * Creates a new location object.
     */
    public void setUp()
    {
        location = new Location(0, 0);
    }


    /**
     * Makes sure the constructor indeed creates a location object.
     */
    public void testLocation()
    {
        assertNotNull(location);
    }


    /**
     * Tests the see that getting the x coordinate works correctly.
     */
    public void testX()
    {
        assertEquals(location.x(), 0);
    }


    /**
     * Tests the see that getting the y coordinate works correctly.
     */
    public void testY()
    {
        assertEquals(location.y(), 0);
    }


    /**
     * Tests that the position to the east of a given location returns the
     * position one to the right of the original position.
     */
    public void testEast()
    {
        Location location2 = new Location(1, 0);
        assertEquals(location.east(), location2);
    }


    /**
     * Tests that the position to the east of a given location returns the
     * position one above the original position.
     */
    public void testNorth()
    {
        Location location2 = new Location(0, -1);
        assertEquals(location.north(), location2);
    }


    /**
     * Tests that the position to the east of a given location returns the
     * position one below the original position.
     */
    public void testSouth()
    {
        Location location2 = new Location(0, 1);
        assertEquals(location.south(), location2);
    }


    /**
     * Tests that the position to the east of a given location returns the
     * position one to the left of the original position.
     */
    public void testWest()
    {
        Location location2 = new Location(-1, 0);
        assertEquals(location.west(), location2);
    }


    /**
     * Tests that the equals method compares the right fields and confirms if
     * they are equal.
     */
    public void testEquals()
    {
        Location location2 = new Location(0, 0);
        Location location3 = new Location(0, 1);
        assertFalse(location.equals(location3));
        assertFalse(location.equals("Hello"));
        assertTrue(location.equals(location2));
    }


    /**
     * Tests that the string representation of a location prints the right
     * string.
     */
    public void testToString()
    {
        assertTrue(location.toString().equals("(0, 0)"));
    }
}
