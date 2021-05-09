package pacman.wormholes;

import pacman.Square;

import java.util.HashSet;
import java.util.Set;

/**
 * @invar | getSquare() != null
 * 
 * @immutable
 */
public class ArrivalPortal {

    /**
     * @invar | square != null
     */
    private final Square square;

    /**
     * @representationObjects
     * @peerObject
     */
    private final HashSet<Wormhole> wormholes = new HashSet<>();

    /**
     * @basic
     */
    public Square getSquare() {
        return square;
    }
    
    /**
     * @basic
     * @peerObjects
     */
    public Set<Wormhole> getWormholes() {
        return Set.copyOf(wormholes);
    }
    
    /**
     * @throws IllegalArgumentException | square == null
     *
     * @post | getSquare() == square
     */
    public ArrivalPortal(Square square) {
        if (square == null) {
            throw new IllegalArgumentException("Wrong parameter");
        }

        this.square = square;
    }

    /**
     * @mutates | this
     *
     * @throws IllegalArgumentException | wormhole == null
     *
     * @post | getWormholes().size() == old(getWormholes().size()) + 1
     */
    void join(Wormhole wormhole) {
        if (wormhole == null) {
            throw new IllegalArgumentException("Wrong parameter");
        }

        this.wormholes.add(wormhole);
    }

    /**
     * @mutates | this
     *
     * @throws IllegalArgumentException | wormhole == null
     *
     * @post | getWormholes().size() == old(getWormholes().size()) - 1
     */
    void remove(Wormhole wormhole) {
        if (wormhole == null) {
            throw new IllegalArgumentException("Wrong parameter");
        }

        this.wormholes.remove(wormhole);
    }
}