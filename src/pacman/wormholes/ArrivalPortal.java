package pacman.wormholes;

import pacman.Square;
import java.util.HashSet;

public class ArrivalPortal {

    private final Square square;

    public ArrivalPortal(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }
}
