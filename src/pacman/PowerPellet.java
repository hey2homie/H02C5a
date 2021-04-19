package pacman;

public class PowerPellet extends FoodItem {


    public PowerPellet(Square square) {
        super(square);
        setSize(getSize() * 2);
    }
}
