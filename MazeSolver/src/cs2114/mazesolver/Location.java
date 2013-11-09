package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 * This class represents a location in the maze. It contains both an x and a y
 * position to represent the coordinates of a location.
 *
 * @author Benjamin Kodres-O'Brien (bkobrien)
 * @version 2013.10.04
 */

public class Location
    implements ILocation
{

    private int x; // The width location property
    private int y; // The height location property


    /**
     * Creates a location object with an x and y coordinate.
     *
     * @param x
     *            The width coordinate
     * @param y
     *            The height coordinate
     */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    /**
     * Gets a new location that represents the (x, y) coordinates one cell east
     * of this location.
     *
     * @return a new location that represents the (x, y) coordinates one cell
     *         east of this location
     */
    public ILocation east()
    {
        return new Location(x + 1, y);
    }


    /**
     * Gets a new location that represents the (x, y) coordinates one cell north
     * of this location.
     *
     * @return a new location that represents the (x, y) coordinates one cell
     *         north of this location
     */
    public ILocation north()
    {
        return new Location(x, y - 1);
    }


    /**
     * Gets a new location that represents the (x, y) coordinates one cell south
     * of this location.
     *
     * @return a new location that represents the (x, y) coordinates one cell
     *         south of this location
     */
    public ILocation south()
    {
        return new Location(x, y + 1);
    }


    /**
     * Gets a new location that represents the (x, y) coordinates one cell west
     * of this location.
     *
     * @return a new location that represents the (x, y) coordinates one cell
     *         west of this location
     */
    public ILocation west()
    {
        return new Location(x - 1, y);

    }


    /**
     * Gets the x-coordinate of the location.
     *
     * @return the x-coordinate of the location
     */
    public int x()
    {
        return x;
    }


    /**
     * Gets the y-coordinate of the location.
     *
     * @return the y-coordinate of the location
     */
    public int y()
    {
        return y;
    }


    /**
     * Checks to see if an object is equal to this location.
     *
     * @param toCompare
     *            The object to compare to this location
     * @return true if the x and y coordinates of another location are equal to
     *         this locations x and y coordinates, otherwise it returns false
     */
    @Override
    public boolean equals(Object toCompare)
    {
        if (toCompare instanceof Location)
        {
            Location newLocation = (Location)toCompare;
            if (this.x() == newLocation.x() && this.y() == newLocation.y())
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Creates a string representation of this location object in the format
     * "(x, y)" without quotations where x and y are the coordinates of this
     * location.
     *
     * @return the string representation of this location
     */
    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}
