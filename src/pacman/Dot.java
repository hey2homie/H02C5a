package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 *
 * @immutable
 *
 * @invar | This
 */
public class Dot {

	/**
	 * @invar | square != null
	 */
	private final Square square;

	/**
	 * Returns object of Square class.
	 *
	 * @basic
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * Initializes this object so that it represents a dot at maze square.
	 *
	 * @pre | square != null
	 *
	 * @post | getSquare == square
	 */
	public Dot(Square square) {
		this.square = square;
	}

}
