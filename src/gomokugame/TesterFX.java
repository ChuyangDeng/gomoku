package gomokugame;

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
import javafx.scene.shape.Line;
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
	private CellFX[][] board = new CellFX[size][size];
	
	static GomokuGame game;
	Stage window;
	Scene boardScene;
	
	private Group cellGroup = new Group();
	private Group pawnGroup = new Group();

	public static void main(String[] args) throws Exception {
		launch(args);
		Player player1 = new HumanPlayer("black", "human player", 15);
		Player player2  = new RandomPlayer("white", "computer player", 15);
		game = new GomokuGame(15, player1, player2);
		game.playGame();
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
                CellFX cell = new CellFX(null, i, j);
                board[i][j] = cell;
//                Line hline = cell.getHline();
//                Line vline = cell.getVline();
//                hline.relocate(i * TesterFX.cellSize, j * TesterFX.cellSize);
                final int finalI = i;
                final int finalJ = j;
                cell.setOnMousePressed(e -> {
                	PawnFX pawn = new PawnFX("black", finalI, finalJ);
                	cell.setPawn(pawn);
                	pawnGroup.getChildren().add(pawn);
                });
//                cell.setStyle("-fx-border-color: black");
//                cellGroup.getChildren().addAll(hline, vline, cell);
                cellGroup.getChildren().addAll(cell);
            }
        }
		
		root.getChildren().addAll(cellGroup, pawnGroup);
		Scene scene = new Scene(root);
		window.setTitle("Welcome to Gomoku");
		window.setScene(scene);
		window.show();
	}

}
