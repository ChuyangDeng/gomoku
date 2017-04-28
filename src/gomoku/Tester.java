package gomoku;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player player1 = new HumanPlayer("black", "human player", 10);
		Player player2  = new RandomPlayer("white", "computer player", 10);
		GomokuGame game = new GomokuGame(10, player1, player2);
		game.playGame();
		
		
	}

}
