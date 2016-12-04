package cs2114.memorygame;

import sofia.util.Random;

// -------------------------------------------------------------------------
/**
 * This class serves as the Model of our project. All the logic needed for our
 * project is found here. We make an empty MemoryBoard and fill in each
 * coordinate with an appropriate type of MemCard. This is similar to how the
 * MazeSolver project worked by making an empty board (a maze board) and filling
 * in values.
 *
 * @author Seth Balodi
 * @author Chris Cornett
 * @author Andrew Knittle
 * @version Apr 16, 2014
 */
public class MemoryBoard
{

    // An array of MemCards that will form the "board". Each coordinate will be
    // filled in with a type of MemCard.
    private MemCard[][] playBoard;
    // The size of the board (ie: a board that is 3x3 or 4x4 etc.)
    private int         size;
    // This field represents the number of cards that need to memorized.
    private int         numMem;
    // A boolean that exists to determine if the game is lost.
    private boolean     cardUncovered;
    // A field to represents the number of memorized cards found correctly.
    private int         counter;


    // ----------------------------------------------------------
    /**
     * Creates a new MemoryBoard object.
     *
     * @param size
     *            the size of the board
     * @param numMemory
     *            the number of memory cards needed to be memorized.
     */
    public MemoryBoard(int size, int numMemory)
    {
        numMem = numMemory;
        this.size = size;
        // If cardUncovered was initialized as true there would be no way to win
        // the game.
        cardUncovered = false;
        // Simply fills in the double array, or the "board", with covered
        // MemCards.
        playBoard = new MemCard[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {

                playBoard[i][j] = MemCard.covered;
            }
        }
        // Sets randomly where the memorized MemCards should be placed.
        Random rand = new Random();
        int coveredSquares = 0;
        // While the counter is less than the number numMemory it places the
        // cards randomly placed.
        while (coveredSquares < numMemory)
        {

            int x = rand.nextInt(size);
            int y = rand.nextInt(size);

            if (getMemoryCell(x, y) == MemCard.covered)
            {
                playBoard[x][y] = MemCard.memorized;
                coveredSquares++;
            }

        }

    }


    // ----------------------------------------------------------
    /**
     * A getter method that gets the MemCard at the coordinates placed into the
     * input.
     *
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @return returns a MemCard value from a certain coordinate or an invalid
     *         MemCard.
     */
    public MemCard getMemoryCell(int x, int y)
    {

        if (0 <= x && x < size && 0 <= y && y < size)
        {
            return playBoard[x][y];
        }
        else
        {
            // If the coordinates put into the input are outside the "board" an
            // invalid MemCard is returned rather than an out of bounds
            // exception.
            return MemCard.invalid;
        }
    }


    // ----------------------------------------------------------
    /**
     * Sets the coordinates, the integer inputs, to a certain MemCard which is
     * the third input in this method. Functions similarly to the getter method
     * above.
     *
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param card
     *            the card on the memory board
     */
    protected void setMemoryCell(int x, int y, MemCard card)
    {
        // Simple check to make sure card being set is within the bounds.
        // Similar in the functionality of the getter method.
        if (x >= 0 && x < size && y >= 0 && y < size)
        {
            playBoard[x][y] = card;
        }

    }


    // ----------------------------------------------------------
    /**
     * A simple method to see if the game has been lost by checking to see if
     * the field cardUncovered is true or not. If cardUncovered is true the game
     * has been lost, likewise if it is false the game has NOT been lost
     *
     * @return cardUncovered which is the boolean field to see if the game is
     *         lost or not.
     */
    public boolean isGameLost()
    {

        return cardUncovered;
    }


    // ----------------------------------------------------------
    /**
     * Checks to see if the game is won by seeing if the counter field is
     * equivalent to the number memorized cards places.
     *
     * @return false or true depending on which conditions are met.
     */
    public boolean isGameWon()
    {

        if (counter == numMem)
        {
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * This method returns the size of the game board
     *
     * @return size the size of the game board
     */
    public int size()
    {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * This is the method that either ups the counter field by one if a
     * memorized card is correctly guessed or sets a wrongly selected card to
     * "wrong". If a card is not correctly chosen then cardUncovered is set to
     * true, this would mean if the method isGameLost() is run the game would be
     * lost. On the other hand if the counter has been increased to the point
     * that it's equal to the number of memory cards placed and then running the
     * isGameWon() method would return true, thereby winning the game.
     *
     * @param x
     *            represents the x coordinate of the grid.
     * @param y
     *            represents the y coordinate of the grid.
     */
    public void uncoverCard(int x, int y)
    {

        // Checks to see if the card chosen is a memorized card.
        if (getMemoryCell(x, y) == MemCard.memorized)
        {
            // If this if statement passes the card chosen is set to clicked and
            // the counter is increased by one.
            playBoard[x][y] = MemCard.clicked;
            counter++;
        }
        if (getMemoryCell(x, y) == MemCard.covered)
        {
            // If this if statement passes the card chosen is set to wrong and
            // the field cardUncovered is set to true.
            playBoard[x][y] = MemCard.wrong;
            cardUncovered = true;
        }

    }

}
