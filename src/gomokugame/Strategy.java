package gomokugame;

public interface Strategy {
	
	public Position getMove(Board board, Player currentPlayer);
	
	public Position makeMove(Player[][] board, Player player);

}
