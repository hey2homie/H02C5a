package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze.
 *
 * @mutable
 *
 * @invar |
 * @invar |
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
	 *
	 * @pre |
	 * @pre |
	 *
	 * @post |
	 * @post |
	 *
	 * @throws IllegalArgumentException
	 */
	public Ghost(Square square, Direction direction) {
		if (square == null || direction == null) {
			throw new IllegalArgumentException("Wrong parameters");
		}

		this.square = square;
		this.direction = direction;
	}

	/**
	 *
	 *
	 * @pre | square != null
	 *
	 * @mutates | this.square
	 *
	 * @post | getSquare() == square
	 *
	 * @throws IllegalArgumentException
	 */
	public void setSquare(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("Wrong parameters");
		}

		this.square = square;
	}

	/**
	 *
	 *
	 * @pre | direction != null
	 *
	 * @mutates | this.direction
	 *
	 * @post | getDirection() == direction
	 *
	 * @throws IllegalArgumentException
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
