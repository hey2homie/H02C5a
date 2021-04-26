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

	/**
	 * @invar | square != null
	 * @invar | direction != null
	 */
	private Square square;
	private Direction direction;
	private GhostState ghostState;
	private final Square originalSquare;

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
	 * Returns initial square on which ghost is located.
	 *
	 * @basic
	 */
	public Square getOriginalSquare() {
		return originalSquare;
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
		if (square == null) {
			throw new IllegalArgumentException("Square cannot be null");
		}
		if (direction == null) {
			throw new IllegalArgumentException("Direction cannot be null");
		}

		this.square = square;
		this.direction = direction;
		this.ghostState = new RegularGhostState();
		this.originalSquare = square;
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
			throw new IllegalArgumentException("Wrong parameter");
		}

		this.direction = direction;
	}

	/**
	 * Set's this object state.
	 *
	 * @mutates | this
	 *
	 * @throws IllegalArgumentException if method's argument is null.
	 */
	public void setGhostState(GhostState ghostState) {
		if (ghostState == null) {
			throw new IllegalArgumentException("Wrong parameter");
		}

		this.ghostState = ghostState;
	}

	/**
	 * Returns boolean value depending on the ghost state: true if ghost in vulnerable state or false otherwise.
	 */
	public boolean isVulnerable() {
		// TODO: Change use of instanceof to dynamical binding
		return ghostState instanceof VulnerableGhostState;
	}

	/**
	 * This method is called when PacMan ate power pellet resulting in changing ghost state to vulnerable state and
	 * reversing the direction of movement.
	 *
	 * @mutates | this
	 */
	public void pacManAtePowerPellet() {
		setGhostState(new VulnerableGhostState());

		switch (this.direction) {
			case LEFT -> this.direction = Direction.RIGHT;
			case RIGHT -> this.direction = Direction.LEFT;
			case DOWN -> this.direction = Direction.UP;
			case UP -> this.direction = Direction.DOWN;
		}
	}

	/**
	 * This method is called upon ghost's collision with PacMan. Depending on the state of ghost PacMan either looses a
	 * health, or PacMan eats ghost resulting in ghost changing state to regular state and spawning again at the
	 * original square.
	 */
	public void hitBy(PacMan pacMan) {
		setGhostState(ghostState.hitBy(this, pacMan));
	}

	public Direction chooseNextMoveDirection(Random random) {
		int MOVE_FORWARD_PREFERENCE = 10;
		int moveForwardPreference = getSquare().canMove(getDirection()) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}

	public void reallyMove(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}

	public void move(Random random) {
		reallyMove(random);
	}
}
