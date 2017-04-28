package gomoku;

public class GomokuGame {
	private Player player1;
	private Player player2;
	private Board board;
	private int size;
	
	public GomokuGame(int size, Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
		board = new Board(size);
		this.size = size;
	}
	
	public boolean playGame(){
		int round = 1;
		Cell opponentMove = new Cell(-1, -1);
		while (!board.checkWinner()){
			if (round % 2 == 1){
				System.out.println("Player1's turn");
				Pawn opponent =  player1.makeMove(opponentMove);
				board.setMove(opponent);
				opponentMove.setPawn(opponent);
			} else {
				System.out.println("Player2's turn");
				Pawn opponent =  player2.makeMove(opponentMove);
				board.setMove(opponent);
				opponentMove.setPawn(opponent);
			}
			board.printBoard();
			round ++;
		}
		
		return board.checkWinner();
	}
	
}
