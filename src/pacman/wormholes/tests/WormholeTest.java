package pacman.wormholes.tests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pacman.MazeMap;
import pacman.Square;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

public class WormholeTest {

    @Test
    void test() {
        MazeMap map = new MazeMap(3, 3,
                new boolean[] {
                        false, true, true,
                        true, true, true,
                        true, false, true
                }
        );
        DeparturePortal departurePortal1 = new DeparturePortal(new Square(map, 0, 0));
        DeparturePortal departurePortal2 = new DeparturePortal(new Square(map, 1, 0));
        ArrivalPortal arrivalPortal1 = new ArrivalPortal(new Square(map, 2, 0));
        ArrivalPortal arrivalPortal2 = new ArrivalPortal(new Square(map, 2, 1));
        Wormhole wormhole = new Wormhole(departurePortal1, arrivalPortal1);

        assertEquals(wormhole.getArrivalPortal(), arrivalPortal1);
        assertEquals(wormhole.getDeparturePortal(), departurePortal1);
        assertEquals(1, wormhole.getDeparturePortal().getWormholes().size()); // Connected to one arrival portal

        Wormhole wormhole1 = new Wormhole(departurePortal1, arrivalPortal2);
        assertEquals(2, departurePortal1.getWormholes().size());    // Connected to two arrival portals
        assertEquals(0, departurePortal2.getWormholes().size());    // Not connected to any arrival portals
    }
}
