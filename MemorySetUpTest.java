package cs2114.memorygame;

import sofia.graphics.RectangleShape;
import android.widget.Button;
import android.widget.TextView;
import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author AndrewK
 * @version Apr 28, 2014
 */

public class MemorySetUpTest
    extends student.AndroidTestCase<MemorySetUp>
{
    private ShapeView   shapeView;
    private TextView    infoLabel;
    private Button      start;

    private int         cellSize;
    private MemorySetUp testScreen;


    // ----------------------------------------------------------
    /**
     * Initiates the text fixtures.
     */
    public MemorySetUpTest()
    {
        super(MemorySetUp.class);
    }


    /**
     * Initializes the test fixtures.
     */
    public void setUp()
    {
        testScreen = this.getScreen();
        float viewSize = Math.min(shapeView.getWidth(), shapeView.getHeight());
        cellSize = (int)(viewSize / 2);
    }


    /**
     * test the startClicked() method.
     */
    public void testStartClicked()
    {
        click(start);

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
