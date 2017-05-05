package gomokugame;

public class AIPlayer {
	
	Strategy ab = new AlphaBeta();
	
	public Position nextMove(Board board, Player currentPlayer) {
		return ab.getMove(board, currentPlayer);
	}
	
}
