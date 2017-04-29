package gomoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * User Interface allow user to choose color and interact with computer player
 * @author chuyangdeng
 *
 */
public class UserInterface extends Application{
	
	private static String humanColor;
	private static String computerColor;
	private static GomokuGame game;
	
	/**
	 * Entrance of the program
	 */
	public static void main(String[] args) {
		launch(args);
		Player human = new HumanPlayer(humanColor, "human player", 10);
		Player random = new RandomPlayer(computerColor, "random player", 10);
		game = new GomokuGame(10, human, random);
		game.playGame();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button blackBtn = new Button("BLACK");
		Button whiteBtn = new Button("WHITE");
		Button exitBtn = new Button("QUIT");
		blackBtn.setOnAction(e -> {
			System.out.println("You have chosen to play BLACK, computer will play WHITE.");
			humanColor = "black";
			computerColor = "white";
		});
		whiteBtn.setOnAction(e -> {
			System.out.println("You have chosen to play WHITE, computer will play BLACK");
			humanColor = "white";
			computerColor = "black";
		});
		exitBtn.setOnAction(e -> {
			System.out.println("Quit the game ...");
			System.exit(0);
		});
		
		VBox root = new VBox();
		root.getChildren().addAll(blackBtn, whiteBtn, exitBtn);
		Scene scene = new Scene(root, 300, 300);
		primaryStage.setTitle("Gomoku Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
