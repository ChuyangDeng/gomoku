package gomoku;

/**
 * 
 * This is a simple class to represent pawn's position on a chessboard.
 * Using this class can avoid too many int[]
 * @author chuyangdeng
 *
 */
public class Position {
	
	/**
	 * Instance variables
	 */
	int x;
	int y;
	
	/**
	 * Constructor of Position
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Accessor to x
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Accessor to y
	 * @return
	 */
	public int getY() {
		return y;
	}
	
}
