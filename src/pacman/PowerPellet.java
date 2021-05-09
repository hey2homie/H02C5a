package pacman;

public class PowerPellet extends FoodItem {

    public PowerPellet(Square square) {
        super(square);
    }

    @Override
    public void isPowerPellet(Maze maze) {
        maze.pacMamAtePowerPellet();
    }

    @Override
    public int getSize() {
        return 2;
    }
}