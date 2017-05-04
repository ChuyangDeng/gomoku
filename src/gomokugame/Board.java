package gomokugame;

public class Board {
	static final int size = 15;
	private Player[][] board;
	
	public Board(){
		board = new Player[size][size];
	}
	
	/**
	 * Checks whether given cell is occupied
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return
	 */
	public boolean isOccupied(int x, int y){
		return board[x][y] != null;
	}
}
