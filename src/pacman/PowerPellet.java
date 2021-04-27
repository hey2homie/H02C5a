package pacman;

public class PowerPellet extends FoodItem {

    public PowerPellet(Square square) {
        super(square);
        setSize(getSize() * 2);
    }

    @Override
    public boolean isPowerPellet() {
        return true;
    }


}