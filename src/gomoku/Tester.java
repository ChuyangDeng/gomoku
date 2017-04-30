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


	public static void main(String[] args) throws Exception {
		launch(args);
		Player player1 = new HumanPlayer("black", "human player", 15);
		Player player2  = new RandomPlayer("white", "computer player", 15);
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
		startBtn.setLayoutX(100); startBtn.setLayoutY(50);
		startBtn.setMinWidth(root.getPrefWidth());
		Board board = new Board(15);
		startBtn.setOnAction(board);
		
		exitBtn.setLayoutX(100);
		exitBtn.setLayoutY(100);
		exitBtn.setMinWidth(root.getPrefWidth());
		exitBtn.setOnAction(e -> {
			System.out.println("Exit the game ... ");
			System.exit(0);
		});
		
		root.getChildren().addAll(startBtn, exitBtn);
		Scene scene = new Scene(root, 300, 180);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
