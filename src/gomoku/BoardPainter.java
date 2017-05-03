package gomoku;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BoardPainter extends JPanel{
	
	public static final BoardPainter panel = new BoardPainter();
	public static final int X = 15;
	public static final int Y = 15;
	public static final int CELL_SIZE = 32;
	public static final int CELL_OFFSET = CELL_SIZE / 2;
	public static final int X_SIZE = CELL_SIZE * X;
	public static final int Y_SIZE = CELL_SIZE * Y;
	public static final int TITLE_BAR_HEIGHT = 30;
	
	private PawnPainter whitePawn;
	private PawnPainter blackPawn;
	private int x;
	private int y;
	private boolean win = false;

}
