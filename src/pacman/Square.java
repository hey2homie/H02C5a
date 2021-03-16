package pacman;

import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 *
 * @immutable
 */
public class Square {

	/**
	 * @invar | map != null
	 * @invar
	 * @invar
	 * @invar
	 */
	private final MazeMap map;
	private final int rowIndex;
	private final int columnIndex;
	private final boolean passable;

	/**
	 * Returns instance (!!! OR OBJECT? !!!) of the MazeMap class.
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
	public boolean isPassable() { return map.isPassable(rowIndex, columnIndex); }

	/**
	 *
	 *
	 */
	public Square(MazeMap map, int rowIndex, int columnIndex) {
		this.map = map;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.passable = map.isPassable(this.rowIndex, this.columnIndex);
	}

	/**
	 *
	 *
	 */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		// I believe that this method should return newly created instance of Square, but not sure yet
		return new Square(mazeMap, rowIndex, columnIndex);
	}

	/**
	 * Returns this square's neighbor in the given direction. If this square has no neighbor in the given direction,
	 * return the square that is furthest away in the opposite direction.
	 */
	// No formal documentation required
	public Square getNeighbor(Direction direction) {
		// Implementation hint: use method java.lang.Math.floorMod.
		// Not sure if it's correct until everything is done and we can run the application.
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
					square = Square.of(map, rowIndex - 1, columnIndex);
				}
			}
		}

		return square;

	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) {

		boolean canMove;

		switch (direction) {
			case RIGHT -> { canMove = map.isPassable(rowIndex, columnIndex + 1); }
			case LEFT -> { canMove = map.isPassable(rowIndex, columnIndex - 1); }
			case UP -> { canMove = map.isPassable(rowIndex + 1, columnIndex - 1); }
			case DOWN -> { canMove = map.isPassable(rowIndex - 1, columnIndex - 1); }
			default -> throw new IllegalStateException("Unexpected value: " + direction);
		}

		return canMove;
	}

	/**
	 * Returns the directions that are different from the given excluded direction and such that the neighbor in that
	 * direction is passable. The returned array shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index
	 * as this square.
	 */
	public boolean equals(Square other) { return other.equals(Square.of(map, rowIndex, columnIndex)); }
	
}
