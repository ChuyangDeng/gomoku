package gomoku;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 
 * This is the Board class, it maintains Cells and check for winners.
 * @author chuyangdeng
 *
 */
public class Board {
	
	/**
	 * Instance variables
	 */
	private Cell[][] board;
	private boolean hasWinner;
	private String winner;
	private int size;
	
	/**
	 * Constructor of Board
	 * @param size is the size of chess board
	 */
	public Board(int size) {
		board = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new Cell(i, j);
			}
		}
		this.size = size;
		hasWinner = false;
		winner = null;
	}
	
	/**
	 * Accessor to the board
	 * @return
	 */
	public Cell[][] getBoard() {
		return board;
	}
	
	/**
	 * This method takes the current chessboard and evaluate a score for current player
	 * @param myPawns
	 * @param opponentPawns
	 * @return
	 */
	public long evaluate(String color) {
		List<List<Position>> positions = getPositions();
		long score = evaluateScore(positions, color);
		return score;
	}
	
	/**
	 * Helper method to get rows, columns and diagonal positions
	 * @return
	 */
	private List<List<Position>> getPositions() {
		List<List<Position>> positions = new ArrayList<>();
		/* ROWS */
		for (int row = 0; row < board.length; row++) {
			List<Position> rows = new ArrayList<>();
			for (int col = 0; col < board.length; col++) {
				Position position = new Position(row, col);
				rows.add(position);
			}
			positions.add(rows);
		}
		/* COLUMNS */
		for (int col = 0; col < board.length; col++) {
			List<Position> cols = new ArrayList<>();
			for (int row = 0; row < board.length; row++) {
				Position position = new Position(row, col);
				cols.add(position);
			}
			positions.add(cols);
		}
		/* DIAGONALS */
		/* upper left */
		int count = 0;
		while (count < board.length) {
			List<Position> diagonal = new ArrayList<>();
			for (int row = 0, col = count; row < board.length && col >= 0; row++, col--) {
				Position position = new Position(row, col);
				diagonal.add(position);
			}
			positions.add(diagonal);
			count++;
		}
		/* lower right */
		count = board.length - 1;
		while (count >= 0) {
			List<Position> diagonal = new ArrayList<>();
			for (int row = board.length - 1, col = count; row >= 0 && col < board.length; row--, col++) {
				Position position = new Position(row, col);
				diagonal.add(position);
			}
			positions.add(diagonal);
		}
		/* upper right */
		count = board.length - 1;
		while (count >= 0) {
			List<Position> diagonal = new ArrayList<>();
			for (int row = 0, col = count; row < board.length && col < board.length; row++, col++) {
				Position position = new Position(row, col);
				diagonal.add(position);
			}
			positions.add(diagonal);
		}
		/* lower left */
		count = 0;
		while (count < board.length) {
			List<Position> diagonal = new ArrayList<>();
			for (int row = board.length - 1, col = count; row >= 0 && col >= 0; row--, col--) {
				Position position = new Position(row, col);
				diagonal.add(position);
			}
			positions.add(diagonal);
		}
		return positions;
	}
	
	/**
	 * Evaluate Scores
	 * @param positions
	 * @param color
	 * @return
	 */
	private long evaluateScore(List<List<Position>> positions, String color) {
		long blackScore = 0, whiteScore = 0;
		int blacks = 0, whites = 0;
		for (int i = 0; i < positions.size() && !hasWinner; i++) {
			if (blacks > 0) blackScore += (long) Math.pow(2.0, blacks * 1.0);
			if (whites > 0) whiteScore += (long) Math.pow(2.0, whites * 1.0);
			blacks = 0; whites = 0;
			List<Position> consecutive = new ArrayList<>();
			for (Position position : consecutive) {
				Pawn currentPawn = board[position.getX()][position.getY()].getPawn();
				if (currentPawn != null) {
					if (currentPawn.getColor().equals("black")) {
						if (whites > 0) whiteScore += (long) Math.pow(2.0, whites * 1.0);
						whites = 0;
						if (blacks++ == 5) {
							blackScore = Long.MAX_VALUE;
							hasWinner = true; winner = "black"; break;
						}
					} else {
						if (blacks > 0) blackScore += (long) Math.pow(2.0, blacks * 1.0);
						blacks = 0;
						if (whites++ == 5) {
							whiteScore = Long.MAX_VALUE;
							hasWinner = true; winner = "white"; break;
						}
					}
				} else {
					if (blacks > 0) blackScore += (long) Math.pow(2.0, blacks * 1.0);
					if (whites > 0) whiteScore += (long) Math.pow(2.0, whites * 1.0);
					blacks = 0; whites = 0;
				}
			}
		}
		if (color.equals("black")) return blackScore - whiteScore;
		else return whiteScore - blackScore;
	}
	
	/**
	 * Prints game board in console
	 */
	public void printBoard(){
		for (int i = 0; i < size; i ++){
			for (int j = 0; j < size; j ++){
				if (board[i][j].isOccupied()){
					if (board[i][j].getPawn().getColor().equalsIgnoreCase("black")){
						System.out.print("X" + " ");
					} else {
						System.out.print("O" + " ");
					}
				} else {
					System.out.print("_" + " ");
				}
			}
			System.out.println("");
		}
	}
	
	/**
	 * Check whether last move creates 5 consecutive cells of same color
	 * @param lastMove last move made by player
	 * @return true if there is a winner, false otherwise
	 */
	public boolean checkWinner(Cell lastMove) {
		if (isValidCell(lastMove)){
			if (checkRowWinner(lastMove) || checkColWinner(lastMove) || checkLDiagWinner(lastMove) || checkRDiagWinner(lastMove)){
				winner = lastMove.getPawn().getColor();
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Helper method to check for 5 consecutive cells of same color in a column
	 * @param lastMove last move made by player
	 * @return true if there are 5 consecutive cells of same color in a column
	 */
	private boolean checkColWinner(Cell lastMove){
		int x = lastMove.getX();
		int y = lastMove.getY();
		String color = lastMove.getPawn().getColor();
		int consecutive = 0;
		
		for (int i = x - 4; i < x + 1; i ++){
			for (int j = 0; j < 5; j ++){
				try{
					if (board[i + j][y].getPawn().getColor().equals(color) && color != null){
						consecutive ++;
					} else {
						break;
					}
				} catch (IndexOutOfBoundsException e){
					break;
				} catch (NullPointerException e){
					break;
				}
			}
			if (consecutive == 5) return true;
			consecutive = 0;
		}
		
		return false;
	}
	
	/**
	 * Helper method to check for 5 consecutive cells of same color in a row
	 * @param lastMove last move made by player
	 * @return true if there are 5 consecutive cells of same color in a row
	 */
	private boolean checkRowWinner(Cell lastMove){
		int x = lastMove.getX();
		int y = lastMove.getY();
		String color = lastMove.getPawn().getColor();
		int consecutive = 0;
		
		for (int i = y - 4; i < y + 1; i ++){
			for (int j = 0; j < 5; j ++){
				try{
					if (board[x][i + j].getPawn().getColor().equals(color) && color != null){
						consecutive ++;
					} else {
						break;
					}
				} catch (IndexOutOfBoundsException e){
					break;
				} catch (NullPointerException e){
					break;
				}
			}
			if (consecutive == 5) return true;
			consecutive = 0;
		}
		
		return false;
	}
	
	/**
	 * Helper method to check for 5 consecutive cells of same color in the left diagonal
	 * @param lastMove last move made by player
	 * @return true if there are 5 consecutive cells of same color in the left diagonal
	 */
	private boolean checkLDiagWinner(Cell lastMove){
		int x = lastMove.getX();
		int y = lastMove.getY();
		String color = lastMove.getPawn().getColor();
		int consecutive = 0;
		
		for (int i = x - 4; i < x + 1; i ++){
			for (int j = 0; j < 5; j ++){
				try{
					if (board[i + j][y - x + i + j].getPawn().getColor().equals(color) && color != null){
						consecutive ++;
					} else {
						break;
					}
				} catch (IndexOutOfBoundsException e){
					break;
				} catch (NullPointerException e){
					break;
				}
			}
			if (consecutive == 5) return true;
			consecutive = 0;
		}
		
		return false;
	}
	
	/**
	 * Helper method to check for 5 consecutive cells of same color in the right diagonal
	 * @param lastMove last move made by player
	 * @return true if there are 5 consecutive cells of same color in the right diagonal
	 */
	private boolean checkRDiagWinner(Cell lastMove){
		int x = lastMove.getX();
		int y = lastMove.getY();
		String color = lastMove.getPawn().getColor();
		int consecutive = 0;
		
		for (int i = x - 4; i < x + 1; i ++){
			for (int j = 0; j < 5; j ++){
				try{
					if (board[i + j][x + y - i - j].getPawn().getColor().equals(color) && color != null){
						consecutive ++;
					} else {
						break;
					}
				} catch (IndexOutOfBoundsException e){
					break;
				} catch (NullPointerException e){
					break;
				}
			}
			if (consecutive == 5) return true;
			consecutive = 0;
		}
		
		return false;
	}
	
	/**
	 * Checks if the given cell is a valid cell on the board
	 * @param cell
	 * @return true if valid, false otherwise
	 */
	private boolean isValidCell(Cell cell){
		int x = cell.getX();
		int y = cell.getY();
		if (x >= 0 && x < size && y >= 0 && y < size){
			return true;
		}
		return false;
	}
	
	/**
	 * If hasWinner() returns true, this method will return the color of the winner.
	 * @return
	 */
	public String declareWinner() {
		return winner;
	}
	
	/**
	 * This method takes Player1's last Pawn and give a Cell to Player2.
	 * @param pawn
	 * @return a Cell to Player2
	 */
	public Cell setMove(Pawn pawn) {
		board[pawn.getX()][pawn.getY()].setPawn(pawn);
		return board[pawn.getX()][pawn.getY()];
	}
	
	/**
	 * This method removes a Pawn from the board for calculating scores
	 * @param position
	 */
	public void removePawn(Position position) {
		board[position.getX()][position.getY()].setPawn(null);
	}
	
	/**
	 * Accessor to chessboard's size
	 * @return
	 */
	public int getSize() {
		return board.length;
	}

	
}
