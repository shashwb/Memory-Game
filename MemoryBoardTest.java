package cs2114.memorygame;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author AndrewK
 * @version Apr 28, 2014
 */

public class MemoryBoardTest
    extends student.TestCase
{
    /**
     * The MemoryBoard field.
     */
    MemoryBoard testMemBoard;


    // ----------------------------------------------------------
    /**
     * initializes the testMemBoard field.
     */
    protected void setUp()

    {
        // Gives the field a size of 2 and 1 card to find.
        testMemBoard = new MemoryBoard(2, 1);
    }


    /**
     * Test method for getMemoryCell, while also testing the setMemoryCell
     * method. Tests to see if every type of cell can be accessed (covered,
     * clicked, memorized, wrong, and invalid).
     */
    public void testGetMemoryCell()
    {
        testMemBoard.setMemoryCell(1, 1, MemCard.covered);
        assertEquals(MemCard.covered, testMemBoard.getMemoryCell(1, 1));

        testMemBoard.setMemoryCell(1, 1, MemCard.clicked);
        assertEquals(MemCard.clicked, testMemBoard.getMemoryCell(1, 1));

        testMemBoard.setMemoryCell(1, 1, MemCard.memorized);
        assertEquals(MemCard.memorized, testMemBoard.getMemoryCell(1, 1));

        testMemBoard.setMemoryCell(1, 1, MemCard.wrong);
        assertEquals(MemCard.wrong, testMemBoard.getMemoryCell(1, 1));
        // Tests to see if getting a MemCard "off" the board returns an invalid
        // card.
        assertEquals(MemCard.invalid, testMemBoard.getMemoryCell(5, 5));
    }


    /**
     * Test method for size().
     */
    public void testSize()
    {
        assertEquals(2, testMemBoard.size());
    }


    /**
     * Test method for {@link cs2114.memorygame.MemoryBoard#isGameLost()}.
     */
    public void testIsGameLost()
    {
        testMemBoard.setMemoryCell(1, 1, MemCard.covered);
        testMemBoard.uncoverCard(1, 1);
        assertTrue(testMemBoard.isGameLost());
    }


    /**
     * Test method for {@link cs2114.memorygame.MemoryBoard#isGameWon()}.
     */
    public void testIsGameWon()
    {

        testMemBoard.setMemoryCell(1, 1, MemCard.covered);
        testMemBoard.setMemoryCell(0, 0, MemCard.memorized);
        testMemBoard.setMemoryCell(0, 1, MemCard.covered);
        testMemBoard.setMemoryCell(1, 0, MemCard.covered);
        testMemBoard.uncoverCard(0, 0);
        assertTrue(testMemBoard.isGameWon());
    }

}
