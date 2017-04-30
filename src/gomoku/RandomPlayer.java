package gomoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * 
 * This is the Random Player class. It implements Player Interface and make moves randomly.
 * @author chuyangdeng
 *
 */
public class RandomPlayer implements Player, EventHandler<MouseEvent>{
	
	/**
	 * Instance variables.
	 */
	private String color;
	private String name;
	private int boardSize;
	private List<Position> availableCells;
	private Random rand;
	
	/**
	 * Constructor of Random Player.
	 * @param color
	 * @param name
	 */
	public RandomPlayer(String color, String name, int size) {
		this.color = color;
		this.name = name;
		boardSize = size;
		availableCells = new ArrayList<>();
		initAvailableCells();
		rand = new Random();
	}

	@Override
	public Pawn makeMove(Cell opponentMove) {
		Position opponent = new Position(opponentMove.getX(), opponentMove.getY());
		availableCells.remove(opponent);
		int random = rand.nextInt(availableCells.size());
		Position randomMove = availableCells.get(random);
		Pawn move = new Pawn(color);
		move.setPawn(randomMove.getX(), randomMove.getY());
		availableCells.remove(randomMove);
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
				Position position = new Position(row, col);
				availableCells.add(position);
			}
		}
	}

	/**
	 * TODO: implement player's handler
	 */
	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

}
