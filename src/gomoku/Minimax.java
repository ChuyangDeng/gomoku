package gomoku;

/**
 * Computer strategy using minimax tree
 * @author paula
 *
 */
public class Minimax implements ComputerStrategy{
	
	private Cell[][] board;
	
	/**
	 * This method takes the current chessboard and evaluate a score for current player
	 * @param myPawns
	 * @param opponentPawns
	 * @return
	 */
	private double evaluate(Board board, String color) {
		double blackScore = 0.0, whiteScore = 0.0;
		int blacks = 0, whites = 0;
		this.board = board.getBoard();
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board.length; j++) {
				if (this.board[i][j].getPawn() != null) {
					Pawn currentPawn = this.board[i][j].getPawn();
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

}
