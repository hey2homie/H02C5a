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
 * 		| getPassable().length == getWidth() * getHeight()
 */

public class MazeMap {

	/**
	 * @invar | width > 0
	 * @invar | height > 0
	 * @invar | passable.length == width * height
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
	 * Returns the array containing maze's passable/unpassable positions.
	 *
	 * @basic
	 */
	public boolean[] getPassable() {
		return passable;
	}

	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 *
	 * @inspects | this
	 *
	 * @pre | rowIndex >= 0 && rowIndex + 1 <= getHeight()
	 * @pre | columnIndex >= 0 && columnIndex + 1 <= getWidth()
	 *
	 * @throws IllegalArgumentException if given row index is beyond maze's height dimensions.
	 * 		| rowIndex + 1 > getHeight()
	 * @throws IllegalArgumentException if given column index is beyond maze's width dimension.
	 * 		| columnIndex + 1 > getWidth()
	 *
	 * @creates | result
	 *
	 * @post | result == getPassable()[rowIndex * getWidth() + columnIndex]
	 */
	public boolean isPassable(int rowIndex, int columnIndex) {
		if (rowIndex + 1 > getHeight() || rowIndex < 0) {
			throw new IllegalArgumentException("Row index is wrong");
		}
		if (columnIndex + 1 > getWidth() || columnIndex < 0) {
			throw new IllegalArgumentException("Column index is wrong");
		}

		boolean result = getPassable()[rowIndex * width + columnIndex];
		return result;
	}

	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the possibility of the maze positions in the first row of the maze).
	 *
	 * @inspects | passable
	 *
	 * @pre | width > 0
	 * @pre | height > 0
	 * @pre | passable != null && passable.length == width * height
	 *
	 * @throws IllegalArgumentException if the length of given array of booleans is less than multiplication of width
	 * and height.
	 * 		| getPassable().length != getWidth() * getHeight()
	 * @throws IllegalArgumentException if width less than 1.
	 * 		| width < 1
	 * @throws IllegalArgumentException if height less than 1.
	 * 		| height < 1
	 *
	 * @post This object's width equal the given width.
	 * 		| getWidth() == width
	 * @post This object's height equal the given height.
	 * 		| getHeight() == height
	 * @post This object's passable equal the given passable.
	 * 		| getPassable() == passable
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		if (width < 1) {
			throw new IllegalArgumentException("Maze's width cannot be less than 1");
		}
		if (height < 1) {
			throw new IllegalArgumentException("Maze's height cannot be less than 1");
		}
		if (passable.length != width * height) {
			throw new IllegalArgumentException("Wrong length of the array");
		}

		this.width = width;
		this.height = height;
		this.passable = passable;
	}
}