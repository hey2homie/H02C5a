package pacman.wormholes;

public class Wormhole {

    private ArrivalPortal arrivalPortal;
    private DeparturePortal departurePortal;

    public Wormhole(DeparturePortal departurePortal, ArrivalPortal arrivalPortal) {
        if (departurePortal == null || arrivalPortal == null) {
            throw new IllegalArgumentException("Wrong parameters");
        }
        this.arrivalPortal = arrivalPortal;
        this.departurePortal = departurePortal;
        this.departurePortal.join(this);
    }

    public ArrivalPortal getArrivalPortal() {
        return arrivalPortal;
    }

    public DeparturePortal getDeparturePortal() {
        return departurePortal;
    }

    public void setArrivalPortal(ArrivalPortal arrivalPortal) {
        this.arrivalPortal = arrivalPortal;
    }

    public void setDeparturePortal(DeparturePortal departurePortal) {
        this.departurePortal.remove(this);
        this.departurePortal = departurePortal;
        this.departurePortal.join(this);
    }
}
