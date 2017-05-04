package gomokugame;

public class Board {
	static final int size = 19;
	private Player[][] board;
//	private Position previousMove;
	
	public Board(){
		board = new Player[size][size];
//		previousMove = new Position(-1, -1);
	}
	
	/**
	 * Checks whether given cell is occupied
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return true if ***not*** occupied, false otherwise
	 */
	public boolean isOccupied(int x, int y){
		return board[x][y] == null;
	}
	
	/**
	 * Checks whether the game board is full
	 * @return true if full, false otherwise
	 */
	public boolean isFull(){
		for (int i = 0; i < size; i ++){
			for (int j = 0; j < size; j ++){
				if (board[i][j] == null) return false;
			}
		}
		return true;
	}
	
	/**
	 * Update player at specified position of the gameboard
	 * @param x
	 * @param y
	 */
	public void updateBoard(int x, int y, String player){
		if (player.equalsIgnoreCase("black")){
			board[x][y] = Player.BLACK;
		} else if (player.equalsIgnoreCase("White")){
			board[x][y] = Player.WHITE;
		}
				
	}
	
	/**
	 * Gets player at given cell
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return player
	 */
	public Player getPlayer(int x, int y){
		return board[x][y];
	}
	
	public Player checkWinner() {
		for (int x = 0; x < board.length; x++)
			for (int y = 0; y < board[x].length; y++)
				if (board[x][y] != null) {
					if (x <= board.length - 5 && y <= board.length - 5) // if not close to lower right corner, check cross right-down
						if (board[x][y] == board[x+1][y+1] &&
							board[x][y] == board[x+2][y+2] &&
							board[x][y] == board[x+3][y+3] &&
							board[x][y] == board[x+4][y+4]) return board[x][y];
					if (x >= 5 && y <= board.length - 5) // if not close to lower left corner, check cross left-down
						if (board[x][y] == board[x-1][y+1] &&
							board[x][y] == board[x-2][y+2] &&
							board[x][y] == board[x-3][y+3] &&
							board[x][y] == board[x-4][y+4]) return board[x][y];
					
					if (x <= board.length - 5) // if not at right edge, check right
						if (board[x][y] == board[x+1][y] &&
							board[x][y] == board[x+2][y] &&
							board[x][y] == board[x+3][y] &&
							board[x][y] == board[x+4][y]) return board[x][y];
					if (y <= board.length - 5) // if not at bottom, check down
						if (board[x][y] == board[x][y+1] &&
							board[x][y] == board[x][y+2] &&
							board[x][y] == board[x][y+3] &&
							board[x][y] == board[x][y+4]) return board[x][y];
				}
		return null;
	}
	
	/**
	 * Checks if the last move gives a new winner
	 * @param previousMove last move
	 * @return player
	 */
	public Player checkWinner2(Position previousMove){
		if (previousMove != null && !isValidPosition(previousMove) && board[previousMove.getX()][previousMove.getY()] != null) return null;
		
		int x = previousMove.getX();
		int y = previousMove.getY();
		int consecutive = 0;
		
		/* check column winner*/
		for (int i = x - 4; i < x + 1; i ++){
			for (int j = 0; j < 5; j ++){
				try{
					if (board[i + j][y].equals(board[x][y])){
						consecutive ++;
					}
				} catch (IndexOutOfBoundsException e){
					break;
				} catch (NullPointerException e){
					break;
				}
			}
			if (consecutive == 5) return board[x][y];
		}
		
		/* reset consecutive and check row winner */
		consecutive = 0; 
		for (int i = y - 4; i < y + 1; i ++){
			for (int j = 0; j < 5; j ++){
				try{
					if (board[i + j][y].equals(board[x][y])){
						consecutive ++;
					}
				} catch (IndexOutOfBoundsException e){
					break;
				} catch (NullPointerException e){
					break;
				}
			}
			if (consecutive == 5) return board[x][y];
		}
		
		/* reset consecutive and check left diagonal winner */
		consecutive = 0; 
		for (int i = x - 4; i < x + 1; i ++){
			for (int j = 0; j < 5; j ++){
				try{
					if (board[i + j][y].equals(board[x][y])){
						consecutive ++;
					}
				} catch (IndexOutOfBoundsException e){
					break;
				} catch (NullPointerException e){
					break;
				}
			}
			if (consecutive == 5) return board[x][y];
		}
		
		/* reset consecutive and check right diagonal winner */
		consecutive = 0;
		for (int i = x - 4; i < x + 1; i ++){
			for (int j = 0; j < 5; j ++){
				try{
					if (board[i + j][y].equals(board[x][y])){
						consecutive ++;
					}
				} catch (IndexOutOfBoundsException e){
					break;
				} catch (NullPointerException e){
					break;
				}
			}
			if (consecutive == 5) return board[x][y];
		}
		
		return null;
	}
	
	/**
	 * Prints the board
	 * @return a string representation of the board
	 */
	public String printBoard(){
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < size; i ++){
			for (int j = 0; j < size; j ++){
				if (board[i][j] != null){
					if (board[i][j] == Player.BLACK){
						sb.append("X ");
					} else if (board[i][j] == Player.WHITE) {
						sb.append("O ");
					}
				} else {
					sb.append("_ ");
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Accessor method to the board
	 * @return board
	 */
	public Player[][] getBoard(){
		return board;
	}
	
	/**
	 * Accessor method to the size of game board
	 * @return
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * Helper method to check whether given position is valid on the board
	 * @param position
	 * @return
	 */
	private boolean isValidPosition(Position position){
		if (position.getX() >=0 && position.getX() < size && position.getY() >= 0 && position.getY() < size){
			return true;
		}
		return false;
	}
}
