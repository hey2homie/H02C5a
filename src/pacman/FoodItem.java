package pacman;

public abstract class FoodItem {

    private final Square square;

    public Square getSquare() {
        return square;
    }

    public FoodItem(Square square) {
        if (square == null) {
            throw new IllegalArgumentException("Square cannot be null");
        }

        this.square = square;
    }

    public void isPowerPellet(Maze maze) {}

    public abstract int getSize();
}