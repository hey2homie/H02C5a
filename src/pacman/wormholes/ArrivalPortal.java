package pacman.wormholes;

import pacman.Square;

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
     * @basic
     */
    public Square getSquare() {
        return square;
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
}
