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
	int boardSize;
	ArrayList<Cell> myCells;
	ArrayList<Cell> opponentCells;
	
	/**
	 * Constructor
	 * @param color pawn color
	 * @param name player name
	 */
	public HumanPlayer(String color, String name, int boardSize){
		this.color = color;
		this.name = name;
		this.boardSize = boardSize;
		myCells = new ArrayList<>();
		opponentCells = new ArrayList<>();
	}
	
	@Override
	public Pawn makeMove(Cell opponent) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int x = -1;
		while (x < 0 || x > boardSize - 1){
			System.out.println("Please enter the row number: (0 to " + boardSize + ")");
			String temp = in.nextLine();
			try{
				x = Integer.valueOf(temp);
			} catch (Exception e){
				System.out.println("Please enter a valid input. ");
			}
		}
		int y = -1;
		while (y < 0 || y > boardSize - 1){
			System.out.println("Please enter the column number: (0 to " + boardSize + ")");
			String temp = in.nextLine();
			try{
				y = Integer.valueOf(temp);
			} catch (Exception e){
				System.out.println("Please enter a valid input. ");
			}
		}
		
//		in.close();
		
		Pawn p = new Pawn(color);
		p.setPawn(x, y);
		
		return p;
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public String getName() {
		return name;
	}

}
