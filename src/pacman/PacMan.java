package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 *
 * @mutable
 */
public class PacMan {

	/**
	 * @invar |
	 * @invar |
	 */
	private int nbLives;
	private Square square;

	/**
	 * Returns
	 *
	 * @basic
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * Returns this object number of lives.
	 *
	 * @basic
	 */
	public int getNbLives() {
		return nbLives;
	}

	/**
	 *
	 *
	 * @pre | nbLives > 1
	 * @pre | square != null
	 *
	 * @throws IllegalArgumentException if method's nbLives parameter is less then 1 or square is null.
	 */
	public PacMan(int nbLives, Square square) {
		if (nbLives < 1 || square == null ) {
			throw new IllegalArgumentException("Wrong parameters");
		}

		this.nbLives = nbLives;
		this.square = square;
	}

	/**
	 * Sets this object square.
	 *
	 * @mutates | this.square
	 */
	public void setSquare(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("Square is null");
		}

		this.square = square;
	}

	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 *
	 * @mutates | this.nbLives
	 *
	 * @post | getNbLives() == getNbLives() - 1
	 */
	public void die() {
		this.nbLives -= 1;
	}

}
