package pacman;

/**
 * @mutable
 */
public class FoodItem {

    /**
     * @invar | square != null
     * @invar | size > 0
     */
    private final Square square;
    private int size;

    /**
     * Returns object of Square class.
     *
     * @basic
     */
    public Square getSquare() {
        return square;
    }

    /**
     * Returns this object Size
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
        this.size = 1;
        this.square = square;
    }

    /**
     * @pre | size > 0
     *
     * @throws IllegalArgumentException | size < 1
     *
     * post | getSize() == size
     */
    public void setSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size cannot be less than 1");
        }

        this.size = size;
    }
}
