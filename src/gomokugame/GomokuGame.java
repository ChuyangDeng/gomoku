package gomokugame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GomokuGame extends Application {

	private View view = new View();
	private Board board = new Board();
	private GameController controller = new GameController(view, board);
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Gomoku");
		GridPane root = new GridPane();
		controller.initContent(root);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		controller.newGame("New Game");
		
		if (controller.gamemode.equals("EVP")) {
			controller.takeComputerTurn();
		}
	}
	
}
