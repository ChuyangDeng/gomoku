package gomokugame;

import java.util.LinkedList;
import java.util.Queue;

public class AIPlayer {
	
	private int maxDepth = 2;
	private int paddingDis = 1;
	
	class Node {
		Position p;
		int f;
		Node (Position p, int f) {
			this.p = p;
			this.f = f;
		}
	}
	
	public Position getMove(Board board, Player currentPlayer) {
		return makeMove(board.getBoard(), currentPlayer);
	}
	
	public Position makeMove(Player[][] board, Player player) {
		Node move = null;
		if (player == Player.BLACK) {
			move = defense(board, player);
			if (move == null) {
				System.out.println("no defense");
				move = maxScore(board, -10000000, 10000000, 0);
			}
		} else {
			move = defense(board, player);
			if (move == null) {
				move = minScore(board, -10000000, 10000000, 0);
				System.out.println("no defense");
			}
		}
		return move.p;
	}
	
	public Node defense(Player[][] board, Player player) {
		Node defense = null;
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
						 if (board[i + row[2][0]][j + row[2][1]] == null) {
							 Position position = new Position(i + row[2][0], j + row[2][1]);
							 defense = new Node(position, 0);
							 break;
						 }
						 if (board[i + row[3][0]][j + row[3][1]] == null) {
							 Position position = new Position(i + row[3][0], j + row[3][1]);
							 defense = new Node(position, 0);
							 break;
						 }
					 }
					 if (board[i + col[0][0]][j + col[0][1]] == opponent && board[i + col[1][0]][j + col[1][1]] == opponent) {
						 if (board[i + col[2][0]][j + col[2][1]] == null) {
							 Position position = new Position(i + col[2][0], j + col[2][1]);
							 defense = new Node(position, 0);
							 break;
						 }
						 if (board[i + col[3][0]][j + col[3][1]] == null) {
							 Position position = new Position(i + col[3][0], j + col[3][1]);
							 defense = new Node(position, 0);
							 break;
						 }
					 }
					 if (board[i + diag1[0][0]][j + diag1[0][1]] == opponent && board[1 + diag1[1][0]][j + diag1[1][1]] == opponent) {
						 if (board[i + diag1[2][0]][j + diag1[2][1]] == null) {
							 Position position = new Position(i + diag1[2][0], j + diag1[2][1]);
							 defense = new Node(position, 0);
							 break;
						 }
						 if (board[i + diag1[3][0]][j + diag1[3][1]] == null) {
							 Position position = new Position(i + diag1[3][0], j + diag1[3][1]);
							 defense = new Node(position, 0);
							 break;
						 }
					 }
					 if (board[i + diag2[0][0]][j + diag2[0][1]] == opponent && board[1 + diag2[1][0]][j + diag2[1][1]] == opponent) {
						 if (board[i + diag2[2][0]][j + diag2[2][1]] == null) {
							 Position position = new Position(i + diag2[2][0], j + diag2[2][1]);
							 defense = new Node(position, 0);
							 break;
						 }
						 if (board[i + diag2[3][0]][j + diag2[3][1]] == null) {
							 Position position = new Position(i + diag2[3][0], j + diag2[3][1]);
							 defense = new Node(position, 0);
							 break;
						 }
					 }
				 }
			 }
		}
		if (defense != null) System.out.println("Defense: " + defense.toString());
		return defense;
	}
	
	private int evaluate(Player[][] board) {
		double Ba1 = numOfActive(board, Player.BLACK, 1);
		double Wa1 = numOfActive(board, Player.WHITE, 1);
		double Ba2 = numOfActive(board, Player.BLACK, 2);
		double Wa2 = numOfActive(board, Player.WHITE, 2);
		double Ba3 = numOfActive(board, Player.BLACK, 3);
		double Wa3 = numOfActive(board, Player.WHITE, 3);
		return (int) (10 * (Ba1 - Wa1) + 100 * (Ba2 - Wa2) + 400 * (Ba3 - Wa3));
	}
	
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
	
	private void bfs(Player[][] board, int[][] dis) {
		int R = 19, C = 19;
		int[][] d = { { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 },
				{ -1, -1 }, { 0, -1 }, { 1, -1 } };
		Queue<Position> q = new LinkedList<Position>();
		for (int x = 0; x < R; x++)
			for (int y = 0; y < C; y++)
				if (dis[x][y] == 0)
					q.add(new Position(x, y));
		if(q.isEmpty())dis[R/2][C/2]=1;
		while (!q.isEmpty()) {
			Position t = q.poll();
			int i, j;
			for (int k = 0; k < 8; k++) {
				i = t.getX() + d[k][0];
				j = t.getY() + d[k][1];
				if (validPosition(i, j) && dis[i][j] == -1) {
					dis[i][j] = dis[t.getX()][t.getY()] + 1;
					if (dis[i][j] < paddingDis)
						q.add(new Position(i, j));
				}
			}
		}
	}
	
	private Node maxScore(Player[][] board, int min, int max, int depth) {
		if (playerWins(board, Player.BLACK)) {
			return new Node(null, 1000000);
		} else if (playerWins(board, Player.WHITE)) {
			return new Node(null, -1000000);
		} else if (depth > maxDepth) {
			return new Node(null, evaluate(board));
		}
		
		int R = 19, C = 19;
		int[][] dis = new int[R][C];
		/* -1: empty; 0: occupied */
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				dis[i][j] = board[i][j] == null ? -1 : 0;
			}
		}
		bfs(board, dis);
		
		int f = -10000000, t;
		Position p = new Position(-1, -1);
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (dis[i][j] > 0) {
					board[i][j] = Player.BLACK;
					t = minScore(board, min, max, depth + 1).f;
					if (t > f) {
						f = t;
						p.setX(i);
						p.setY(j);
					}
					board[i][j] = null;
					if (f >= max) return new Node(p, f);
					min = Math.max(min, f);
				}
			}
		}
		return new Node(p, f);
	}
	
	private Node minScore(Player[][] board, int min, int max, int depth) {
		if (playerWins(board, Player.BLACK)) {
			return new Node(null, 100000);
		} else if (playerWins(board, Player.WHITE)) {
			return new Node(null, -100000);
		} else if (depth > maxDepth) {
			return new Node(null, evaluate(board));
		}
		int R = 19, C = 19;
		int[][] dis = new int[R][C];
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				dis[i][j] = board[i][j] == null ? -1 : 0;
		bfs(board, dis);
		int f = 10000000, t;
		Position p = new Position(-1, -1);
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++) {
				if (dis[i][j] > 0) {
					board[i][j] = Player.WHITE;
					t = maxScore(board, min, max, depth + 1).f;
					if (t < f) {
						f = t;
						p.setX(i);
						p.setY(j);
					}
					board[i][j] = null;
					if (f <= min) return new Node(p, f);
					max = Math.min(max, f);
				}
			}
		return new Node(p, f);
	}
	
	public static boolean validPosition(int x, int y) {
		return x >= 0 && x < 19 && y >= 0 && y < 19;
	}
	
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
