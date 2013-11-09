package cs2114.mazesolver;

import android.widget.Button;
import android.widget.TextView;
import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * The test class for the screen. It is meant to test that interacting with the
 * GUI changes the screen in the correct ways.
 *
 * @author Benjamin Kodres-O'Brien (bkobrien)
 * @version 2013.10.25
 */
public class MazeSolverScreenTests
    extends student.AndroidTestCase<MazeSolverScreen>
{
    // ~ Fields ................................................................
    private Button    solve;
    private Button    drawWalls;
    private Button    eraseWalls;
    private Button    setGoal;
    private Button    setStart;
    private ShapeView shapeView;
    private TextView  infoLabel;

    // This field will store the pixel width/height of a cell in the maze.
    private int       cellSize;


    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public MazeSolverScreenTests()
    {
        super(MazeSolverScreen.class);
    }


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Initializes the text fixtures.
     */
    public void setUp()
    {
        float viewSize = Math.min(shapeView.getWidth(), shapeView.getHeight());
        cellSize = (int)(viewSize / 8);
    }


    // ~ Test methods ..........................................................
    /**
     * Tests that the maze was set up correctly.
     */
    public void testMazeSolverScreen()
    {
        assertNotNull(getScreen().getMaze());
    }


    /**
     * Tests that the draw button changes the mode correctly.
     */
    public void testDrawWallsClicked()
    {
        click(drawWalls);
        assertEquals(getScreen().getMode(), Mode.DRAW);
    }


    /**
     * Tests that the erase button changes the mode correctly.
     */
    public void testEraseWallsClicked()
    {
        click(eraseWalls);
        assertEquals(getScreen().getMode(), Mode.ERASE);
    }


    /**
     * Tests that the set start button changes the mode correctly.
     */
    public void testSetStartClicked()
    {
        click(setStart);
        assertEquals(getScreen().getMode(), Mode.SET_START);
    }


    /**
     * Tests that the set start button changes the mode correctly.
     */
    public void testSetGoalClicked()
    {
        click(setGoal);
        assertEquals(getScreen().getMode(), Mode.SET_GOAL);
    }


    /**
     * Tests that clicking on a cell to draw a wall works.
     */
    public void testOnTouchDown()
    {
        clickCell(0, 0);
        clickCell(2, 0);
        assertEquals(
            getScreen().getMaze().getCell(new Location(2, 0)),
            MazeCell.WALL);
    }


    /**
     * Tests that erasing a wall works.
     */
    public void testOnTouchDown2()
    {
        clickCell(2, 0);
        click(eraseWalls);
        clickCell(2, 0);
        assertEquals(
            getScreen().getMaze().getCell(new Location(2, 0)),
            MazeCell.UNEXPLORED);
    }


    /**
     * Tests that setting the start location works correctly.
     */
    public void testOnTouchDown3()
    {
        click(setStart);
        clickCell(2, 0);
        assertTrue(getScreen().getMaze().getStartLocation()
            .equals(new Location(2, 0)));
        click(drawWalls);
        clickCell(3, 0);
        click(setStart);
        clickCell(3, 0);
        assertTrue(getScreen().getMaze().getStartLocation()
            .equals(new Location(3, 0)));
        assertEquals(
            getScreen().getMaze().getCell(new Location(3, 0)),
            MazeCell.UNEXPLORED);
    }


    /**
     * Tests that setting the goal location works correctly.
     */
    public void testOnTouchDown4()
    {
        click(setGoal);
        clickCell(2, 0);
        assertTrue(getScreen().getMaze().getGoalLocation()
            .equals(new Location(2, 0)));
        click(drawWalls);
        clickCell(3, 0);
        click(setGoal);
        clickCell(3, 0);
        assertTrue(getScreen().getMaze().getGoalLocation()
            .equals(new Location(3, 0)));
        assertEquals(
            getScreen().getMaze().getCell(new Location(3, 0)),
            MazeCell.UNEXPLORED);
    }


    /**
     * Tests that clicking on a cell to draw a wall works.
     */
    public void testOnTouchMove()
    {
        touchDownCell(1, 0);
        touchMoveCell(2, 0);
        touchMoveCell(3, 0);
        touchMoveCell(0, 0);
        touchUp();
        assertEquals(
            getScreen().getMaze().getCell(new Location(0, 0)),
            MazeCell.UNEXPLORED);
        assertEquals(
            getScreen().getMaze().getCell(new Location(1, 0)),
            MazeCell.WALL);
        assertEquals(
            getScreen().getMaze().getCell(new Location(2, 0)),
            MazeCell.WALL);
        assertEquals(
            getScreen().getMaze().getCell(new Location(3, 0)),
            MazeCell.WALL);
    }


    /**
     * Tests that erasing a wall works.
     */
    public void testOnTouchMove2()
    {
        touchDownCell(1, 0);
        touchMoveCell(2, 0);
        touchMoveCell(3, 0);
        touchUp();
        click(eraseWalls);
        touchDownCell(1, 0);
        touchMoveCell(2, 0);
        touchMoveCell(3, 0);
        touchUp();
        assertEquals(
            getScreen().getMaze().getCell(new Location(1, 0)),
            MazeCell.UNEXPLORED);
        assertEquals(
            getScreen().getMaze().getCell(new Location(2, 0)),
            MazeCell.UNEXPLORED);
        assertEquals(
            getScreen().getMaze().getCell(new Location(3, 0)),
            MazeCell.UNEXPLORED);
    }


    /**
     * Tests that setting the start location works correctly.
     */
    public void testOnTouchMove3()
    {
        click(setStart);
        touchDownCell(2, 0);
        touchMoveCell(3, 0);
        touchUp();
        assertTrue(getScreen().getMaze().getStartLocation()
            .equals(new Location(3, 0)));
        click(drawWalls);
        clickCell(4, 0);
        click(setStart);
        touchDownCell(3, 0);
        touchMoveCell(4, 0);
        touchUp();
        assertTrue(getScreen().getMaze().getStartLocation()
            .equals(new Location(4, 0)));
        assertEquals(
            getScreen().getMaze().getCell(new Location(4, 0)),
            MazeCell.UNEXPLORED);
    }


    /**
     * Tests that setting the goal location works correctly.
     */
    public void testOnTouchMove4()
    {
        click(setGoal);
        touchDownCell(2, 0);
        touchMoveCell(3, 0);
        touchUp();
        assertTrue(getScreen().getMaze().getGoalLocation()
            .equals(new Location(3, 0)));
        click(drawWalls);
        clickCell(4, 0);
        click(setGoal);
        touchDownCell(3, 0);
        touchMoveCell(4, 0);
        touchUp();
        assertTrue(getScreen().getMaze().getGoalLocation()
            .equals(new Location(4, 0)));
        assertEquals(
            getScreen().getMaze().getCell(new Location(4, 0)),
            MazeCell.UNEXPLORED);
    }


    /**
     * Tests solving a maze with no solution.
     */
    public void testSolveClicked()
    {
        clickCell(1, 0);
        clickCell(0, 1);
        click(solve);
        assertTrue(infoLabel.getText().toString()
            .equals("No solution was possible."));
    }


    /**
     * Tests solving a maze with no solution.
     */
    public void testSolveClicked2()
    {
        touchDownCell(1, 0);
        touchMoveCell(1, 6);
        touchUp();
        touchDownCell(6, 7);
        touchMoveCell(6, 4);
        touchUp();
        click(solve);
        assertTrue(infoLabel.getText().toString().equals("(0, 0) (0, 1) (1, 1" +
            ") (2, 1) (3, 1) (4, 1) (5, 1) (6, 1) (7, 1) (7, 2) (7, 3) (7, 4)" +
            " (7, 5) (7, 6) (7, 7)"));
    }


    // ~ Private methods .......................................................

    // ----------------------------------------------------------
    /**
     * Simulates touching down on the middle of the specified cell in the maze.
     */
    private void touchDownCell(int x, int y)
    {
        touchDown(shapeView, (x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates moving the finger instantaneously to the middle of the
     * specified cell in the maze.
     */
    private void touchMoveCell(int x, int y)
    {
        touchMove((x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates clicking the middle of the specified cell in the maze. This is
     * equivalent to calling: touchDownCell(x, y); touchUp();
     */
    private void clickCell(int x, int y)
    {
        touchDownCell(x, y);
        touchUp();
    }
}
