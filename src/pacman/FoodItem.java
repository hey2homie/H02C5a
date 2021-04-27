package pacman;

/**
 * @mutable
 */
public abstract class FoodItem {

    /**
     * @invar | square != null
     * @invar | size > 0
     */
    private final Square square;
    protected int size;

    /**
     * Returns object of Square class.
     *
     * @basic
     */
    public Square getSquare() {
        return square;
    }

    /**
     * Returns this object size.
     *
     * @basic
     */
    public int getSize() {
        return size;
    }

    /**
     * Initializes this object so that it represents a dot at maze square at given square.
     *
     * @pre | square != null
     *
     * @throws IllegalArgumentException if given square is null.
     * 		| square == null
     *
     * @post | getSquare() == square
     */
    public FoodItem(Square square) {
        if (square == null) {
            throw new IllegalArgumentException("Square cannot be null");
        }
        this.square = square;
    }

    public abstract boolean isPowerPellet();
}
