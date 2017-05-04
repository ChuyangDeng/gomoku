package gomoku;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * This class implements a gomoku game. It allows 2 users to play the game and declares the winner in the end.
 * @author paula
 *
 */
public class GomokuGame extends JPanel{
	
	public static final BoardPainter panel = new BoardPainter();
	public static final int X = 15;
	public static final int Y = 15;
	public static final int CELL_SIZE = 32;
	public static final int CELL_OFFSET = CELL_SIZE / 2;
	public static final int X_SIZE = CELL_SIZE * X;
	public static final int Y_SIZE = CELL_SIZE * Y;
	public static final int TITLE_BAR_HEIGHT = 30;
	
	private PawnPainter whitePawn;
	private PawnPainter blackPawn;
	private int row;
	private int col;
	private boolean win = false;

	private Player player1;
	private Player player2;
	private Board board;
	private int size;
	
	/**
	 * Constructor 
	 * @param size size of game board
	 * @param player1
	 * @param player2
	 */
	public GomokuGame(int size, Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
		this.size = size;
		board = new Board(size);
		
		whitePawn = new PawnPainter("WHITE-PAWN-IMAGE-PATH");
		blackPawn = new PawnPainter("BLACK-PAWN-IMAGE-PATH");
	}
	
	/**
	 * This method implements the gomoku game. It allows two users to take alternative turns to play the game.
	 * @return true if there is a winner, false otherwise
	 */
	public boolean playGame(){
		int round = 1;
		Cell opponentMove = new Cell(-1, -1);
		
		/* loop until there is a winner */
		while (!board.checkWinner(opponentMove)){
			if (round % 2 == 1){
				System.out.println(player1.getName() + "'s turn");
				Pawn current = player1.makeMove(opponentMove);
				System.out.println(current.getColor());
				board.setMove(current);
				opponentMove.setPawn(current);
			} else {
				System.out.println(player2.getName() + "'s turn");
				Pawn current =  player2.makeMove(opponentMove);
				board.setMove(current);
				opponentMove.setPawn(current);
				board.printBoard();
			}
			round ++;
		}
		
		/* declare the winner */
		System.out.println(board.declareWinner() + " wins!");
		return board.checkWinner(opponentMove);
	}


	
}
