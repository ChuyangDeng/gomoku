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
	 * Instance variables
	 */
	Strategy ab = new AlphaBeta();
	Strategy random = new RandomStrategy();
	
	@Override
	public Position nextMove(Board board, Player currentPlayer) {
		Position move = ab.getMove(board, currentPlayer);
		if (!AlphaBeta.validPosition(move.getX(), move.getY())){
			move = random.getMove(board, currentPlayer);
		}
		return move;
	}
	
}
