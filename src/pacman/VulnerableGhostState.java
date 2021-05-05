package pacman;

import java.util.Random;

public class VulnerableGhostState extends GhostState {

    @Override
    public GhostState move(Ghost ghost, Random random) {
        if (ghost.getDelay() % 2 == 0) {
            ghost.reallyMove(random);
        }
        if (ghost.getDelay() == 12) {
            ghost.setDelay(1);
            return new RegularGhostState();
        }

        ghost.increaseDelay();

        return new VulnerableGhostState();
    }

    @Override
    public GhostState hitBy(Ghost ghost, PacMan pacMan) {
        ghost.setDelay(1);
        ghost.setSquare(ghost.getOriginalSquare());
        return new RegularGhostState();
    }

    @Override
    public boolean isVulnerable() {
        return true;
    }
}