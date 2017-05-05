package gomokugame;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * This class implements Strategy Interface.
 * It applies Mini-Max algorithm to find out the best move for AIPlayer, and uses Alpha-Beta pruning to speed calculation.
 * @author chuyangdeng
 *
 */
public class AlphaBeta implements Strategy {
	
	/**
	 * Instance Variables
	 */
	private int maxDepth = 2;
	private int count = 1;
	
	@Override
	public Position getMove(Board board, Player currentPlayer) {
		return makeMove(board.getBoard(), currentPlayer);
	}
	
	@Override
	public Position makeMove(Player[][] board, Player player) {
		Cell move = null;
		if (player == Player.BLACK) {
			move = defense(board, player);
			if (move == null) {
				System.out.println("no defense");
				move = maxScore(board, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
			}
		} else {
			move = defense(board, player);
			if (move == null) {
				move = minScore(board, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
				System.out.println("no defense");
			}
		}
		return move.getPosition();
	}
	
	/**
	 * AIPlayer's strategy gives priority to defense.
	 * If it detects opponent have pawns 2 in a row (rows, columns, diagonals), it will prevent opponents from winning,
	 * by setting a pawn next to opponent's 3 contiguous pawns.
	 * @param board
	 * @param player
	 * @return
	 */
	public Cell defense(Player[][] board, Player player) {
		Cell defense = null;
		Player opponent;
		if (player == Player.BLACK) opponent = Player.WHITE;
		else opponent = Player.BLACK;
		int[][] row = {{0, -1}, {0, 1}, {0, -2}, {0, 2}};
		int[][] col = {{-1, 0}, {1, 0}, {2, 0}, {-2, 0}};
		int[][] diag1 = {{-1, -1}, {1, 1}, {-2, -2}, {2, 2}};
		int[][] diag2 = {{-1, 1}, {1, -1}, {-2, 2}, {2, -2}};
		for (int i = 2; i < board.length - 2; i++) {
			if (defense != null) break;
			 for (int j = 2; j < board.length - 2; j++) {
				 if (board[i][j] == opponent) {
					 if (board[i + row[0][0]][j + row[0][1]] == opponent && board[i + row[1][0]][j + row[1][1]] == opponent) {
						 if (board[i + row[2][0]][j + row[2][1]] == null && board[i + row[3][0]][j + row[3][1]] == null) {
							 Position position = new Position(i + row[2][0], j + row[2][1]);
							 defense = new Cell(position, 0);
							 break;
						 }
					 }
					 if (board[i + col[0][0]][j + col[0][1]] == opponent && board[i + col[1][0]][j + col[1][1]] == opponent) {
						 if (board[i + col[2][0]][j + col[2][1]] == null && board[i + col[3][0]][j + col[3][1]] == null) {
							 Position position = new Position(i + col[2][0], j + col[2][1]);
							 defense = new Cell(position, 0);
							 break;
						 }
					 }
					 if (board[i + diag1[0][0]][j + diag1[0][1]] == opponent && board[1 + diag1[1][0]][j + diag1[1][1]] == opponent) {
						 if (board[i + diag1[2][0]][j + diag1[2][1]] == null && board[i + diag1[3][0]][j + diag1[3][1]] == null) {
							 Position position = new Position(i + diag1[2][0], j + diag1[2][1]);
							 defense = new Cell(position, 0);
							 break;
						 }
					 }
					 if (board[i + diag2[0][0]][j + diag2[0][1]] == opponent && board[1 + diag2[1][0]][j + diag2[1][1]] == opponent) {
						 if (board[i + diag2[2][0]][j + diag2[2][1]] == null && board[i + diag2[3][0]][j + diag2[3][1]] == null) {
							 Position position = new Position(i + diag2[2][0], j + diag2[2][1]);
							 defense = new Cell(position, 0);
							 break;
						 }
					 }
				 }
			 }
		}
		if (defense != null) System.out.println("Defense: " + defense.toString());
		return defense;
	}
	
	/**
	 * This method collect information from a cell's neighbor positions that are 1/2/3 cells away and calculate a score.
	 * The further the cell is, the more weight it will be given.
	 * @param board
	 * @return
	 */
	private int evaluate(Player[][] board) {
		double black1 = numOfActive(board, Player.BLACK, 1);
		double white1 = numOfActive(board, Player.WHITE, 1);
		double black2 = numOfActive(board, Player.BLACK, 2);
		double white2 = numOfActive(board, Player.WHITE, 2);
		double black3 = numOfActive(board, Player.BLACK, 3);
		double white3 = numOfActive(board, Player.WHITE, 3);
		return (int) (10 * (black1 - white1) + 100 * (black2 - white2) + 400 * (black3 - white3));
	}
	
	/**
	 * This method is a helper method of evaluate().
	 * It calculates the number of AIPlayer's pawns that are 1/2/3 cells away from it.
	 * @param board
	 * @param player
	 * @param n
	 * @return
	 */
	private double numOfActive(Player[][] board, Player player, int n) {
		int R = 19, C = 19;
		double active = 0;
		int deadEnd,l;
		int[][] d = {{0, 1}, {1, 1}, {1, 0}, {1, -1}};
		for (int x = 1; x < R - 1; x++)
			for (int y = 1; y < C - 1; y++)
				for (int k = 0; k < 4; k++)
					if (validPosition(x + n * d[k][0], y + n * d[k][1])) {
						deadEnd=2;
						if(board[x - d[k][0]][y - d[k][1]] != null)
							deadEnd--;
						if(board[x + n * d[k][0]][y + n * d[k][1]] == null)
							deadEnd--;
						if(deadEnd==2)
							continue;
						for (l = 0; l < n; l++)
							if (board[x + l * d[k][0]][y + l * d[k][1]] != player)
								break;
						if (l == n)
							active += deadEnd == 0 ? 1 : 0.4;
					}
		return active;
	}
	
	/**
	 * This method goes through the entire board in bfs and set a cell's neighborhood in 8 directions as occupied temporarily.
	 * @param board
	 * @param dis
	 */
	private void bfs(Player[][] board, int[][] dis) {
		int R = 19, C = 19;
		int[][] d = { 
				{ 1, 0 },
				{ 1, 1 }, 
				{ 0, 1 }, 
				{ -1, 1 }, 
				{ -1, 0 },
				{ -1, -1 }, 
				{ 0, -1 }, 
				{ 1, -1 } 
		};
		Queue<Position> q = new LinkedList<Position>();
		for (int x = 0; x < R; x++)
			for (int y = 0; y < C; y++)
				if (dis[x][y] == 0)
					q.add(new Position(x, y));
		if(q.isEmpty()) dis[R/2][C/2]=1;
		while (!q.isEmpty()) {
			Position t = q.poll();
			int i, j;
			for (int k = 0; k < 8; k++) {
				i = t.getX() + d[k][0];
				j = t.getY() + d[k][1];
				if (validPosition(i, j) && dis[i][j] == -1) {
					dis[i][j] = dis[t.getX()][t.getY()] + 1;
					if (dis[i][j] < count)
						q.add(new Position(i, j));
				}
			}
		}
	}
	
	/**
	 * The AIPlayer assumes its opponent will make a perfect move at every turn, so it calculates the best score it can gets.
	 * @param board
	 * @param min
	 * @param max
	 * @param depth
	 * @return
	 */
	private Cell maxScore(Player[][] board, int min, int max, int depth) {
		if (playerWins(board, Player.BLACK)) {
			return new Cell(null, Integer.MAX_VALUE);
		} else if (playerWins(board, Player.WHITE)) {
			return new Cell(null, Integer.MIN_VALUE);
		} else if (depth > maxDepth) {
			return new Cell(null, evaluate(board));
		}
		
		int R = 19, C = 19;
		int[][] dis = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				dis[i][j] = board[i][j] == null ? -1 : 0;
			}
		}
		bfs(board, dis);
		
		int score = Integer.MIN_VALUE, temp;
		Position p = new Position(-1, -1);
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (dis[i][j] > 0) {
					board[i][j] = Player.BLACK;
					temp = minScore(board, min, max, depth + 1).getScore();
					if (temp > score) {
						score = temp;
						p.setX(i);
						p.setY(j);
					}
					board[i][j] = null;
					if (score >= max) return new Cell(p, score);
					min = Math.max(min, score);
				}
			}
		}
		return new Cell(p, score);
	}
	
	/**
	 * This method is called interactively with maxScore().
	 * For the opponent, AIPlayer assumes it makes the best move and takes the minimum of them all as its score.
	 * @param board
	 * @param min
	 * @param max
	 * @param depth
	 * @return
	 */
	private Cell minScore(Player[][] board, int min, int max, int depth) {
		if (playerWins(board, Player.BLACK)) {
			return new Cell(null, Integer.MAX_VALUE);
		} else if (playerWins(board, Player.WHITE)) {
			return new Cell(null, Integer.MIN_VALUE);
		} else if (depth > maxDepth) {
			return new Cell(null, evaluate(board));
		}
		int R = 19, C = 19;
		int[][] dis = new int[R][C];
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				dis[i][j] = board[i][j] == null ? -1 : 0;
		bfs(board, dis);
		int score = Integer.MAX_VALUE, temp;
		Position p = new Position(-1, -1);
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++) {
				if (dis[i][j] > 0) {
					board[i][j] = Player.WHITE;
					temp = maxScore(board, min, max, depth + 1).getScore();
					if (temp < score) {
						score = temp;
						p.setX(i);
						p.setY(j);
					}
					board[i][j] = null;
					if (score <= min) return new Cell(p, score);
					max = Math.min(max, score);
				}
			}
		return new Cell(p, score);
	}
	
	/**
	 * This method checks if a position is inside the chess board.
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean validPosition(int x, int y) {
		return x >= 0 && x < 19 && y >= 0 && y < 19;
	}
	
	/**
	 * This method checks if there is a winner already.
	 * @param board
	 * @param player
	 * @return
	 */
	public static boolean playerWins(Player[][] board, Player player) {
		int R = 19, C = 19;
		int[][] d = {
				{0, 1},
				{1, 1},
				{1, 0},
				{1, -1}
		};
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int k = 0; k < 4; k++) {
					if (validPosition(i + 4 * d[k][0], j + 4 * d[k][1])) {
						int l;
						for (l = 0; l < 5; l++) {
							if (board[i + l * d[k][0]][j + l * d[k][1]] != player) break;
						}
						if (l == 5) return true;
					}
				}
			}
		}
		return false;
	}

}
