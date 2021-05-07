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
	private int delay = 1;

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

	public Square getOriginalSquare() {
		return originalSquare;
	}

	public int getDelay() {
		return delay;
	}

	/**
	 * Initializes this object so that it represents a ghost in the maze at the given square with the initial direction.
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
	 * @throws IllegalArgumentException if method's argument is null.
	 * 		| square == null
	 *
 	 * @mutates | this
 	 * 
	 * @post | getSquare() == square
	 * @post | getDirection() == old(getDirection())
	 */
	public void setSquare(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("Wrong parameters");
		}

		this.square = square;
	}

	/**
	 * Set this object's direction.
	 *
	 * @throws IllegalArgumentException if method's argument is null.
	 * 		| direction == null
	 * 
 	 * @mutates | this
 	 * 
	 * @post | getDirection() == direction
	 * @post | getSquare() == old(getSquare())
	 */
	public void setDirection(Direction direction) {
		if (direction == null) {
			throw new IllegalArgumentException("Wrong parameter");
		}

		this.direction = direction;
	}

	public void setGhostState(GhostState ghostState) {
		if (ghostState == null) {
			throw new IllegalArgumentException("Wrong parameter");
		}

		this.ghostState = ghostState;
	}

	public void setDelay(int delay) {
		if (delay < 0) {
			throw new IllegalArgumentException("Wrong parameter");
		}

		this.delay = delay;
	}

	public void increaseDelay() {
		this.delay++;
	}

	public boolean isVulnerable() {
		return ghostState.isVulnerable();
	}

	public void pacManAtePowerPellet() {
		setGhostState(new VulnerableGhostState());
		this.direction = direction.getOpposite();
	}

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
		setGhostState(ghostState.move(this, random));
	}
}