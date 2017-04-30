package gomoku;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 
 * This class is the Pawn that Player can access to and maintain.
 * @author chuyangdeng
 *
 */
public class Pawn implements EventHandler<MouseEvent> {
	
	/**
	 * Instance variables
	 */
	private String color;
	private Position position;
	
	private GridPane root;
	private Stage stage;
	
	/**
	 * Constructor of Pawn
	 * @param color
	 * @param name
	 */
	public Pawn(String color) {
		this.color = color;
		root = new GridPane();
		stage = new Stage();
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

	@Override
	public void handle(MouseEvent event) {
		final int size = 15;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				Rectangle square = new Rectangle();
				square.setWidth(30);
				square.setHeight(30);
				square.setFill(Color.BEIGE);
				
				root.add(square, row, col);
			}
		}
		Circle circle = new Circle();
		circle.setRadius(15);
		if (color.equals("black")) circle.setFill(Color.BLACK);
		if (color.equals("white")) circle.setFill(Color.WHITE);
		root.add(circle, getX(), getY());
		stage.setScene(new Scene(root, 450, 450));
		stage.show();
	}
	

}
