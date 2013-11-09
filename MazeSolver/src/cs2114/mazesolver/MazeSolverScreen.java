package cs2114.mazesolver;

import android.graphics.RectF;
import sofia.graphics.OvalShape;
import sofia.graphics.Color;
import sofia.graphics.ShapeView;
import android.widget.TextView;
import sofia.graphics.RectangleShape;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * This is the "controller" class for MazeSolver. It communicates with a view to
 * display the maze to the user. It also handles touch events for when the user
 * is building the maze to be solved.
 *
 * @author Benjamin Kodres-O'Brien (bkobrien)
 * @version 2013.10.25
 */
public class MazeSolverScreen
    extends ShapeScreen
{
    // -------------------------------------------------------------------------

    // ~ Fields ................................................................
    private Maze               maze;
    private ShapeView          shapeView;
    private TextView           infoLabel;
    private Mode               mode;
    private RectangleShape[][] shapes;
    private OvalShape          goal;
    private OvalShape          start;


    // ~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Initializes the shape view such that it creates a maze with all
     * unexplored cells, to start.
     */
    public void initialize()
    {
        maze = new Maze(8);
        shapes = new RectangleShape[8][8];
        int sideLength = Math.min(shapeView.getWidth(), shapeView.getHeight());
        int cellSide = sideLength / 8;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                RectangleShape cell =
                    new DefaultCell(i * cellSide, j * cellSide, (i + 1)
                        * cellSide, (j + 1) * cellSide);
                shapes[i][j] = cell;
                shapeView.add(cell);
            }
        }
        mode = Mode.DRAW;
        infoLabel.setText("You have not finished making the maze yet!");
        start = new OvalShape(new RectF(0, 0, cellSide, cellSide));
        goal =
            new OvalShape(new RectF(
                (maze.size() - 1) * cellSide,
                (maze.size() - 1) * cellSide,
                maze.size() * cellSide,
                maze.size() * cellSide));
        start.setFillColor(Color.black);
        goal.setFillColor(Color.red);
        shapeView.add(start);
        shapeView.add(goal);
        maze.addObserver(this);
    }


    /**
     * An event responder to a touch down on the screen.
     *
     * @param x
     *            The x-coordinate of the touch.
     * @param y
     *            The y-coordinate of the touch.
     */
    public void onTouchDown(float x, float y)
    {
        int cell = Math.min(shapeView.getWidth(), shapeView.getHeight()) / 8;
        int i = (int)(x / cell);
        int j = (int)(y / cell);
        if (mode == Mode.DRAW)
        {
            maze.setCell(new Location(i, j), MazeCell.WALL);
            if (maze.getCell(new Location(i, j)) == MazeCell.WALL)
            {
                shapes[i][j].setFillColor(Color.gray);
            }
        }
        else if (mode == Mode.ERASE)
        {
            maze.setCell(new Location(i, j), MazeCell.UNEXPLORED);
            shapes[i][j].setFillColor(Color.white);
        }
        else if (mode == Mode.SET_START)
        {
            start.remove();
            shapes[i][j].setFillColor(Color.white);
            maze.setStartLocation(new Location(i, j));
            try
            {
                start.setBounds(shapes[i][j].getBounds());
            }
            catch (Exception e)
            {
                // Do nothing
            }
            shapeView.add(start);
        }
        else
        {
            goal.remove();
            shapes[i][j].setFillColor(Color.white);
            maze.setGoalLocation(new Location(i, j));
            try
            {
                goal.setBounds(shapes[i][j].getBounds());
            }
            catch (Exception e)
            {
                // Do nothing
            }
            shapeView.add(goal);
        }
    }


    /**
     * An event responder to a moving touch.
     *
     * @param x
     *            The x-coordinate of the current touch.
     * @param y
     *            The y-coordinate of the current touch.
     */
    public void onTouchMove(float x, float y)
    {
        int cell = Math.min(shapeView.getWidth(), shapeView.getHeight()) / 8;
        int i = (int)(x / cell);
        int j = (int)(y / cell);
        if (mode == Mode.DRAW)
        {
            maze.setCell(new Location(i, j), MazeCell.WALL);
            if (maze.getCell(new Location(i, j)) == MazeCell.WALL)
            {
                shapes[i][j].setFillColor(Color.gray);
            }
        }
        else if (mode == Mode.ERASE)
        {
            maze.setCell(new Location(i, j), MazeCell.UNEXPLORED);

            shapes[i][j].setFillColor(Color.white);

        }
        else if (mode == Mode.SET_START)
        {
            start.remove();
            shapes[i][j].setFillColor(Color.white);
            maze.setStartLocation(new Location(i, j));
            try
            {
                start.setBounds(shapes[i][j].getBounds());
            }
            catch (Exception e)
            {
                // Do nothing
            }
            shapeView.add(start);
        }
        else
        {
            goal.remove();
            shapes[i][j].setFillColor(Color.white);
            maze.setGoalLocation(new Location(i, j));
            try
            {
                goal.setBounds(shapes[i][j].getBounds());
            }
            catch (Exception e)
            {
                // Do nothing
            }
            shapeView.add(goal);
        }
    }


    /**
     * Solves the maze and puts the correct colors in each cell of the maze.
     */
    public void solveClicked()
    {
        String solution = maze.solve();
        if (solution != null)
        {
            infoLabel.setText(solution);
        }
        else
        {
            infoLabel.setText("No solution was possible.");
        }
        for (int i = 0; i < maze.size(); i++)
        {
            for (int j = 0; j < maze.size(); j++)
            {
                ILocation current = new Location(i, j);
                if (maze.getCell(current) == MazeCell.CURRENT_PATH)
                {
                    shapes[i][j].setFillColor(Color.green);
                }
                else if (maze.getCell(current) == MazeCell.FAILED_PATH)
                {
                    shapes[i][j].setFillColor(Color.red);
                }
            }
        }

    }


    /**
     * Responds to the Draw Button being clicked.
     */
    public void drawWallsClicked()
    {
        mode = Mode.DRAW;
    }


    /**
     * Responds to the Erase Button being clicked.
     */
    public void eraseWallsClicked()
    {
        mode = Mode.ERASE;
    }


    /**
     * Responds to the Goal Button being clicked.
     */
    public void setGoalClicked()
    {
        mode = Mode.SET_GOAL;
    }


    /**
     * Responds to the Start Button being clicked.
     */
    public void setStartClicked()
    {
        mode = Mode.SET_START;
    }


    /**
     * Gets the maze.
     *
     * @return the maze
     */
    public Maze getMaze()
    {
        return maze;
    }


    /**
     * Gets the current mode.
     *
     * @return the mode
     */
    public Mode getMode()
    {
        return mode;
    }
}
