package gomoku;

/**
 * 
 * This is the Board class, it maintains Cells and check for winners.
 * @author chuyangdeng
 *
 */
public class Board {
	
	/**
	 * Instance variables
	 */
	private Cell[][] board;
	private boolean hasWinner;
	private String winner;
	
	/**
	 * Constructor of Board
	 * @param size is the size of chess board
	 */
	public Board(int size) {
		board = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new Cell(i, j);
			}
		}
		hasWinner = false;
		winner = null;
	}
	
	/**
	 * Accessor to the board
	 * @return
	 */
	public Cell[][] getBoard() {
		return board;
	}
	
	/**
	 * Check for 5 consecutive cells of same color
	 * @return
	 */
	public boolean checkWinner() {
		return hasWinner;
	}
	
	/**
	 * If hasWinner() returns true, this method will return the color of the winner.
	 * @return
	 */
	public String declareWinner() {
		return winner;
	}
	
	/**
	 * This method takes Player1's last Pawn and give a Cell to Player2.
	 * @param pawn
	 * @return a Cell to Player2
	 */
	public Cell setMove(Pawn pawn) {
		board[pawn.getX()][pawn.getY()].setPawn(pawn);
		return board[pawn.getX()][pawn.getY()];
	}
	
	/**
	 * This method removes a Pawn from the board for calculating scores
	 * @param position
	 */
	public void removePawn(Position position) {
		board[position.getX()][position.getY()].setPawn(null);
	}

}
