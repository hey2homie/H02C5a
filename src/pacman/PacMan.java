package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 *
 * @mutable
 *
 * @invar This object's nbLives cannot be less than zero.
 * 		| getNbLives() >= 0
 * @invar This object's square cannot be null.
 * 		| getSquare() != null
 */
public class PacMan {

	/**
	 * @invar | nbLives >= 0
	 * @invar | square != null
	 */
	private int nbLives;
	private Square square;

	/**
	 * Returns this object number of lives.
	 *
	 * @basic
	 */
	public int getNbLives() {
		return nbLives;
	}

	/**
	 * Returns this object's square.
	 *
	 * @basic
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * Initializes this object so that it represents a PacMan at the given square of the maze. Number of lives cannot
	 * be less than 1 because it'll result in immediate end of the game.
	 *
	 * @throws IllegalArgumentException if method's nbLives parameter is less then 1.
	 * 		| nbLives < 0
	 * @throws IllegalArgumentException if method's square parameter is null.
	 * 		| square == null
	 *
	 * @post This object's number of lives equals given number of lives.
	 * 		| getNbLives() == nbLives
	 * @post This object's square equals given square.
	 * 		| getSquare() == square
	 */
	public PacMan(int nbLives, Square square) {
		if (nbLives < 0) {
			throw new IllegalArgumentException("Number of lives cannot be less than 1");
		}
		if (square == null) {
			throw new IllegalArgumentException("Square cannot be null");
		}

		this.nbLives = nbLives;
		this.square = square;
	}

	/**
	 * Sets this object square.
	 *
	 * @throws IllegalArgumentException if given square equals null.
	 * 		| square == null
	 *
	 * @mutates | this
	 *
	 * @post | getSquare() == square
 	 * @post | getNbLives() == old(getNbLives()) 
	 */
	public void setSquare(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("Square cannot be null");
		}

		this.square = square;
	}

	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 *
	 *
	 * @throws IllegalArgumentException if number of lives is less than one
	 * 		| getNbLives() == 0
	 * 
	 * @mutates | this
	 * 
	 * @post | getNbLives() == old(getNbLives()) - 1
	 * @post | getSquare() == old(getSquare())
	 */
	public void die() {
		if (nbLives > 0) {
			this.nbLives--;
		} else {
			throw new IllegalArgumentException("There are no lives left");
		}
	}
}