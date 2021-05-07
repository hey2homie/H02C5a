package pacman;

public class Dot extends FoodItem {

	public Dot(Square square) {
		super(square);
	}

	@Override
	public boolean isPowerPellet() {
		return false;
	}

	@Override
	public int getSize() {
		return 1;
	}
}