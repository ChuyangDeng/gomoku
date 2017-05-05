package gomokugame;

/**
 * 
 * This is the Random player, it applies a RandomStrategy to play the game.
 * @author chuyangdeng
 *
 */
public class RandomPlayer implements PlayerInterface {
	
	/**
	 * Instance variables
	 */
	Strategy random = new RandomStrategy();

	@Override
	public Position nextMove(Board board, Player currentPlayer) {
		return random.getMove(board, currentPlayer);
	}

}
