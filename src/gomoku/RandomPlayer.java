package gomoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * This is the Random Player class. It implements Player Interface and make moves randomly.
 * @author chuyangdeng
 *
 */
public class RandomPlayer implements Player{
	
	/**
	 * Instance variables.
	 */
	private String color;
	private String name;
	private List<Cell> opponentCells;
	private int boardSize;
	private List<int[]> availableCells;
	private Random rand;
	
	/**
	 * Constructor of Random Player.
	 * @param color
	 * @param name
	 */
	public RandomPlayer(String color, String name, int size) {
		this.color = color;
		this.name = name;
		opponentCells = new ArrayList<>();
		boardSize = size;
		availableCells = new ArrayList<>();
		initAvailableCells();
		rand = new Random();
	}

	@Override
	public Pawn makeMove(Cell opponentMove) {
		opponentCells.add(opponentMove);
		int[] coordination = {opponentMove.getX(), opponentMove.getY()};
		availableCells.remove(coordination);
		int random = rand.nextInt(availableCells.size());
		int[] chosen = availableCells.get(random);
		int x = chosen[0];
		int y = chosen[1];
		Pawn move = new Pawn(color);
		move.setPawn(x, y);
		availableCells.remove(chosen);
		return move;
	}

	@Override
	public String getColor() {
		return color;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * This is the helper method that fills the availableCells with all Cell coordinations initially.
	 */
	private void initAvailableCells() {
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				int[] coordination = {row, col};
				availableCells.add(coordination);
			}
		}
	}

}
