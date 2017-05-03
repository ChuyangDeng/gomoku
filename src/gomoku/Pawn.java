package gomoku;

import javafx.stage.Stage;

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
	private Position position;
	
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
		position = new Position(x, y);
	}
	
	/**
	 * Accessor to Pawn's position
	 * @return
	 */
	public Position getPosition(){
		return position;
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
		return position.getX();
	}
	
	/**
	 * Accessor to this Pawn's y coordination.
	 * @return
	 */
	public int getY() {
		return position.getY();
	}
	
}
