package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze.
 *
 * @mutable
 *
 * @invar This object's square cannot be null.
 * 		| getSquare() != null
 * @invar This object's direction cannot be null.
 * 		| getDirection() != null
 */
public class Ghost {

	private Square square;
	private Direction direction;

	/**
	 * Returns location of this ghost.
	 *
	 * @basic
	 */
	public Square getSquare() {
		return square;
	}
	
	/**
	 * Returns the direction in which this ghost will preferably move next.
	 *
	 * @basic
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Initializes this object so that it represents a ghost in the maze at the given square with the initial direction.
	 *
	 * @pre | square != null
	 * @pre | direction != null
	 *
	 * @throws IllegalArgumentException if given square is null.
	 * 		| square == null
	 * @throws IllegalArgumentException if given direction is null.
	 * 		| direction == null
	 *
	 * @post | square == getSquare()
	 * @post | direction == getDirection()
	 */
	public Ghost(Square square, Direction direction) {
		if (square == null || direction == null) {
			throw new IllegalArgumentException("Wrong parameters");
		}

		this.square = square;
		this.direction = direction;
	}

	/**
	 * Sets this object's square.
	 *
	 * @pre | square != null
	 *
	 * @mutates | this
	 *
	 * @throws IllegalArgumentException if method's argument is null.
	 * 		| square == null
	 *
	 * @post | getSquare() == square
	 */
	public void setSquare(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("Wrong parameters");
		}

		this.square = square;
	}

	/**
	 * Set's this object's direction.
	 *
	 * @pre | direction != null
	 *
	 * @mutates | this
	 *
	 * @throws IllegalArgumentException if method's argument is null.
	 *
	 * @post | getDirection() == direction
	 */
	public void setDirection(Direction direction) {
		if (direction == null) {
			throw new IllegalArgumentException("Wrong parameters");
		}

		this.direction = direction;
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal document required
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(getDirection()) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}
	
	// No formal document required
	public void move(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}
}
