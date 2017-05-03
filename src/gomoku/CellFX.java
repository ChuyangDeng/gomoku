package gomoku;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This is the cell class using java fx
 * @author paula
 *
 */
public class CellFX extends Rectangle{
	
	private PawnFX p;
	private Position position;
	
	/**
	 * Constructor
	 * @param x x location of cell
	 * @param y y location of cell
	 */
	public CellFX(int x, int y){
		setWidth(TesterFX.cellSize);
		setHeight(TesterFX.cellSize);
//		setStyle("-fx-border-color: black");
		setStroke(Color.BLACK);
		setFill(Color.WHITE);
		
		relocate(x * TesterFX.cellSize, y * TesterFX.cellSize);
		
		p = null;
		position = new Position(x, y);
	}
	
	/**
	 * set pawn at cell
	 * @param p pawn
	 */
	public void setPawn(PawnFX p){
		this.p = p;
//		position = new Position(p.getX(), p.getY());
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
	public PawnFX getPawn() {
		return p;
	}
	
	/**
	 * Get x position
	 * @return x
	 */
	public int getPositionX(){
		return position.getX();
	}
	
	/**
	 * Get y position
	 * @return y
	 */
	public int getPositionY(){
		return position.getY();
	}
	
}
