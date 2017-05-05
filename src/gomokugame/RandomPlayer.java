package gomokugame;

public class RandomPlayer implements PlayerInterface {
	
	Strategy random = new RandomStrategy();

	@Override
	public Position nextMove(Board board, Player currentPlayer) {
		return random.getMove(board, currentPlayer);
	}

}
