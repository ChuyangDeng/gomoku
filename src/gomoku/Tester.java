package gomoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This is the tester class. It creates player objects and a GomokuGame object and runs the game in the console.
 * @author paula
 *
 */
public class Tester extends Application {
	
	static Player player1;
	static Player player2;

	public static void main(String[] args) throws Exception {
		launch(args);
		GomokuGame game = new GomokuGame(15, player1, player2);
		game.playGame();
	}
	
	/**
	 * Start the game
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();
		Button startBtn = new Button("START");
		Button exitBtn = new Button("EXIT");
		Button blackBtn = new Button("BLACK");
		Button whiteBtn = new Button("WHITE");
		
		startBtn.setLayoutX(100); startBtn.setLayoutY(50);
		startBtn.setMinWidth(root.getPrefWidth());
		Board board = new Board(15);
		startBtn.setOnAction(board);
		
		exitBtn.setLayoutX(100); exitBtn.setLayoutY(100);
		exitBtn.setMinWidth(root.getPrefWidth());
		exitBtn.setOnAction(e -> {
			System.out.println("Exit the game ... ");
			System.exit(0);
		});
		
		/* TODO: set human player to black */
		blackBtn.setLayoutX(200); blackBtn.setLayoutY(50);
		blackBtn.setMinWidth(root.getPrefWidth());
		blackBtn.setOnAction(e -> {
			player1 = new HumanPlayer("black", "human player", 15);
			player2 = new RandomPlayer("white", "random player", 15);
			System.out.println("BLACK: " + player1.getName() + " vs " + "WHITE: " + player2.getName());
		});
		
		/* TODO: set computer player to white */
		whiteBtn.setLayoutX(200); whiteBtn.setLayoutY(100);
		whiteBtn.setMinWidth(root.getPrefWidth());
		whiteBtn.setOnAction(e -> {
			player1 = new HumanPlayer("white", "human player", 15);
			player2 = new RandomPlayer("black", "random player", 15);
			System.out.println("BLACK: " + player2.getName() + " vs " + "WHITE: " + player1.getName());
		});
		
		root.getChildren().addAll(startBtn, exitBtn, blackBtn, whiteBtn);
		Scene scene = new Scene(root, 350, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
