package pacman;

/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze and, for each
 * position in the maze, whether it is passable or not.
 *
 * @immutable
 *
 * @invar This object's width and height cannot be less than 1.
 * 		| getWidth() > 0 && getHeight() > 0
 * @invar This object's length of passable should be equal to multiplication of width and height.
 * 		| passable.length() == getWidth() * getHeight()
 */
public class MazeMap {

	/**
	 * @invar | getWidth() > 0
	 * @invar | getHeight() > 0
	 * @invar | passable.length() == getWidth() * getHeight()
	 */
	private final int width;
	private final int height;
	private final boolean[] passable;

	/**
	 * Returns the width (i.e. the number of columns) of this maze map.
	 *
	 * @basic
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height (i.e. the number of rows) of this maze map.
	 *
	 * @basic
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 *
	 * @pre | rowIndex >= 0 && rowIndex + 1 <= this.height
	 * @pre | columnIndex >= 0 && columnIndex + 1 <= this.width
	 *
	 * @throws IllegalArgumentException if given row index or column index go beyond maze dimensions
	 * 		| rowIndex + 1 > this.height || columnIndex + 1 > this.width
	 *
	 * @return whether square at given row index and column index is passable.
	 * 		| isPassable[rowIndex, columnIndex] == passable[rowIndex * width + columnIndex]
	 */
	public boolean isPassable(int rowIndex, int columnIndex) {
		if (rowIndex + 1 > this.height || columnIndex + 1 > this.width) {
			throw new IllegalArgumentException("Wrong indexes!");
		}

		return passable[rowIndex * width + columnIndex];
	}

	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the possibility of the maze positions in the first row of the maze).
	 *
	 * @pre | width > 0
	 * @pre | height > 0
	 * @pre | passable != null && passable.length == width * height
	 *
	 * @post This object's width equal the given width.
	 * 		| getWidth() == width
	 * @post This object's height equal the given height.
	 * 		| getHeight() == height
	 * @post This object's passable equal the given passable.
	 * 		| this.passable == passable
	 *
	 * @throws IllegalArgumentException if the length of given array of booleans is less than multiplication of width
	 * and height.
	 * 		| passable.length() == getWidth() * getHeight()
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		this.width = width;
		this.height = height;

		if (passable.length != this.width * this.height) {
			throw new IllegalArgumentException("Wrong length of the array!");
		}

		this.passable = passable;
	}
}
