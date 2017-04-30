package gomoku;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * This class implements a human player
 * @author paula
 *	
 */
public class HumanPlayer implements Player, EventHandler<MouseEvent>{
	String color;
	String name;
	int boardSize;
	ArrayList<Position> myPositions;
	ArrayList<Position> opponentPositions;
	
	/**
	 * Constructor
	 * @param color pawn color
	 * @param name player name
	 */
	public HumanPlayer(String color, String name, int boardSize){
		this.color = color;
		this.name = name;
		this.boardSize = boardSize;
		myPositions = new ArrayList<>();
		opponentPositions = new ArrayList<>();
	}
	
	@Override
	public Pawn makeMove(Cell opponent) {
		// TODO Auto-generated method stub
		Position o = new Position(opponent.getX(), opponent.getY());
		opponentPositions.add(o);
		Scanner in = new Scanner(System.in);
//		Position position = null;
		Pawn p = null;
		
		while (p == null){
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
			
			Position newPos = new Position(x, y);
			if (myPositions.contains(newPos) || opponentPositions.contains(newPos)){
				System.out.println("This spot is occupied.");
				continue;
			} else {
				p = new Pawn(color);
				p.setPawn(x, y);
				myPositions.add(newPos);	
			}
		}
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
	
	/**
	 * TODO: implement player's 
	 */
	@Override
	public void handle(MouseEvent event) {
		
	}

}
