package gomokugame;

/**
 * 
 * This class allows for different strategies to play the gomoku game.
 * [Mini-Max with Alpha-Beta strategy and Random strategy are implemented in this project]
 * @author chuyangdeng
 *
 */
public interface Strategy {
	
	/**
	 * Accessor to the best move
	 * @param board
	 * @param currentPlayer
	 * @return
	 */
	public Position getMove(Board board, Player currentPlayer);
	
	/**
	 * Implementations of a specific strategy
	 * @param board
	 * @param player
	 * @return
	 */
	public Position makeMove(Player[][] board, Player player);

}
