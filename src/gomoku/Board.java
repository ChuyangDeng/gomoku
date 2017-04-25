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
	 * This method takes the current chessboard and evaluate a score for current player
	 * @param myPawns
	 * @param opponentPawns
	 * @return
	 */
	public double evaluate(String color) {
		double blackScore = 0.0, whiteScore = 0.0;
		int blacks = 0, whites = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (this.board[i][j].getPawn() != null) {
					Pawn currentPawn = board[i][j].getPawn();
					if (currentPawn.getColor().equals("black")) {
						whiteScore += Math.pow(2.0, whites * 1.0);
						whites = 0;
						blacks++;
					}
					else {
						blackScore += Math.pow(2.0, blacks * 1.0);
						blacks++;
						whites++;
					}
				}
			}
		}
		if (color.equals("black")) return blackScore;
		else return whiteScore;
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
	
	/**
	 * Accessor to chessboard's size
	 * @return
	 */
	public int getSize() {
		return board.length;
	}

}
