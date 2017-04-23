package gomuku;

/**
 * 
 * This class is the Pawn that Player can access to and maintain.
 * @author chuyangdeng
 *
 */
public class Pawn {
	
	/**
	 * Instance variables
	 */
	private String color;
	private int x;
	private int y;
	
	/**
	 * Constructor of Pawn
	 * @param color
	 * @param name
	 */
	public Pawn(String color) {
		this.color = color;
	}
	
	/**
	 * Set Pawn's position on chess board
	 * @param x
	 * @param y
	 */
	public void setPawn(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Accessor to this Pawn's color.
	 * @return
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Accessor to this Pawn's x coordination.
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Accessor to this Pawn's y coordination.
	 * @return
	 */
	public int getY() {
		return y;
	}
	

}
