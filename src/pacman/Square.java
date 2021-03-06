package pacman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 *
 * @immutable
 *
 * @invar This object's row index that cannot be less then 0 and more than maze height - 1.
 * 		| getRowIndex() >= 0 && getRowIndex() < getMazeMap().getHeight()
 * @invar This object's column index that cannot be less then 0 and more than maze width - 1.
 * 		| getColumnIndex() >= 0 && getColumnIndex() < getMazeMap().getWidth()
 * @invar This object's maze map cannot be null.
 * 		| getMazeMap() != null
 */
public class Square {

	/**
	 * @invar | map != null
	 * @invar | rowIndex >= 0 && rowIndex < map.getHeight()
	 * @invar | columnIndex >= 0 && columnIndex < map.getWidth()
	 */
	private final MazeMap map;
	private final int rowIndex;
	private final int columnIndex;
	private final boolean passable;

	/**
	 * Returns object of the MazeMap class.
	 *
	 * @basic
	 */
	public MazeMap getMazeMap() {
		return map;
	}

	/**
	 * Returns row index of this square.
	 *
	 * @basic
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * Returns column index of this square.
	 *
	 * @basic
	 */
	public int getColumnIndex() {
		return columnIndex;
	}

	/**
	 * Returns whether the square is passable.
	 *
	 * @basic
	 */
	public boolean isPassable() {
		return passable;
	}

	/**
	 * Initializes this object so that it represents a maze square at given rowIndex, columnIndex, and whether square is
	 * passable or not.
	 *
	 * @throws IllegalArgumentException if map is null.
	 * 		| map == null
	 * @throws IllegalArgumentException if row index is incorrect with regard to maze height or less than zero.
	 * 		| rowIndex < 0 || rowIndex > map.getHeight() - 1
	 * @throws IllegalArgumentException if column index is incorrect with regard to maze width or less than zero.
	 * 		| columnIndex < 0 || columnIndex > map.getWidth() - 1
	 *
	 * @post | getMazeMap() == map
	 * @post | getRowIndex() == rowIndex
	 * @post | getColumnIndex() == columnIndex
	 * @post | isPassable() == map.isPassable(rowIndex, columnIndex)
	 */
	public Square(MazeMap map, int rowIndex, int columnIndex) {
		if (map == null) {
			throw new IllegalArgumentException("Map is null");
		}
		if (rowIndex < 0 || rowIndex > map.getHeight() - 1) {
			throw new IllegalArgumentException("Row index cannot be less than zero and more then maze's height - 1");
		}
		if (columnIndex < 0 || columnIndex > map.getWidth() - 1) {
			throw new IllegalArgumentException("Column index cannot be less than zero and more then maze's width - 1");
		}

		this.map = map;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.passable = map.isPassable(this.rowIndex, this.columnIndex);
	}

	/**
	 * Returns newly created instance of Square class, which represents square of the maze at given rowIndex,
	 * columnIndex, and includes MazeMap instance.
	 *
	 * @creates | result
	 *
	 * @throws IllegalArgumentException if map is null.
	 * 		| mazeMap == null
	 * @throws IllegalArgumentException if row index is incorrect with regard to maze height or less than zero.
	 * 		| rowIndex < 0 || rowIndex > mazeMap.getHeight() - 1
	 * @throws IllegalArgumentException if column index is incorrect with regard to maze width or less than zero.
	 * 		| columnIndex < 0 || columnIndex > mazeMap.getWidth() - 1
	 *
	 * @post | result.getMazeMap() == mazeMap && result.getRowIndex() == rowIndex && result.getColumnIndex() == columnIndex
	 * @post | result != null
	 */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		return new Square(mazeMap, rowIndex, columnIndex);
	}

	/**
	 * Returns this square's neighbor in the given direction. If this square has no neighbor in the given direction,
	 * return the square that is furthest away in the opposite direction.
	 */
	public Square getNeighbor(Direction direction) {
		if (direction == null) {
			throw new IllegalArgumentException("Direction cannot be null");
		}

		Square square = null;

		switch (direction) {
			case RIGHT -> {
				if (columnIndex == map.getWidth() - 1) {
					square = Square.of(map, rowIndex, 0);
				} else {
					square = Square.of(map, rowIndex, columnIndex + 1);
				}
			}
			case LEFT -> {
				if (columnIndex == 0) {
					square = Square.of(map, rowIndex, map.getWidth() - 1);
				} else {
					square = Square.of(map, rowIndex, columnIndex - 1);
				}
			}
			case UP -> {
				if (rowIndex == 0) {
					square = Square.of(map, map.getHeight() - 1, columnIndex);
				} else {
					square = Square.of(map, rowIndex - 1, columnIndex);
				}
			}
			case DOWN -> {
				if (rowIndex == map.getHeight() - 1) {
					square = Square.of(map, 0, columnIndex);
				} else {
					square = Square.of(map, rowIndex + 1, columnIndex);
				}
			}
		}

		return square;
	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	public boolean canMove(Direction direction) {
		if (direction == null) {
			throw new IllegalArgumentException("Direction cannot be null");
		}

		return getNeighbor(direction).isPassable();
	}

	/**
	 * Returns the directions that are different from the given excluded direction and such that the neighbor in that
	 * direction is passable. The returned array shall have no null elements and shall have no duplicates.
	 */
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		if (excludedDirection == null) {
			throw new IllegalArgumentException("Direction cannot be null");
		}

		List<Direction> directions = new ArrayList<>();

		for (Direction direction : Direction.values()) {
			if (direction != excludedDirection && canMove(direction)) {
				directions.add(direction);
			}
		}

		return Arrays.copyOf(directions.toArray(new Direction[0]), directions.size());
	}

	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index
	 * as this square.
	 *
	 * @throws IllegalArgumentException | other == null
	 * 
	 * @post | result == (
	 *       |     getMazeMap() == other.getMazeMap() &&
	 *       |     getRowIndex() == other.getRowIndex() &&
	 *       |     getColumnIndex() == other.getColumnIndex()
	 *       | ) 
	 */
	public boolean equals(Square other) {
		if (other == null) {
			throw new IllegalArgumentException("Square cannot be null");
		}

		return other.getMazeMap() == getMazeMap() && other.getRowIndex() == getRowIndex()
				&& other.getColumnIndex() == getColumnIndex();
	}
}