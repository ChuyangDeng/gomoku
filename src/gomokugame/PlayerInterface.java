package gomokugame;

/**
 * 
 * This interface allows for different types of players.
 * [AIPlayer and RandomPlayer are implemented in this project]
 * @author chuyangdeng
 *
 */
public interface PlayerInterface {
	
	/**
	 * This method is for the player to call and uses a particular strategy to decide next best move.
	 * @param board
	 * @param currentPlayer
	 * @return
	 */
	public Position nextMove(Board board, Player currentPlayer);

}
