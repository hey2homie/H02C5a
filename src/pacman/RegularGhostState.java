package pacman;

import java.util.Random;

public class RegularGhostState extends  GhostState {

    @Override
    public GhostState move(Ghost ghost, Random random) {
        ghost.reallyMove(random);
        return new RegularGhostState();
    }

    @Override
    public GhostState hitBy(Ghost ghost, PacMan pacMan) {
        pacMan.die();
        return new RegularGhostState();
    }

    @Override
    public boolean isVulnerable() {
        return false;
    }
}