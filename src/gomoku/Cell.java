package gomoku;

/**
 * This class keeps track of a cell on the game board
 * @author paula
 *
 */
public class Cell {
	private Pawn p;
	private Position position;
	
	/**
	 * Constructor
	 * @param x xpos
	 * @param y ypos
	 */
	public Cell(int x, int y){
		p = null;
		position = new Position(x, y);
	}
	
	/**
	 * set pawn at cell
	 * @param p pawn
	 */
	public void setPawn(Pawn p){
		this.p = p;
	}
	
	/**
	 * Checks if the cell is occupied
	 * @return true if occupied, false otherwise
	 */
	public boolean isOccupied(){
		return p != null;
	}
	
	/**
	 * Accessor to the Pawn on this Cell
	 * @return
	 */
	public Pawn getPawn() {
		return p;
	}
	
	/**
	 * Get x position
	 * @return x
	 */
	public int getX(){
		return position.getX();
	}
	
	/**
	 * Get y position
	 * @return y
	 */
	public int getY(){
		return position.getY();
	}
	
}
