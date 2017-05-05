package gomokugame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStrategy implements Strategy{
	
	private Random rand;
	
	public RandomStrategy() {
		rand = new Random();
	}

	@Override
	public Position getMove(Board board, Player currentPlayer) {
		return makeMove(board.getBoard(), currentPlayer);
	}

	@Override
	public Position makeMove(Player[][] board, Player player) {
		List<Position> empties = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == null) empties.add(new Position(i, j));
			}
		}
		return empties.get(rand.nextInt(empties.size()));
	}

}
