package pacman;

import java.util.stream.IntStream;

/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze and, for each
 * position in the maze, whether it is passable or not.
 *
 * @immutable
 *
 * @invar This object's width and height cannot be less than 1.
 * 		| getWidth() >= 0 && getHeight() >= 0
 */
public class MazeMap {

	/**
	 * @invar | width >= 0
	 * @invar | height >= 0
	 * @invar | passable != null
	 * @invar | passable.length == width * height
	 *
	 * @representationObject
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
	 * @inspects | this
	 *
	 * @throws IllegalArgumentException if given row index is beyond maze's height dimensions.
	 * 		| rowIndex + 1 > getHeight() || rowIndex < 0
	 * @throws IllegalArgumentException if given column index is beyond maze's width dimension.
	 * 		| columnIndex + 1 > getWidth() || columnIndex < 0
	 *
	 * @basic
	 */
	public boolean isPassable(int rowIndex, int columnIndex) {
		if (rowIndex + 1 > getHeight() || rowIndex < 0) {
			throw new IllegalArgumentException("Row index is wrong");
		}
		if (columnIndex + 1 > getWidth() || columnIndex < 0) {
			throw new IllegalArgumentException("Column index is wrong");
		}

		return passable[rowIndex * width + columnIndex];
	}

	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the possibility of the maze positions in the first row of the maze).
	 *
	 * @inspects | passable
	 *
	 * @throws IllegalArgumentException if the length of given array of booleans is less than multiplication of width
	 * and height.
	 * 		| passable.length != width * height || passable == null
	 * @throws IllegalArgumentException if width less than 0.
	 * 		| width < 0
	 * @throws IllegalArgumentException if height less than 0.
	 * 		| height < 0
	 *
	 * @post | getWidth() == width
	 * @post | getHeight() == height
	 * @post | IntStream.range(0, height).allMatch(rowIndex -> IntStream.range(0, width).allMatch(columnIndex ->
	 *       |         isPassable(rowIndex, columnIndex) == passable[rowIndex * width + columnIndex]))
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		if (width < 0) {
			throw new IllegalArgumentException("Maze's width cannot be less than 1");
		}
		if (height < 0) {
			throw new IllegalArgumentException("Maze's height cannot be less than 1");
		}
		if (passable.length != width * height) {
			throw new IllegalArgumentException("Wrong length of the array");
		}

		this.width = width;
		this.height = height;
		this.passable = passable.clone();
	}
}