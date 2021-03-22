package pacman.tests;

import org.junit.jupiter.api.Test;
import pacman.MazeMap;

import static org.junit.jupiter.api.Assertions.*;

class MazeMapTest {

    MazeMap map = new MazeMap(2, 3, new boolean[] {false, true, true, true, false, true});

    @Test
    void getWidth() {
        assertEquals(2, map.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(3, map.getHeight());
    }

    @Test
    void isPassable() {
        assertFalse(map.isPassable(0, 0));
        assertTrue(map.isPassable(0, 1));
        assertFalse(map.isPassable(2, 0));
    }
}