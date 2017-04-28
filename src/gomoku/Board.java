package gomoku;

import java.util.ArrayList;
import java.util.List;

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
	 * Prints the game board in terminal
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
	 * Check for 5 consecutive cells of same color
	 * @return
	 */
	public boolean checkWinner() {
		return hasWinner;
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
