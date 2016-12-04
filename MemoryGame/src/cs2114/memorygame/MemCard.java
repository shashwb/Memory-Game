package cs2114.memorygame;

/**
 * // -------------------------------------------------------------------------
/**
 * This is an enumerated type Card which represents the different kinds of
 * cards that will be used in the game.
 * Each time is explained by the Javadoc that proceeds it.
 *
 *  @author Chris Cornett
 *  @author Seth Balodi
 *  @author Andrew Knittle
 *  @version Apr 13, 2014
 */

public enum MemCard
{
    /**
     * a covered tile.
     */
    covered("covered"),
    /**
     * a covered tile that must be clicked.
     */
    memorized("memorized"),
    /**
     * a correctly clicked tile.
     */
    clicked("clicked"),
    /**
     * a wrongly clicked tile
     */
    wrong("wrong"),
    /**
     * cell not in the thing
     */
    invalid("invalid");

    /**
     * code used in the random placement of memory cards
     */
    public static int coveredSquares;
    private final String name;

    MemCard(String name)
    {
        this.name = name;
    }

    /**
     * gets the name of the enumerated type.
     * @return the name of the enumerated type.
     */
    public String getName()
    {
        return name;
    }
}
