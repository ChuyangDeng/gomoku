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
	public long evaluate(String color) {
		long score = 0;
		score += horizontalScore(color);
		score += verticalScore(color);
		return score;
	}
	
	/**
	 * Calculate scores based on vertical consecutive pawns
	 * @param color
	 * @return
	 */
	private long verticalScore(String color) {
		long blackScore = Long.MIN_VALUE, whiteScore = Long.MIN_VALUE;
		int blacks = 0, whites = 0;
		for (int col = 0; col < board.length && !hasWinner; col++) {
			if (blacks > 0) blackScore += (long) Math.pow(2.0, blacks * 1.0);
			if (whites > 0) whiteScore += (long) Math.pow(2.0, whites * 1.0);
			blacks = 0; whites = 0;
			for (int row = 0; row < board.length && !hasWinner; row++) {
				if (board[row][col].getPawn() != null) {
					Pawn currentPawn = board[row][col].getPawn();
					if (currentPawn.getColor().equals("black")) {
						if (whites > 0) whiteScore += (long) Math.pow(2.0, whites * 1.0);
						whites = 0;
						blacks++;
						if (blacks == 5) {
							hasWinner = true;
							winner = "black";
							break;
						}
					} else {
						if (blacks > 0) blackScore += (long) Math.pow(2.0, whites * 1.0);
						blacks = 0;
						whites++;
						if (whites == 5) {
							hasWinner = true;
							winner = "white";
							break;
						}
					}
				} else {
					if (blacks > 0) blackScore += (long) Math.pow(2.0, blacks * 1.0);
					if (whites > 0) whiteScore += (long) Math.pow(2.0, whites * 1.0);
					blacks = 0; whites = 0;
				}
			}
		}
		if (color.equals("black")) return blackScore - whiteScore;
		else return whiteScore - blackScore;
	}
	
	/**
	 * Calculate scores based on horizontal consecutive pawns
	 * @param color
	 * @return
	 */
	private long horizontalScore(String color) {
		long blackScore = Long.MIN_VALUE, whiteScore = Long.MIN_VALUE;
		for (int row = 0; row < board.length && !hasWinner; row++) {
			int blacks = 0, whites = 0;
			for (int col = 0; col < board.length && !hasWinner; col++) {
				if (board[row][col].getPawn() != null) {
					Pawn currentPawn = board[row][col].getPawn();
					if (currentPawn.getColor().equals("black")) {
						if (whites > 0) whiteScore += (long) Math.pow(2.0, whites * 1.0);
						whites = 0;
						blacks++;
						if (blacks == 5) {
							hasWinner = true;
							winner = "black";
							break;
						}
					} else {
						if (blacks > 0) blackScore += (long) Math.pow(2.0, blacks * 1.0);
						blacks = 0;
						whites++;
						if (whites == 5) {
							hasWinner = true;
							winner = "white";
							break;
						}
					}
				} else {
					if (whites > 0) whiteScore += (long) Math.pow(2.0, whites * 1.0);
					if (blacks > 0) blackScore += (long) Math.pow(2.0, blacks * 1.0);
					whites = 0; blacks = 0;
				}
			}
		}
		if (color.equals("black")) return blackScore - whiteScore;
		else return whiteScore - blackScore;
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
