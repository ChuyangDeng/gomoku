package gomuku;

/**
 * This class keeps track of a cell on the game board
 * @author paula
 *
 */
public class Cell {
	Pawn p;
	int x;
	int y;
	
	/**
	 * Constructor
	 * @param x xpos
	 * @param y ypos
	 */
	public Cell(int x, int y){
		p = null;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * set pawn at cell
	 * @param p pawn
	 */
	public void setPawn(Pawn p){
		if (p == null){
			this.p = p;
		}
	}
	
	/**
	 * Checks if the cell is occupied
	 * @return true if occupied, false otherwise
	 */
	public boolean isOccupied(){
		return p != null;
	}
	
	/**
	 * Get x position
	 * @return x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Get y position
	 * @return y
	 */
	public int getY(){
		return y;
	}
	
}
