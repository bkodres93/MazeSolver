package cs2114.mazesolver;

import sofia.graphics.Color;
import sofia.graphics.RectangleShape;

// -------------------------------------------------------------------------
/**
 * This is the visual representation of a default cell on the maze. It is white
 * and has a black background.
 *
 * @author Benjamin Kodres-O'Brien (bkobrien)
 * @version 2013.10.10
 */

public class DefaultCell
    extends RectangleShape
{

    // ----------------------------------------------------------
    /**
     * Create a new DefaultCell object.
     *
     * @param left
     *            The left bound
     * @param top
     *            The top bound
     * @param right
     *            The right bound
     * @param bottom
     *            The bottom bound
     */
    public DefaultCell(float left, float top, float right, float bottom)
    {
        super(left, top, right, bottom);
        this.setFillColor(Color.white);
        this.setColor(Color.black);
    }

}