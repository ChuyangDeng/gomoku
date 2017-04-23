package gomuku;

import java.util.ArrayList;

/**
 * This class implements a human player
 * @author paula
 *
 */
public class HumanPlayer implements Player{
	String color;
	String name;
	ArrayList<Cell> myCells;
	ArrayList<Cell> opponentCells;
	
	/**
	 * Constructor
	 * @param color pawn color
	 * @param name player name
	 */
	public HumanPlayer(String color, String name){
		this.color = color;
		this.name = name;
		myCells = new ArrayList<>();
		opponentCells = new ArrayList<>();
	}
	
	@Override
	public Pawn makeMove(Cell opponent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}

}
