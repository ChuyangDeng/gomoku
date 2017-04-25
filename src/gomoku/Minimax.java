package gomoku;

import java.util.ArrayList;
import java.util.List;

/**
 * Computer strategy using minimax tree
 * @author paula
 *
 */
public class Minimax {
	
	/**
	 * Instance variables
	 * For every legal position, copy and availables will be updated,
	 * minScore and maxScore will then be calculated based on the copies.
	 */
	private Board board;
	private Board copy;
	private List<Position> availables;
	private String color;
	private double minResult;
	private double maxResult;
	private double best;
	private Position move;
	
	/**
	 * Constructor of the Minimax class
	 * @param board
	 * @param color
	 */
	public Minimax(Board board, String color) {
		this.board = board;
		this.color = color;
		minResult = Double.MAX_VALUE;
		maxResult =0.0;
		best = 0.0;
		move = null;
	}
	
	/**
	 * Use mini-max algorithm to determine the next move
	 * @param availables
	 * @return
	 */
	public Position getMove(List<Position> positions) {
		for (int i = 0; i < positions.size(); i++) { // check for every legal position
			Position position = positions.get(i);
			positions.remove(position);
			Pawn pawn = new Pawn(color);
			pawn.setPawn(position.getX(), position.getY());
			board.setMove(pawn);
			copy = copyBoard(board);
			availables = copyAvailablePositions(positions, position);
			double currentScore = minScore(availables, 0);
			if (currentScore > best) move = position;
			board.removePawn(position);
			availables.add(i, position);
		}
		return move;
	}
	
	/**
	 * Helper method to calculate the minimal score
	 * @param index
	 * @return
	 */
	private double minScore(List<Position> availables, int index) {
		if (index == availables.size() - 1) return copy.evaluate(color);
		else {
			double score = Double.MAX_VALUE;
			for (int i = index; i < availables.size(); i++) {
				Position position = availables.get(i);
				Pawn pawn = new Pawn(color);
				pawn.setPawn(position.getX(), position.getY());
				copy.setMove(pawn);
				score = maxScore(availables, i + 1);
				minResult = Math.min(minResult, score);
			}
			return minResult;
		}
	}
	
	/**
	 * Helper method to calculate the maximal score
	 * @param index
	 * @return
	 */
	private double maxScore(List<Position> availables, int index) {
		if (index == availables.size() - 1) return copy.evaluate(color);
		else {
			double score = Double.MAX_VALUE;
			for (int i = index; i < availables.size(); i++) {
				Position position = availables.get(i);
				Pawn pawn = new Pawn(color);
				pawn.setPawn(position.getX(), position.getY());
				copy.setMove(pawn);
				score = minScore(availables, i + 1);
				maxResult = Math.max(maxResult, score);
			}
			return maxResult;
		}
	}
	
	/**
	 * Helper method to make a deep copy of chessboard
	 * @param board
	 * @return
	 */
	private Board copyBoard(Board board) {
		int size = board.getSize();
		Board copy = new Board(size);
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				copy.getBoard()[row][col] = new Cell(row, col);
				Cell currentCell = board.getBoard()[row][col];
				if (currentCell.isOccupied()) {
					Pawn pawn = new Pawn(currentCell.getPawn().getColor());
					pawn.setPawn(row, col);
				}
			}
		}
		return copy;
	}
	
	/**
	 * Helper method to make a deep copy of available positions, eliminating the chosen position
	 * @param positions
	 * @return
	 */
	private List<Position> copyAvailablePositions(List<Position> positions, Position chosen) {
		List<Position> availables = new ArrayList<>();
		for (Position position : positions) {
			if (!position.equals(chosen)) availables.add(position);
		}
		return availables;
	}

}
