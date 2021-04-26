package pacman;

import java.util.Random;

public class Maze {

	private final Random random;
	private final MazeMap map;
	private final PacMan pacMan;
	private final Ghost[] ghosts;
	private FoodItem[] foodItems;

	public MazeMap getMap() {
		return map;
	}

	public PacMan getPacMan() {
		return pacMan;
	}

	public Ghost[] getGhosts() {
		return ghosts.clone();
	}

	public FoodItem[] getFoodItems() {
		return foodItems.clone();
	}

	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foodItems) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
	}

	public boolean isCompleted() {
		return foodItems.length == 0;
	}

	private void checkPacManDamage() {
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(pacMan.getSquare()))
				ghost.hitBy(pacMan);
	}

	public void moveGhosts() {
		for (Ghost ghost : ghosts)
			ghost.move(random);
		checkPacManDamage();
	}

	private void removeFoodItemAtIndex(int index) {
		FoodItem[] newFoodItems = new FoodItem[foodItems.length - 1];
		System.arraycopy(foodItems, 0, newFoodItems, 0, index);
		System.arraycopy(foodItems, index + 1, newFoodItems, index, newFoodItems.length - index);
		foodItems = newFoodItems;
	}

	// TODO: Change use of instanceof to dynamical binding
	private void removeFoodItemAtSquare(Square square) {
		for (int i = 0; i < foodItems.length; i++) {
			if (foodItems[i].getSquare().equals(square)) {
				if (foodItems[i] instanceof PowerPellet) {
					for (Ghost ghost : ghosts) {
						ghost.pacManAtePowerPellet();
					}
				}
				removeFoodItemAtIndex(i);
				return;
			}
		}
	}

	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		if (newSquare.isPassable()) {
			pacMan.setSquare(newSquare);
			removeFoodItemAtSquare(newSquare);
			checkPacManDamage();
		}
	}
}