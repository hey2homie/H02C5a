package pacman;

public class PowerPellet extends FoodItem {

    public PowerPellet(Square square) {
        super(square);
        this.size = 2;
    }

    @Override
    public boolean isPowerPellet() {
        return true;
    }
}