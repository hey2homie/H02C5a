package pacman.tests;

import org.junit.jupiter.api.Test;
import pacman.Dot;
import pacman.MazeMap;
import pacman.Square;

import static org.junit.jupiter.api.Assertions.*;

class DotTest {

    MazeMap map = new MazeMap(3, 3,
            new boolean[] {
                    false, true, true,
                    true, true, true,
                    true, false, true
            }
    );

    Square square = new Square(map, 0, 0);
    Dot dot = new Dot(square);

    @Test
    void getSquare() {
        assertEquals(square, dot.getSquare());
    }
}