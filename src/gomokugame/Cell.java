package gomokugame;

public class Cell {
	
	private Position position;
	private int score;
	
	public Cell (Position position, int score) {
		this.position = position;
		this.score = score;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
}
