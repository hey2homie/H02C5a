package pacman.wormholes;

import pacman.Square;
import java.util.HashSet;
import java.util.Random;

/**
 * @invar | getSquare() != null
 * @mutable
 */
public class DeparturePortal {

    /**
     * @representationObjects
     * @peerObject
     */
    private final HashSet<Wormhole> wormholes = new HashSet<>();

    /**
     * @invar | square != null
     */
    private final Square square;

    /**
     * @basic
     */
    public Square getSquare() {
        return square;
    }

    /**
     * @basic
     */
    public HashSet<Wormhole> getWormholes() {
        return wormholes;
    }

    /**
     * @throws IllegalArgumentException | square == null
     *
     * @post | getSquare() == square
     */
    public DeparturePortal(Square square) {
        if (square == null) {
            throw new IllegalArgumentException("Wrong parameter");
        }
        this.square = square;
    }

    /**
     * @basic
     */
    public Square getRandomArrivalSquare() {
        if (wormholes.size() != 0) {
            Wormhole[] arrayNumbers = wormholes.toArray(new Wormhole[0]);
            return arrayNumbers[new Random().nextInt(wormholes.size())].getArrivalPortal().getSquare();
        }
        else {
            return square;
        }
    }

    /**
     * @mutates | this
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
     * @post | getWormholes().size() == old(getWormholes().size()) - 1
     */
    void remove(Wormhole wormhole) {
        if (wormhole == null) {
            throw new IllegalArgumentException("Wrong parameter");
        }

        this.wormholes.remove(wormhole);
    }
}