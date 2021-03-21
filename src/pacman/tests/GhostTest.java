package pacman.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pacman.Direction;
import pacman.Ghost;
import pacman.MazeMap;
import pacman.Square;

import static org.junit.jupiter.api.Assertions.*;

class GhostTest {

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
    Ghost ghost = new Ghost(square, Direction.LEFT);

    @Test
    void getSquare() {
        assertEquals(square, ghost.getSquare());
    }

    @Test
    void getDirection() {
        assertEquals(Direction.LEFT, ghost.getDirection());
        Assertions.assertNotEquals(Direction.RIGHT, ghost.getDirection());
    }

    @Test
    void setSquare() {
        ghost.setSquare(rightSquare);
        assertEquals(rightSquare, ghost.getSquare());
        Assertions.assertNotEquals(leftSquare, ghost.getSquare());
    }

    @Test
    void setDirection() {
        ghost.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, ghost.getDirection());
        Assertions.assertNotEquals(Direction.UP, ghost.getDirection());
    }
}