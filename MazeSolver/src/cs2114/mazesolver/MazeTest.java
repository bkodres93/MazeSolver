package cs2114.mazesolver;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This class tests the maze class. It contains methods that run through the
 * code in the maze class that try to discover bugs.
 *
 * @author Benjamin Kodres-O'Brien (bkobrien)
 * @version 2013.10.04
 */

public class MazeTest
    extends TestCase
{

    private Maze maze;
    private Maze maze2;


    // ----------------------------------------------------------
    /**
     * Sets up a maze or two.
     *
     * @throws java.lang.Exception
     */
    public void setUp()
        throws Exception
    {
        maze = new Maze(5);
        maze2 = new Maze(10);
        // Creating my custom maze
        maze2.setCell(new Location(3, 0), MazeCell.WALL);
        maze2.setCell(new Location(4, 0), MazeCell.WALL);
        maze2.setCell(new Location(5, 0), MazeCell.WALL);
        maze2.setCell(new Location(9, 0), MazeCell.WALL);
        maze2.setCell(new Location(1, 1), MazeCell.WALL);
        maze2.setCell(new Location(2, 1), MazeCell.WALL);
        maze2.setCell(new Location(5, 1), MazeCell.WALL);
        maze2.setCell(new Location(6, 1), MazeCell.WALL);
        maze2.setCell(new Location(7, 1), MazeCell.WALL);
        maze2.setCell(new Location(9, 1), MazeCell.WALL);
        maze2.setCell(new Location(1, 2), MazeCell.WALL);
        maze2.setCell(new Location(3, 2), MazeCell.WALL);
        maze2.setCell(new Location(5, 2), MazeCell.WALL);
        maze2.setCell(new Location(9, 2), MazeCell.WALL);
        maze2.setCell(new Location(3, 3), MazeCell.WALL);
        maze2.setCell(new Location(5, 3), MazeCell.WALL);
        maze2.setCell(new Location(7, 3), MazeCell.WALL);
        maze2.setCell(new Location(9, 3), MazeCell.WALL);
        maze2.setCell(new Location(0, 4), MazeCell.WALL);
        maze2.setCell(new Location(1, 4), MazeCell.WALL);
        maze2.setCell(new Location(3, 4), MazeCell.WALL);
        maze2.setCell(new Location(7, 4), MazeCell.WALL);
        maze2.setCell(new Location(9, 4), MazeCell.WALL);
        maze2.setCell(new Location(0, 5), MazeCell.WALL);
        maze2.setCell(new Location(1, 5), MazeCell.WALL);
        maze2.setCell(new Location(7, 5), MazeCell.WALL);
        maze2.setCell(new Location(9, 5), MazeCell.WALL);
        maze2.setCell(new Location(0, 6), MazeCell.WALL);
        maze2.setCell(new Location(1, 6), MazeCell.WALL);
        maze2.setCell(new Location(2, 6), MazeCell.WALL);
        maze2.setCell(new Location(4, 6), MazeCell.WALL);
        maze2.setCell(new Location(7, 6), MazeCell.WALL);
        maze2.setCell(new Location(1, 7), MazeCell.WALL);
        maze2.setCell(new Location(4, 7), MazeCell.WALL);
        maze2.setCell(new Location(7, 7), MazeCell.WALL);
        maze2.setCell(new Location(8, 7), MazeCell.WALL);
        maze2.setCell(new Location(1, 8), MazeCell.WALL);
        maze2.setCell(new Location(2, 8), MazeCell.WALL);
        maze2.setCell(new Location(3, 8), MazeCell.WALL);
        maze2.setCell(new Location(4, 8), MazeCell.WALL);
        maze2.setCell(new Location(8, 8), MazeCell.WALL);
        maze2.setCell(new Location(6, 9), MazeCell.WALL);
        maze2.setCell(new Location(7, 9), MazeCell.WALL);
        maze2.setCell(new Location(8, 9), MazeCell.WALL);
    }


    /**
     * Tests the constructor and that it indeed makes a maze object.
     */
    public void testMaze()
    {
        assertNotNull(maze);
    }


    /**
     * Confirms that the size method returns the correct size.
     */
    public void testSize()
    {
        assertEquals(maze.size(), 5);
    }


    /**
     * Checks if the getCell method returns the correct cell.
     */
    public void testGetCell()
    {
        assertEquals(maze.getCell(new Location(0, 0)), MazeCell.UNEXPLORED);
        assertEquals(maze.getCell(new Location(-1, -1)), MazeCell.INVALID_CELL);
        assertEquals(maze.getCell(new Location(6, 6)), MazeCell.INVALID_CELL);

    }


    /**
     * Makes sure that setting a cell works correctly.
     */
    public void testSetCell()
    {
        maze.setCell(new Location(1, 0), MazeCell.CURRENT_PATH);
        assertEquals(maze.getCell(new Location(1, 0)), MazeCell.CURRENT_PATH);
        maze.setCell(new Location(0, 0), MazeCell.WALL);
        assertEquals(maze.getCell(new Location(0, 0)), MazeCell.UNEXPLORED);
        maze.setCell(new Location(4, 4), MazeCell.WALL);
        assertEquals(maze.getCell(new Location(0, 0)), MazeCell.UNEXPLORED);
        maze.setCell(new Location(4, 4), MazeCell.CURRENT_PATH);
        assertEquals(maze.getCell(new Location(4, 4)), MazeCell.CURRENT_PATH);
        maze.setCell(new Location(3, 3), MazeCell.WALL);
        assertEquals(maze.getCell(new Location(3, 3)), MazeCell.WALL);
        maze.setCell(new Location(3, 3), MazeCell.CURRENT_PATH);
        assertEquals(maze.getCell(new Location(3, 3)), MazeCell.CURRENT_PATH);
        maze.setCell(new Location(-1, -1), MazeCell.CURRENT_PATH);
        assertEquals(maze.getCell(new Location(-1, -1)), MazeCell.INVALID_CELL);
        maze.setCell(new Location(5, 5), MazeCell.CURRENT_PATH);
        assertEquals(maze.getCell(new Location(5, 5)), MazeCell.INVALID_CELL);
        maze.setCell(new Location(-1, 0), MazeCell.CURRENT_PATH);
        assertEquals(maze.getCell(new Location(-1, 0)), MazeCell.INVALID_CELL);
        maze.setCell(new Location(0, -1), MazeCell.CURRENT_PATH);
        assertEquals(maze.getCell(new Location(0, -1)), MazeCell.INVALID_CELL);
        maze.setCell(new Location(5, 4), MazeCell.CURRENT_PATH);
        assertEquals(maze.getCell(new Location(5, 4)), MazeCell.INVALID_CELL);
        maze.setCell(new Location(4, 5), MazeCell.CURRENT_PATH);
        assertEquals(maze.getCell(new Location(-1, -1)), MazeCell.INVALID_CELL);
    }


    /**
     * Confirms that the correct goal location is returned.
     */
    public void testGetGoalLocation()
    {
        assertTrue(maze.getGoalLocation().equals(
            new Location(maze.size() - 1, maze.size() - 1)));
    }


    /**
     * Confirms that getting the start location works as it is supposed to.
     */
    public void testGetStartLocation()
    {
        assertTrue(maze.getStartLocation().equals(new Location(0, 0)));
    }


    /**
     * Tests to see if setting the goal location works correctly.
     */
    public void testSetGoalLocation()
    {
        maze.setGoalLocation(new Location(3, 3));
        assertTrue(maze.getGoalLocation().equals(new Location(3, 3)));
        maze.setCell(new Location(0, 0), MazeCell.WALL);
        maze.setGoalLocation(new Location(0, 0));
    }


    /**
     * Tests that setting a start location works correctly.
     */
    public void testSetStartLocation()
    {
        maze.setStartLocation(new Location(3, 3));
        assertTrue(maze.getStartLocation().equals(new Location(3, 3)));
        maze.setCell(new Location(0, 0), MazeCell.WALL);
        maze.setStartLocation(new Location(0, 0));
    }


    /**
     * Tests that the maze was solved correctly.
     */
    public void testSolve()
    {
        assertTrue(maze2.solve().equals(
            "(0, 0) (0, 1) (0, 2) (0, 3) (1, 3) (2, 3) (2, 4) (2, 5) "
                + "(3, 5) (4, 5) (5, 5) (6, 5) (6, 4) (6, 3) (6, 2) (7, 2) "
                + "(8, 2) (8, 3) (8, 4) (8, 5) (8, 6) (9, 6) (9, 7) (9, 8) "
                + "(9, 9)"));
    }


    /**
     * Tests solve for a maze that has no solution
     */
    public void testSolve2()
    {
        maze2.setCell(new Location(0, 1), MazeCell.WALL);
        assertNull(maze2.solve());
    }


    /**
     * Tests solve for a maze with a different start and goal.
     */
    public void testSolve3()
    {
        maze2.setStartLocation(new Location(7, 8));
        maze2.setGoalLocation(new Location(3, 3));
        assertTrue(maze2.solve().equals(
            "(7, 8) (6, 8) (5, 8) (5, 7) (6, 7) (6, 6) (5, 6) (5, 5) (6, 5) "
                + "(6, 4) (5, 4) (4, 4) (4, 5) (3, 5) (2, 5) (2, 4) (2, 3) "
                + "(3, 3)"));
    }
}
