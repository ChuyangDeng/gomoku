package gomokugame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * This is the controller class. It holds a view object and a board (model) object and serves as the intermediate object between them
 * @author paula
 *
 */
public class GameController {

	private View view;
	private final Canvas canvas;
	private Board board;
	private Player currentplayer = Player.WHITE;
	private boolean gameover = true;
	String gamemode;
	private AIPlayer computerplayer = new AIPlayer();

	/**
	 * Constructor
	 * @param view view object
	 * @param board game board (model)
	 */
	public GameController(View view, Board board){
		this.view = view;
		this.board = board;
		canvas = view.getCanvas();
	}
	
	public void initContent(GridPane root) {
		root.add(canvas, 0, 0);
		canvas.setOnMouseClicked(event -> onClickAction(event));
		view.drawBoard(board);
	}

	public void onClickAction(MouseEvent event) {
		if (gameover) {newGame("New Game"); return;}
		int x = view.translateToBoard(event.getX());
		int y = view.translateToBoard(event.getY());
		if (x == -1 || y == -1) return; // if outside the board, do nothing
		if (!takeTurn(x, y, "Player")) return;
		
		// if playing against an ai, let it take a turn
		if (!gamemode.equals("PVP")) {
			takeComputerTurn();
		}
	}
	
	private boolean takeTurn(int x, int y, String s) {
		boolean legalmove = board.isEmpty(x, y);
		if (!legalmove) return false;
		if (board.isFull()) {
			newGame("Draw!");
			return false;
		}
		
		if (currentplayer == Player.BLACK){
			board.updateBoard(x, y, "black");
		} else if (currentplayer == Player.WHITE){
			board.updateBoard(x, y, "white");
		}	
	
		view.drawBoard(board);
		
		Player winner = board.checkWinner();
		if (winner != null) {
			newGame("Winner is: " + winner);
			return false;
		}

		// if the game hasn't ended, give the turn to the other player
		currentplayer = currentplayer == Player.WHITE ? Player.BLACK : Player.WHITE;
		return true;
	}
	
	public boolean takeComputerTurn() {
		Position move = computerplayer.nextMove(board, currentplayer);
		System.out.println(move);
		return takeTurn(move.getX(), move.getY(), "Computer");
	}
	
	public void newGame(String message) {
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
			
			view.drawBoard(board);
		});
	}
	
}
