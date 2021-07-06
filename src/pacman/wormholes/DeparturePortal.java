package pacman.wormholes;

import pacman.Square;
import java.util.HashSet;
import java.util.Set;

/**
 * @invar | getSquare() != null
 * @invar | getWormholes() != null
 * @invar | getWormholes().stream().allMatch(wormhole -> wormhole.getDeparturePortal() == this)
 */
public class DeparturePortal {

    /**
     * @representationObjects
     * @peerObject
     */
    final Set<Wormhole> wormholes = new HashSet<>();

    /**
     * @invar | square != null
     * @invar | wormholes != null
     * @invar | wormholes.stream().allMatch(wormhole -> wormhole.start == this)
     */
    final Square square;

    /**
     * @basic
     * @immutable
     */
    public Square getSquare() {
        return square;
    }

    /**
     * @basic
     * @creates | result
     * @peerObjects
     */
    public Set<Wormhole> getWormholes() {
        return Set.copyOf(wormholes);
    }

    /**
     * @post | getWormholes().isEmpty()
     * @post | getSquare() == square
     */
    public DeparturePortal(Square square) {
        this.square = square;
    }
}