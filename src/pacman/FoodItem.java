package pacman;


public abstract class FoodItem {

    private final Square square;
    protected int size;


    public Square getSquare() {
        return square;
    }


    public int getSize() {
        return size;
    }


    public FoodItem(Square square) {
        if (square == null) {
            throw new IllegalArgumentException("Square cannot be null");
        }

        this.square = square;
    }

    public abstract boolean isPowerPellet();
}