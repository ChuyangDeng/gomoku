package gomokugame;

/**
 * 
 * This class represents a Position and its potential score if the AIPlayer sets a pawn on the position.
 * @author chuyangdeng
 *
 */
public class Cell {
	
	/**
	 * Instanve variables
	 */
	private Position position;
	private int score;
	
	/**
	 * Constructor of the class
	 * @param position
	 * @param score
	 */
	public Cell (Position position, int score) {
		this.position = position;
		this.score = score;
	}
	
	/**
	 * Accessor to Position
	 * @return
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Accesor to score
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Mutator of position
	 * @param position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	/**
	 * Mutator of score
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
}
