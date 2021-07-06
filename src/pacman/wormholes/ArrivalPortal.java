package pacman.wormholes;

import pacman.Square;

import java.util.HashSet;
import java.util.Set;

/**
 * @invar | getSquare() != null
 * @invar | getWormholes() != null
 * @invar | getWormholes().stream().allMatch(wormhole -> wormhole.getArrivalPortal() == this)
 */
public class ArrivalPortal {

    /**
     * @invar | square != null
     * @invar | wormholes != null
     * @invar | wormholes.stream().allMatch(wormhole -> wormhole.end == this)
     */
    final Square square;

    /**
     * @representationObjects
     * @peerObject
     */
    final Set<Wormhole> wormholes = new HashSet<>();

    /**
     * @post | getSquare() == square
     * @post | getWormholes().isEmpty()
     */
    public ArrivalPortal(Square square) {
        this.square = square;
    }

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
}