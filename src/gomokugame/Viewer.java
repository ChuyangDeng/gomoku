package gomokugame;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

/**
 * This class controls the view in the Gomoku game. It contains methods to draw out the game board.
 * @author paula
 *
 */
public class Viewer {
	private final Canvas canvas = new Canvas(700, 700);
	private double margin = 10;
	private int dot_radius = 3;
	private int stone_radius = 15;
	private int boardSize = 19;
	
	/**
	 * Draws the game board on the interface
	 * @param board board object
	 */
	public void drawBoard(Board board) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setLineWidth(1.0);
		
		// border should be ... LIGHTGRAY!
		gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getHeight(), canvas.getHeight());
        
        // draw the board-background
		gc.setFill(Color.BURLYWOOD);
        gc.fillRect(translateToCanvas(0), translateToCanvas(0), translateToCanvas(boardSize-1)-margin, translateToCanvas(boardSize-1)-margin);
		
		// draw the lines that make up the board
		for (int x = 0; x < boardSize; x++)
			gc.strokeLine(translateToCanvas(x), translateToCanvas(0), translateToCanvas(x), translateToCanvas(boardSize-1));
		for (int y = 0; y < boardSize; y++)
			gc.strokeLine(translateToCanvas(0), translateToCanvas(y), translateToCanvas(boardSize-1), translateToCanvas(y));
		
		// putting in the traditional dots on the go-board
		gc.setFill(Color.BLACK);
		for (int x = 3; x < boardSize; x+=6)
			for (int y = 3; y < boardSize; y+=6)
				gc.fillOval(translateToCanvas(x)-dot_radius, translateToCanvas(y)-dot_radius, dot_radius*2, dot_radius*2);
		
		// draw the stones
		gc.setLineWidth(0.2);
		gc.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.9, true, CycleMethod.REFLECT, new Stop(0.35, Color.BLACK), new Stop(1.0, Color.DARKGRAY)));
		for (int x = 0; x < boardSize; x++)
			for (int y = 0; y < boardSize; y++)
				if (board.getPlayer(x, y) == Player.BLACK) {
					gc.fillOval(translateToCanvas(x)-stone_radius, translateToCanvas(y)-stone_radius, stone_radius*2, stone_radius*2);
					gc.strokeOval(translateToCanvas(x)-stone_radius, translateToCanvas(y)-stone_radius, stone_radius*2, stone_radius*2);
				}
		gc.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.7, true, CycleMethod.REFLECT, new Stop(0.0, Color.WHITE), new Stop(1.0, Color.LIGHTGRAY)));
		for (int x = 0; x < boardSize; x++)
			for (int y = 0; y < boardSize; y++)
				if (board.getPlayer(x, y) == Player.WHITE) {
					gc.fillOval(translateToCanvas(x)-stone_radius, translateToCanvas(y)-stone_radius, stone_radius*2, stone_radius*2);
					gc.strokeOval(translateToCanvas(x)-stone_radius, translateToCanvas(y)-stone_radius, stone_radius*2, stone_radius*2);
				}
	}
	
	/**
	 * This is the helper method to calculate location on canvas
	 * @param value
	 * @return position on canvas
	 */
	private double translateToCanvas(double value) {
		return margin + value * (canvas.getHeight() - 2 * margin) / (boardSize-1);
	}
	
	/**
	 * This is the helper method to calculate position on board
	 * @param value
	 * @return position on board
	 */
	public int translateToBoard(double value) {
		if (margin/2 > value || value > canvas.getHeight() - margin/2) return -1;
		
		double tile_width = (canvas.getHeight() - 2 * margin) / (boardSize-1);
		for (int i = 0; i < boardSize+1; i++)
			if (value - margin/2 < i * tile_width + tile_width/2)
				return i;
		
		return -1;
	}

	/**
	 * Accessor method to canvas
	 * @return
	 */
	public Canvas getCanvas(){
		return canvas;
	}
	
}
