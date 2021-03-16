package pacman.tests;

import pacman.MazeMap;

import static org.junit.jupiter.api.Assertions.*;

class MazeMapTest {

    MazeMap map = new MazeMap(2, 3, new boolean[] {false, true, true, true, false, true});

    @org.junit.jupiter.api.Test
    void getWidth() {
        assertEquals(2, map.getWidth());
    }

    @org.junit.jupiter.api.Test
    void getHeight() {
        assertEquals(3, map.getHeight());
    }

    @org.junit.jupiter.api.Test
    void isPassable() {
        assertFalse(map.isPassable(0, 0));
        assertTrue(map.isPassable(0, 1));
        assertFalse(map.isPassable(2, 0));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Wrong length of the array!");
        });
        assertEquals("Wrong length of the array!", exception.getMessage());
    }
}