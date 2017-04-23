package gomoku;

import java.util.ArrayList;
import java.util.Scanner;

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
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the row number: ");
		int x = in.nextInt();
		System.out.println("Please enter the column number:  ");
		int y = in.nextInt();
		in.close();
		
		Pawn p = new Pawn(color);
		p.setPawn(x, y);
		
		return p;
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}

}
