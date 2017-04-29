package gomoku;

/**
 * This class implements a gomoku game. It allows 2 users to play the game and declares the winner in the end.
 * @author paula
 *
 */
public class GomokuGame {
	private Player player1;
	private Player player2;
	private Board board;
	private int size;
	
	/**
	 * Constructor 
	 * @param size size of game board
	 * @param player1
	 * @param player2
	 */
	public GomokuGame(int size, Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
		board = new Board(size);
		this.size = size;
	}
	
	/**
	 * This method implements the gomoku game. It allows two users to take alternative turns to play the game.
	 * @return true if there is a winner, false otherwise
	 */
	public boolean playGame(){
		int round = 1;
		Cell opponentMove = new Cell(-1, -1);
//		Cell temp = new Cell(-1, -1);
		
		/* loop until there is a winner */
		while (!board.checkWinner(opponentMove)){
			if (round % 2 == 1){
				System.out.println("Player1's turn");
				Pawn current = player1.makeMove(opponentMove);
				System.out.println(current.getColor());
				board.setMove(current);
				opponentMove.setPawn(current);
				board.printBoard();
//				System.out.println("Start evaluating");
//				board.evaluate(player1.getColor());
//				System.out.println("End evaluating");
			} else {
				System.out.println("Player2's turn");
				Pawn current =  player2.makeMove(opponentMove);
				board.setMove(current);
				opponentMove.setPawn(current);
				board.printBoard();
//				board.evaluate(player2.getColor());
			}
			
			round ++;
		}
		
		/* declare the winner */
		System.out.println(board.declareWinner() + " wins!");
		return board.checkWinner(opponentMove);
	}
	
}
