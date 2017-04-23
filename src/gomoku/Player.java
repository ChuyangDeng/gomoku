package gomoku;

/**
 * 
 * This Player Interface provides common method a Player should have.
 * @author chuyangdeng
 *
 */

public interface Player {
	
	/**
	 * Make a move.
	 * @return a Pawn this player creates
	 */
	public Pawn makeMove(Cell opponentMove);
	
	/**
	 * Accessor to Player's color.
	 * @return color of the Player
	 */
	public String getColor();
	
	/**
	 * Accessor to Player's name
	 * @return Player's name
	 */
	public String getName();
	
}
