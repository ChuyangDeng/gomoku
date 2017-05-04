package gomoku;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This is the cell class using java fx
 * @author paula
 *
 */
public class CellFX extends StackPane{
	
	private PawnFX p;
	private Position position;
	private Line hline;
	private Line vline = new Line();
	private Text text;
	
	int boardLength = TesterFX.size * TesterFX.cellSize;
	
	/**
	 * Constructor
	 * @param x x location of cell
	 * @param y y location of cell
	 */
	public CellFX(Player player, int x, int y){
		Rectangle cell = new Rectangle(TesterFX.cellSize, TesterFX.cellSize);
		cell.setFill(Color.GRAY);
		setAlignment(Pos.TOP_LEFT);
		hline = new Line(0, (y + 0.5) * TesterFX.cellSize, (1 + TesterFX.size) * TesterFX.cellSize, (y + 0.5) * TesterFX.cellSize);
		vline = new Line((x + 0.5) * TesterFX.cellSize, 0, (x + 0.5) * TesterFX.cellSize, (1 + TesterFX.size) * TesterFX.cellSize);
//		hline.setStroke(Color.BLACK);
//		hline.relocate(x * TesterFX.cellSize, y * TesterFX.cellSize);
//		setStroke(Color.BLACK);
		getChildren().addAll(cell, hline, vline);
		relocate(x * TesterFX.cellSize, y * TesterFX.cellSize);
//		p = new PawnFX();
//		setOnMousePressed(e -> {
//			if (!isOccupied()){
//				PawnFX pawn = new PawnFX("black", x, y);
//				p = pawn;
//            	getChildren().add(pawn);
//			}
//		});
		
		
		p = null;
		position = new Position(x, y);
	}
	
	
	/**
	 * Get hline
	 * @return
	 */
	public Line getHline() {
		return hline;
	}
	
	/**
	 * Get vline
	 * @return
	 */
	public Line getVline() {
		return vline;
	}

	/**
	 * set pawn at cell
	 * @param p pawn
	 */
	public void setPawn(PawnFX p){
		this.p = p;
		position = new Position(p.getX(), p.getY());
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
