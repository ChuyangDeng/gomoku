package gomokugame;

import java.util.LinkedList;
import java.util.Queue;

public class AIPlayer {
	
	private int maxDepth = 1;
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
		Node res;
		if (player == Player.BLACK) {
			res = maxScore(board, -10000000, 10000000, 0);
		} else {
			res = minScore(board, -10000000, 10000000, 0);
		}
		return res.p;
	}
	
	private int evaluate(Player[][] board) {
		double Ba2 = numOfActive(board, Player.BLACK, 2);
		double Wa2 = numOfActive(board, Player.WHITE, 2);
		double Ba3 = numOfActive(board, Player.BLACK, 3);
		double Wa3 = numOfActive(board, Player.WHITE, 3);
		double Ba4 = numOfActive(board, Player.BLACK, 4);
		double Wa4 = numOfActive(board, Player.WHITE, 4);
		return (int) (10 * (Ba2 - Wa2) + 100 * (Ba3 - Wa3) + 400 * (Ba4 - Wa4));
	}
	
	private double numOfActive(Player[][] board, Player piece, int n) {
		int R = 15, C = 15;
		double res = 0;
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
							if (board[x + l * d[k][0]][y + l * d[k][1]] != piece)
								break;
						if (l == n)
							res+=deadEnd==0?1:0.4;
					}
		return res;
	}
	
	private void bfs(Player[][] board, int[][] dis) {
		int R = 15, C = 15;
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
			return new Node(null, 100000);
		} else if (playerWins(board, Player.WHITE)) {
			return new Node(null, -100000);
		} else if (depth > maxDepth) {
			return new Node(null, evaluate(board));
		}
		
		int R = 15, C = 15;
		int[][] dis = new int[R][C];
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
		return x >= 0 && x <= 15 && y >= 0 && y <= 15;
	}
	
	public static boolean playerWins(Player[][] board, Player player) {
		int R = 15, C = 15;
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
