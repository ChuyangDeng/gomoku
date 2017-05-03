package gomoku;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * This is the tester class. It creates player objects and a GomokuGame object and runs the game in the console.
 * @author paula
 *
 */
public class TesterFX extends Application {
	
	static Player player1;
	static Player player2;
	final static int size = 15;
	final static int cellSize = 50;
	
	static GomokuGame game;
	Stage window;
	Scene boardScene;
	
	private Group cellGroup = new Group();
	private Group pawnGroup = new Group();

	public static void main(String[] args) throws Exception {
		launch(args);
	}
	
	/**
	 * Start the game
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		Pane root = new Pane();
		root.setPrefSize(size * cellSize, size * cellSize);
		
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j ++) {
                CellFX cell = new CellFX(i, j);
//                cell.setStyle("-fx-border-color: black");
                cellGroup.getChildren().add(cell);
            }
        }
		
		root.getChildren().addAll(cellGroup, pawnGroup);
		Scene scene = new Scene(root);
		window.setTitle("Welcome to Gomoku");
		window.setScene(scene);
		window.show();
	}

}
