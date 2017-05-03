package gomoku;

/**
 * This is the tester class. It creates player objects and a GomokuGame object and runs the game in the console.
 * @author paula
 *
 */
public class Tester {
	
	static Player player1;
	static Player player2;
	static GomokuGame game;
	static Board board;

	public static void main(String[] args) throws Exception {
		player1 = new HumanPlayer("black", "human player", 15);
		player2 = new RandomPlayer("white", "random player", 15);
		board = new Board(15);
		game = new GomokuGame(15, player1, player2);
		game.playGame();
	}

}
