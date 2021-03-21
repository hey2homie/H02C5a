package pacman.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

import static org.junit.jupiter.api.Assertions.*;

class PacManTest {

    MazeMap map = new MazeMap(3, 3,
            new boolean[] {
                    false, true, true,
                    true, true, true,
                    true, false, true
            }
    );
    Square square = new Square(map, 0, 0);
    Square rightSquare = new Square(map, 0, 1);
    PacMan pacMan = new PacMan(3, square);

    @Test
    void getSquare() {
        assertEquals(square, pacMan.getSquare());
        Assertions.assertNotEquals(rightSquare, pacMan.getSquare());
    }

    @Test
    void getNbLives() {
        assertEquals(3, pacMan.getNbLives());
        Assertions.assertNotEquals(5, pacMan.getNbLives());
    }

    @Test
    void setSquare() {
        pacMan.setSquare(rightSquare);
        assertEquals(rightSquare, pacMan.getSquare());
        Assertions.assertNotEquals(square, pacMan.getSquare());
    }

    @Test
    void die() {
        pacMan.die();
        assertEquals(2, pacMan.getNbLives());
        Assertions.assertNotEquals(4, pacMan.getNbLives());
    }
}