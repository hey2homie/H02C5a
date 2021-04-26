package pacman;

import java.util.Random;

public abstract class GhostState {

    private int delay = 0;

    public GhostState move(Ghost ghost, Random random) {
        return null;
    }

    public void increaseDelay() {
        this.delay++;
    }

    public void decreaseDelay() {
        this.delay--;
    }

    public GhostState hitBy(Ghost ghost, PacMan pacMan) {
        if (ghost.isVulnerable()) {
            ghost.setSquare(ghost.getOriginalSquare());
            return new RegularGhostState();
        } else {
            pacMan.die();
            return new VulnerableGhostState();
        }
    }
}
