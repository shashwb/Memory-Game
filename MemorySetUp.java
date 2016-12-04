package cs2114.memorygame;

import android.widget.TextView;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;
import sofia.app.ShapeScreen;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author AndrewK (andrk11)
 * @author Chris Cornett (chrispc)
 * @author Seth Balodi (shashwb@vt.edu)
 * @version Apr 13, 2014
 */
public class MemorySetUp
    extends ShapeScreen
{
    // The size of the board (cell size, ie: 2x2, 4x4, etc.)
    /**
     * The size of the board.
     */
    public int          size;
    // The size in pixels of the cells as they appear on screen.
    /**
     * dimension in pixels of the size of the cell on the screen.
     */
    public float        cellDim;
    // The boolean field to tell if a card that needs to be memorized by the
    // user is at a cell.
    private int         redCards;
    // This field represents the MemoryBoard class. This is so we can use the
    // data from the MemoryBoard and translate the data to a visual
    // representation
    // for the user. This is following MVC method.
    private MemoryBoard memBoard;
    // Refers to the TextView located in the xml with the same ID name. The text
    // is updated to correspond what is occurring in the game.
    private TextView    infoLabel;


    public void initialize()
    {
        // The width of the ShapeScreen.
        float x = getWidth();
        // The height of the ShapeScreen.
        float y = getHeight();
        // Checks to see which is smaller in pixel size, and stores the returned
        // value in square. Square is named as such because it will define the
        // square shape of the cells.
        float square = Math.min(x, y);
        redCards = 3;
        size = 6;
        this.memBoard = new MemoryBoard(size, 3);
        cellDim = square / size;
        this.infoLabel.setText("");
        for (int i = 0; i < size; i++)
        {
            float top = i * cellDim;
            float bot = (i + 1) * cellDim;
            for (int j = 0; j < size; j++)
            {
                float left = j * cellDim;
                float right = (j + 1) * cellDim;
                RectangleShape rect = new RectangleShape(left, top, right, bot);
                rect.setColor(Color.black);
                rect.setFillColor(Color.white);
                add(rect);
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * After clicking the start button the memBoard is initialized, and the
     * tiles required to be memorized are shoed after a short time.
     */
    public void startClicked()
    {
        memBoard = new MemoryBoard(size, redCards);
        for (int w = 0; w < size; w++)
        {
            for (int h = 0; h < size; h++)
            {
                MemCard tempMemCard = memBoard.getMemoryCell(w, h);
                if (tempMemCard.getName().equals("memorized"))
                {
                    float pointX = (w * cellDim);
                    float pointY = (h * cellDim);
                    RectangleShape pointcell =
                        getShapes().locatedAt(pointX, pointY)
                            .withClass(RectangleShape.class).front();
                    pointcell.animate(500).fillColor(Color.red).play();
                }
            }
        }
    }

 // ----------------------------------------------------------
    /**
     * After clicking the Show button and then clicking the Hide button hides
     * all the memorized cells. This changes the color from red to white.
     */
    public void hideClicked()
    {
        for (int w = 0; w < size; w++)
        {
            for (int h = 0; h < size; h++)
            {
                float pointX = (w  * cellDim);
                float pointY = (h  * cellDim);
                RectangleShape pointcell =
                    getShapes().locatedAt(pointX, pointY)
                        .withClass(RectangleShape.class).front();
                pointcell.animate(500).fillColor(Color.white).play();
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param x
     * @param y
     */
    public void processTouch(float x, float y)
    {
        int a = (int)(x / (Math.min(getWidth(), getHeight()) / size));
        int b = (int)(y / (Math.min(getWidth(), getHeight()) / size));
        if (memBoard.getMemoryCell(a, b) == MemCard.memorized)
        {
            // Changes the corresponding cell to red to show you've already
            // clicked on that cell.
            RectangleShape cell =
                getShapes().locatedAt(x, y).withClass(RectangleShape.class)
                    .front();
            cell.setFillColor(Color.red);
            // Checks to see if all the memorized cards had been found.
            memBoard.uncoverCard(a, b);
            if (memBoard.isGameWon())
            {
                infoLabel.setText("You've won");
            }
        }
        if (memBoard.getMemoryCell(a, b) == MemCard.covered)
        {
            memBoard.uncoverCard(a, b);
            if (memBoard.isGameLost())
            {
                infoLabel.setText("You suck!");
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * For when you drag you finger across the screen.
     *
     * @param x
     * @param y
     */
    public void onTouchMove(float x, float y)
    {
        processTouch(x, y);
    }
}
