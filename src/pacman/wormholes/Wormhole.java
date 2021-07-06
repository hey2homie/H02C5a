package pacman.wormholes;

/**
 * @invar | getDeparturePortal() != null
 * @invar | getDeparturePortal().getWormholes().contains(this)
 *
 * @invar | getArrivalPortal() != null
 * @invar | getArrivalPortal().getWormholes().contains(this)
 */
public class Wormhole {

    /**
     * @invar | arrivalPortal != null
     * @invar | departurePortal != null
     *
     * @peerObjects
     */
    ArrivalPortal arrivalPortal;
    DeparturePortal departurePortal;

    /**
     * @basic
     * @peerObject
     */
    public ArrivalPortal getArrivalPortal() {
        return arrivalPortal;
    }

    /**
     * @basic
     * @peerObjects
     */
    public DeparturePortal getDeparturePortal() {
        return departurePortal;
    }

    /**
     * @throws IllegalArgumentException | departurePortal == null || arrivalPortal == null
     *
     * @mutates_properties | arrivalPortal.getDeparturePortal(), departurePortal.getDeparturePortal()
     *
     * @post | getArrivalPortal() == arrivalPortal
     * @post | getDeparturePortal() == departurePortal
     * @post | arrivalPortal.getWormholes().equals(LogicalSet.plus(old(arrivalPortal.getWormholes()), this))
     * @post | departurePortal.getWormholes().equals(LogicalSet.plus(old(departurePortal.getWormholes()), this))
     */
    public Wormhole(DeparturePortal departurePortal, ArrivalPortal arrivalPortal) {
        if (departurePortal == null || arrivalPortal == null) {
            throw new IllegalArgumentException("Wrong parameters");
        }
        this.arrivalPortal = arrivalPortal;
        arrivalPortal.wormholes.add(this);
        this.departurePortal = departurePortal;
        departurePortal.wormholes.add(this);
    }

    /**
     * @throws IllegalArgumentException | portal == null
     * @throws IllegalArgumentException | portal == getArrivalPortal()
     *
     * @mutates_properties | this.getArrivalPortal(), getArrivalPortal().getWormholes(), portal.getWormholes()
     *
     * @post | getArrivalPortal() == portal
     * @post | old(getArrivalPortal()).getWormholes().equals(LogicalSet.minus(old(getArrivalPortal().getWormholes()), this))
     * @post | portal.getWormholes().equals(LogicalSet.plus(old(portal.getWormholes()), this))
     */
    public void setArrivalPortal(ArrivalPortal portal) {
        if (arrivalPortal == null || arrivalPortal == portal) {
            throw new IllegalArgumentException("Wrong parameter");
        }

        arrivalPortal.wormholes.remove(this);
        arrivalPortal = portal;
        arrivalPortal.wormholes.add(this);
    }

    /**
     * @throws IllegalArgumentException | portal == null
     * @throws IllegalArgumentException | portal == getDeparturePortal()
     *
     * @mutates_properties | this.getDeparturePortal(), getDeparturePortal().getWormholes(), portal.getWormholes()
     *
     * @post | getDeparturePortal() == portal
     * @post | old(getDeparturePortal()).getWormholes().equals(LogicalSet.minus(old(getDeparturePortal().getWormholes()), this))
     * @post | portal.getWormholes().equals(LogicalSet.plus(old(portal.getWormholes()), this))
     */
    public void setDeparturePortal(DeparturePortal portal) {
        if (portal == null || portal == departurePortal)
            throw new IllegalArgumentException("Wrong parameter");

        departurePortal.wormholes.remove(this);
        departurePortal = portal;
        departurePortal.wormholes.add(this);
    }
}
