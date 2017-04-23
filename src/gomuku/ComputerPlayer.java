package gomuku;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This is the ComputerPlayer class. It implements the Player Interface.
 * It also applies Mini-Max and Alpha-Beta Prunning Algorithm as its stratege.
 * @author chuyangdeng
 *
 */
public class ComputerPlayer implements Player{
	
	private String color;
	private String name;
	private List<Cell> myCells;
	private List<Cell> opponentCells;
	
	/**
	 * 
	 * @param color
	 * @param name
	 */
	public ComputerPlayer(String color, String name) {
		this.color = color;
		this.name = name;
		myCells = new ArrayList<>();
		opponentCells = new ArrayList<>();
	}

	@Override
	public Pawn makeMove(Cell oppoentCell) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}

}
