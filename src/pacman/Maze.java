package pacman;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

import java.util.Random;

public class Maze {

	private final Random random;
	private final MazeMap map;
	private final PacMan pacMan;
	private final Ghost[] ghosts;
	private FoodItem[] foodItems;
	private final ArrivalPortal[] arrivalPortals;
	private final DeparturePortal[] departurePortals;
	private final Wormhole[] wormholes = new Wormhole[3];
	private int nbWormholes = 0;

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

	public ArrivalPortal[] getArrivalPortals() {
		return arrivalPortals.clone();
	}

	public DeparturePortal[] getDeparturePortals() {
		return departurePortals.clone();
	}

	public Wormhole[] getWormholes() {
		return wormholes;
	}

	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foodItems, ArrivalPortal[]
			arrivalPortals, DeparturePortal[] departurePortals) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
		this.arrivalPortals = arrivalPortals.clone();
		this.departurePortals = departurePortals.clone();
	}

	public boolean isCompleted() {
		return foodItems.length == 0;
	}

	private void checkPacManDamage() {
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(pacMan.getSquare()))
				ghost.hitBy(pacMan);
	}

	public void pacMamAtePowerPellet() {
		for (Ghost ghost : ghosts) {
			ghost.pacManAtePowerPellet();
		}
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

	private void removeFoodItemAtSquare(Square square) {
		for (int i = 0; i < foodItems.length; i++) {
			if (foodItems[i].getSquare().equals(square)) {
				foodItems[i].isPowerPellet(this);
				removeFoodItemAtIndex(i);
			}
		}
	}

	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		if (newSquare.isPassable()) {
			pacMan.setSquare(checkForPortal(newSquare));
			removeFoodItemAtSquare(newSquare);
			checkPacManDamage();
		}
	}

	public void addWormhole(Wormhole wormhole) {
		wormholes[nbWormholes] = wormhole;
		nbWormholes++;
	}

	private Square checkForPortal(Square square) {
		for (Wormhole wormhole : wormholes) {
			if (square.equals(wormhole.getDeparturePortal().getSquare())) {
				return wormhole.getDeparturePortal().getRandomArrivalSquare();
			}
		}

		return square;
	}
}