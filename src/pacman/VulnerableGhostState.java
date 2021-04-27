package pacman;

import java.util.Random;

public class VulnerableGhostState extends GhostState {

    @Override
    public GhostState move(Ghost ghost, Random random) {
        if (ghost.getDelay() % 2 == 1) {
            ghost.reallyMove(random);
        }
        if (ghost.getDelay() == 12) {
            ghost.setDelay(0);
            return new RegularGhostState();
        }

        ghost.increaseDelay();

        return new VulnerableGhostState();
    }

    @Override
    public GhostState hitBy(Ghost ghost, PacMan pacMan) {
        ghost.setSquare(ghost.getOriginalSquare());
        return new RegularGhostState();
    }

    @Override
    public boolean isVulnerable() {
        return true;
    }
}
