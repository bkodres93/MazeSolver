package cs2114.mazesolver;

import java.util.Stack;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This class represents the maze itself. It contains an array that stores the
 * size of the maze as well as what each cell in the maze is..
 *
 * @author Benjamin Kodres-O'Brien (bkobrien)
 * @version 2013.10.04
 */

public class Maze extends sofia.util.Observable
    implements IMaze
{

    private MazeCell[][] theMaze;
    private ILocation    startLocation;
    private ILocation    goalLocation;


    // ----------------------------------------------------------
    /**
     * Create a new Maze with a specified size.
     *
     * @param size
     *            The width and height of the maze
     */
    public Maze(int size)
    {
        theMaze = new MazeCell[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                theMaze[i][j] = MazeCell.UNEXPLORED;
            }
        }
        startLocation = new Location(0, 0);
        goalLocation = new Location(size - 1, size - 1);
    }


    // ----------------------------------------------------------
    /**
     * Gets the MazeCell enum value for a given location
     *
     * @param location
     *            The location to get the MazeCell value for
     * @return the enum MazeCell value
     */
    @Override
    public MazeCell getCell(ILocation location)
    {
        if (location.x() >= 0 && location.y() >= 0
            && location.x() < this.size() && location.y() < this.size())
        {
            return theMaze[location.x()][location.y()];
        }
        return MazeCell.INVALID_CELL;
    }


    // ----------------------------------------------------------
    /**
     * Returns the location of the goal in the maze.
     *
     * @return the goal location
     */
    @Override
    public ILocation getGoalLocation()
    {
        return goalLocation;
    }


    // ----------------------------------------------------------
    /**
     * Returns the location of the start in the maze.
     *
     * @return the start location
     */
    @Override
    public ILocation getStartLocation()
    {
        return startLocation;
    }


    // ----------------------------------------------------------
    /**
     * Sets the given cell to a given MazeCell enum value. The method checks to
     * see if the cell thats trying to be set is a wall or if it is the start or
     * goal locations and will ignore the request to set if one of those is
     * true.
     *
     * @param location
     *            The location to set
     * @param mazeCell
     *            The MazeCell enum value to set the location to
     */
    @Override
    public void setCell(ILocation location, MazeCell mazeCell)
    {
        if (location.x() >= 0 && location.y() >= 0
            && location.x() < this.size() && location.y() < this.size())
        {
            if (!(mazeCell == MazeCell.WALL && (location.equals(goalLocation)
                || location.equals(startLocation))))
            {
                theMaze[location.x()][location.y()] = mazeCell;
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Sets the goal location to a given location.
     *
     * @param location
     *            The location to set to the goal
     */
    @Override
    public void setGoalLocation(ILocation location)
    {
        if (this.getCell(location) == MazeCell.WALL)
        {
            setCell(location, MazeCell.UNEXPLORED);
        }
        goalLocation = new Location(location.x(), location.y());
    }


    // ----------------------------------------------------------
    /**
     * Sets the start location to a given location.
     *
     * @param location
     *            The location to set to the start
     */
    @Override
    public void setStartLocation(ILocation location)
    {
        if (this.getCell(location) == MazeCell.WALL)
        {
            setCell(location, MazeCell.UNEXPLORED);
        }
        startLocation = new Location(location.x(), location.y());
    }


    // ----------------------------------------------------------
    /**
     * Returns the size of the maze.
     *
     * @return the size of the maze
     */
    @Override
    public int size()
    {
        return theMaze.length;
    }


    // ----------------------------------------------------------
    @Override
    public String solve()
    {
        Stack<ILocation> stack;
        stack = new Stack<ILocation>();
        stack.push(startLocation);
        while (!(stack.isEmpty()))
        {
            ILocation current = stack.peek();
            if (current.equals(goalLocation))
            {
                setCell(current, MazeCell.CURRENT_PATH);
                break;
            }
            setCell(current, MazeCell.CURRENT_PATH);
            if (getCell(current.east()) == MazeCell.UNEXPLORED)
            {
                stack.push(current.east());
            }
            else if (getCell(current.south()) == MazeCell.UNEXPLORED)
            {
                stack.push(current.south());
            }
            else if (getCell(current.west()) == MazeCell.UNEXPLORED)
            {
                stack.push(current.west());
            }
            else if (getCell(current.north()) == MazeCell.UNEXPLORED)
            {
                stack.push(current.north());
            }
            else
            {
                setCell(stack.pop(), MazeCell.FAILED_PATH);
            }
        }
        if (stack.isEmpty())
        {
            return null;
        }

        // Making the string to return
        StringBuilder toReturn = new StringBuilder();
        ArrayList<String> result = new ArrayList<String>();
        int n = stack.size();
        for (int i = 0; i < n; i++)
        {
            result.add(0, stack.pop().toString());
        }
        toReturn = new StringBuilder();
        for (int i = 0; i < result.size(); i++)
        {
            toReturn.append(result.get(i) + " ");
        }
        toReturn.deleteCharAt(toReturn.length() - 1);
        return toReturn.toString();
    }

}
