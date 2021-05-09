package pacman.wormholes;

import pacman.Square;

import java.util.HashSet;
import java.util.Random;

public class DeparturePortal {

    HashSet<Wormhole> wormholes = new HashSet<>();
    private final Square square;

    public DeparturePortal(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }

    public Square getRandomArrivalSquare() {
        if (wormholes.size() != 0) {
            Wormhole[] arrayNumbers = wormholes.toArray(new Wormhole[wormholes.size()]);
            return arrayNumbers[new Random().nextInt(wormholes.size())].getArrivalPortal().getSquare();
        }
        else {
            return square;
        }
    }

    void join(Wormhole wormhole) {
        this.wormholes.add(wormhole);
    }

    void remove(Wormhole wormhole) {
        this.wormholes.remove(wormhole);
    }
}
