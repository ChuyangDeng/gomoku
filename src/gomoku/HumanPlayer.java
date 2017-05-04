package gomoku;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;


/**
 * This class implements a human player
 * @author paula
 *	
 */
public class HumanPlayer extends JPanel implements Player{
	
	public static final BoardPainter panel = new BoardPainter();
	public static final int X = 15;
	public static final int Y = 15;
	public static final int CELL_SIZE = 32;
	public static final int CELL_OFFSET = CELL_SIZE / 2;
	public static final int X_SIZE = CELL_SIZE * X;
	public static final int Y_SIZE = CELL_SIZE * Y;
	public static final int TITLE_BAR_HEIGHT = 30;
	
	String color;
	String name;
	int boardSize;
	ArrayList<Position> myPositions;
	ArrayList<Position> opponentPositions;
	private int x;
	private int y;
	
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
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1) {
					x = (e.getX() - CELL_OFFSET) / CELL_SIZE;
					y = (e.getY() - CELL_OFFSET) / CELL_SIZE;
				}
			}
		});

	}
	
	@Override
	public Pawn makeMove(Cell opponent) {
		Position o = new Position(opponent.getX(), opponent.getY());
		opponentPositions.add(o);
		Pawn p = null;
		
//		Scanner in = new Scanner(System.in);
//		while (p == null){
//			int x = -1;
//			while (x < 0 || x > boardSize - 1){
//				System.out.println("Please enter the row number: (0 to " + boardSize + ")");
//				String temp = in.nextLine();
//				try{
//					x = Integer.valueOf(temp);
//				} catch (Exception e){
//					System.out.println("Please enter a valid input. ");
//				}
//			}
//			int y = -1;
//			while (y < 0 || y > boardSize - 1){
//				System.out.println("Please enter the column number: (0 to " + boardSize + ")");
//				String temp = in.nextLine();
//				try{
//					y = Integer.valueOf(temp);
//				} catch (Exception e){
//					System.out.println("Please enter a valid input. ");
//				}
//			}
//			
//			Position newPos = new Position(x, y);
//			if (myPositions.contains(newPos) || opponentPositions.contains(newPos)){
//				System.out.println("This spot is occupied.");
//				continue;
//			} else {
//				p = new Pawn(color);
//				p.setPawn(x, y);
//				myPositions.add(newPos);	
//			}
//		}
		
		Position newPos = new Position(x, y);
		if (myPositions.contains(newPos) || opponentPositions.contains(newPos)){
			System.out.println("This spot is occupied.");
		} else {
			p = new Pawn(color);
			p.setPawn(x, y);
			myPositions.add(newPos);	
		}
		return p;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public String getName() {
		return name;
	}
	


}
