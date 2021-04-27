package pacman;

public class Dot extends FoodItem {

	public Dot(Square square) {
		super(square);
		this.size = 1;
	}

	@Override
	public boolean isPowerPellet() {
		return false;
	}
}