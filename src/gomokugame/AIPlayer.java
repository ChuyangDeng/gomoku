package gomokugame;

/**
 * 
 * This class implements PlayerInterface.
 * It initiates a strategy with mini-max algorithm and alpha-beta pruning to decide next move.
 * @author chuyangdeng
 *
 */
public class AIPlayer implements PlayerInterface{
	
	/**
	 * Instanve vatiable
	 */
	Strategy ab = new AlphaBeta();
	
	@Override
	public Position nextMove(Board board, Player currentPlayer) {
		return ab.getMove(board, currentPlayer);
	}
	
}
