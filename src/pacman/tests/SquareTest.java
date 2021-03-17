package pacman.tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pacman.Direction;
import pacman.MazeMap;
import pacman.Square;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    MazeMap map = new MazeMap(3, 3,
        new boolean[] {
            false, true, true,
            true, true, true,
            true, false, true
        }
    );

    Square square = new Square(map, 0, 0);
    Square rightSquare = new Square(map, 0, 1);
    Square leftSquare = new Square(map, 0, 2);
    Square identicalSquare = new Square(map, 0, 0);
    Square squareMiddle = new Square(map, 1, 1);

    @Test
    void getMazeMap() {
        assertEquals(map, square.getMazeMap());
    }

    @Test
    void getRowIndex() {
        assertEquals(0, square.getRowIndex());
    }

    @Test
    void getColumnIndex() {
        assertEquals(0, square.getColumnIndex());
    }

    @Test
    void isPassable() {
        assertFalse(square.isPassable());
    }

    @Test
    void of() {
        assertTrue(rightSquare.equals(Square.of(map, 0, 1)));
    }

    @Test
    void getNeighbor() {
        assertTrue(rightSquare.equals(square.getNeighbor(Direction.RIGHT)));
    }

    @Test
    void canMove() {
        assertTrue(square.canMove(Direction.LEFT));
        assertTrue(square.canMove(Direction.RIGHT));
        assertTrue(square.canMove(Direction.UP));
        assertTrue(square.canMove(Direction.DOWN));
        assertFalse(leftSquare.canMove(Direction.RIGHT));
    }

    @Test
    void getPassableDirectionsExcept() {
        Assert.assertArrayEquals(new Direction[]{Direction.DOWN, Direction.UP},
                leftSquare.getPassableDirectionsExcept(Direction.LEFT));
    }

    @Test
    void testEquals() {
        assertTrue(square.equals(identicalSquare));
    }
}