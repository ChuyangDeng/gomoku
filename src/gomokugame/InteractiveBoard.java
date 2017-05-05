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

public class InteractiveBoard extends Application {
	
	private final Controller controller = new Controller();
	private final Canvas canvas = new Canvas(700, 700);
	private Board board = new Board();
	private double margin = 10;
	private int dot_radius = 3;
	private int stone_radius = 15;
	private Player currentplayer = Player.WHITE;
	private boolean gameover = true;
	private String gamemode;
	private PlayerInterface computerplayer = new RandomPlayer();
	private Position position = new Position(-1, -1);
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage stage) {
		stage.setTitle("Gomoku");
		GridPane root = new GridPane();
		initContent(root);
		stage.setScene(new Scene(root));
		stage.show();
		newGame("New Game");
		
		if (gamemode.equals("EVP")) {
			takeComputerTurn();
		}
	}
	
	private void initContent(GridPane root) {
		root.add(canvas, 0, 0);
		canvas.setOnMouseClicked(event -> controller.onClickAction(event));
		drawBoard();
	}

	private class Controller {
		public void onClickAction(MouseEvent event) {
			if (gameover) {newGame("New Game"); return;}
			int x = translateToBoard(event.getX());
			int y = translateToBoard(event.getY());
			if (x == -1 || y == -1) return; // if outside the board, do nothing
			if (!takeTurn(x, y, "Player")) return;
			
			// if playing against an ai, let it take a turn
			if (!gamemode.equals("PVP")) {
				takeComputerTurn();
			}
		}
	}
	
	private boolean takeTurn(int x, int y, String s) {
		boolean legalmove = board.isEmpty(x, y);
		if (!legalmove) return false;
		
		if (board.isFull()) {
			newGame("Draw!");
			return false;
		} else {
			if (currentplayer == Player.BLACK){
				board.updateBoard(x, y, "black");
			} else if (currentplayer == Player.WHITE){
				board.updateBoard(x, y, "white");
			}	
		}
		
		drawBoard();
		
//		Player winner = board.checkWinner();
		Position current = new Position(x, y);
		Player winner = board.checkWinner2(current);
		if (winner != null) {
			newGame("Winner is: " + winner);
			return false;
		}
		
		position.setX(x);
		position.setY(y);
		
		// if the game hasn't ended, give the turn to the other player
		currentplayer = currentplayer == Player.WHITE ? Player.BLACK : Player.WHITE;
		return true;
	}
	
	public boolean takeComputerTurn() {
		Position move = computerplayer.nextMove(board, currentplayer);
		System.out.println(move);
		return takeTurn(move.getX(), move.getY(), "Computer");
	}
	
	private void newGame(String message) {
		List<String> choices = new ArrayList<>();
		choices.add("PVP");
		choices.add("PVE");
		choices.add("EVP");

		if (gamemode == null) gamemode = "PVP";
		ChoiceDialog<String> dialog = new ChoiceDialog<>(gamemode, choices);
		dialog.setTitle("Gomoku");
		dialog.setHeaderText(message);
		dialog.setContentText("Choose gamemode:");

		Optional<String> result = dialog.showAndWait();
		result.ifPresent(letter -> {
			gamemode = letter;
			gameover = false;
			board = new Board();
			currentplayer = Player.WHITE;
			
			if (gamemode == "EVP")
				takeComputerTurn();
			
			drawBoard();
		});
	}
	
	private void drawBoard() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setLineWidth(1.0);
		
		// border should be ... LIGHTGRAY!
		gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getHeight(), canvas.getHeight());
        
        // draw the board-background
		gc.setFill(Color.BURLYWOOD);
        gc.fillRect(translateToCanvas(0), translateToCanvas(0), translateToCanvas(board.getSize()-1)-margin, translateToCanvas(board.getSize()-1)-margin);
		
		// draw the lines that make up the board
		for (int x = 0; x < board.getSize(); x++)
			gc.strokeLine(translateToCanvas(x), translateToCanvas(0), translateToCanvas(x), translateToCanvas(board.getSize()-1));
		for (int y = 0; y < board.getSize(); y++)
			gc.strokeLine(translateToCanvas(0), translateToCanvas(y), translateToCanvas(board.getSize()-1), translateToCanvas(y));
		
		// putting in the traditional dots on the go-board
		gc.setFill(Color.BLACK);
		for (int x = 3; x < board.getSize(); x+=6)
			for (int y = 3; y < board.getSize(); y+=6)
				gc.fillOval(translateToCanvas(x)-dot_radius, translateToCanvas(y)-dot_radius, dot_radius*2, dot_radius*2);
		
		// draw the stones
		gc.setLineWidth(0.2);
		gc.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.9, true, CycleMethod.REFLECT, new Stop(0.35, Color.BLACK), new Stop(1.0, Color.DARKGRAY)));
		for (int x = 0; x < board.getSize(); x++)
			for (int y = 0; y < board.getSize(); y++)
				if (board.getPlayer(x, y) == Player.BLACK) {
					gc.fillOval(translateToCanvas(x)-stone_radius, translateToCanvas(y)-stone_radius, stone_radius*2, stone_radius*2);
					gc.strokeOval(translateToCanvas(x)-stone_radius, translateToCanvas(y)-stone_radius, stone_radius*2, stone_radius*2);
				}
		gc.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.7, true, CycleMethod.REFLECT, new Stop(0.0, Color.WHITE), new Stop(1.0, Color.LIGHTGRAY)));
		for (int x = 0; x < board.getSize(); x++)
			for (int y = 0; y < board.getSize(); y++)
				if (board.getPlayer(x, y) == Player.WHITE) {
					gc.fillOval(translateToCanvas(x)-stone_radius, translateToCanvas(y)-stone_radius, stone_radius*2, stone_radius*2);
					gc.strokeOval(translateToCanvas(x)-stone_radius, translateToCanvas(y)-stone_radius, stone_radius*2, stone_radius*2);
				}
	}
	
	private double translateToCanvas(double value) {
		return margin + value * (canvas.getHeight() - 2 * margin) / (board.getSize()-1);
	}
	
	private int translateToBoard(double value) {
		if (margin/2 > value || value > canvas.getHeight() - margin/2) return -1;
		
		double tile_width = (canvas.getHeight() - 2 * margin) / (board.getSize()-1);
		for (int i = 0; i < board.getSize()+1; i++)
			if (value - margin/2 < i * tile_width + tile_width/2)
				return i;
		
		return -1;
	}

}
