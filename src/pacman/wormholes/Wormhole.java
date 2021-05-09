package pacman.wormholes;

/**
 * @mutable
 *
 * @invar | getArrivalPortal() != null
 * @invar | getDeparturePortal() != null
 */
public class Wormhole {

    /**
     * @invar | arrivalPortal != null
     * @invar | departurePortal != null
     */
    private ArrivalPortal arrivalPortal;
    private DeparturePortal departurePortal;

    /**
     * @basic
     */
    public ArrivalPortal getArrivalPortal() {
        return arrivalPortal;
    }

    /**
     * @basic
     */
    public DeparturePortal getDeparturePortal() {
        return departurePortal;
    }

    /**
     * @throws IllegalArgumentException | departurePortal == null || arrivalPortal == null
     *
     * @mutates_properties | this.getDeparturePortal()
     *
     * @post | getArrivalPortal() == arrivalPortal
     * @post | getDeparturePortal() == departurePortal
     */
    public Wormhole(DeparturePortal departurePortal, ArrivalPortal arrivalPortal) {
        if (departurePortal == null || arrivalPortal == null) {
            throw new IllegalArgumentException("Wrong parameters");
        }
        this.arrivalPortal = arrivalPortal;
        this.departurePortal = departurePortal;
        this.departurePortal.join(this);
    }

    /**
     * @mutates | this
     *
     * @throws IllegalArgumentException | arrivalPortal == null
     *
     * @post | getArrivalPortal().equals(arrivalPortal)
     */
    public void setArrivalPortal(ArrivalPortal arrivalPortal) {
        if (arrivalPortal == null) {
            throw new IllegalArgumentException("Wrong parameter");
        }

        this.arrivalPortal = arrivalPortal;
    }

    /**
     * @mutates | this
     *
     * @mutates_properties | this.getDeparturePortal()
     *
     * @throws IllegalArgumentException | departurePortal == null
     *
     * @post | getDeparturePortal() == departurePortal
     */
    public void setDeparturePortal(DeparturePortal departurePortal) {
        if (departurePortal == null) {
            throw new IllegalArgumentException("Wrong parameter");
        }

        this.departurePortal.remove(this);
        this.departurePortal = departurePortal;
        this.departurePortal.join(this);
    }
}
