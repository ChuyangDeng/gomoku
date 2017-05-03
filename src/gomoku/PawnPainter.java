package gomoku;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class PawnPainter {
	
	private static HashMap<String, BufferedImage> images = new HashMap<>();
	static BufferedImage img;
	
	public PawnPainter(String path) {
		if (!images.containsKey(path)) {
			try {
				if (path.contains("http")) {
					img = ImageIO.read(new URL(path));
				} else {
					img = ImageIO.read(new File(path));
				}
			} catch (IOException e) {
				System.out.println("Image does not exist or cannot be opened.");
				System.exit(1);
			}
		}
	}
	
	public void paint(Graphics graphic, int x, int y) {
		graphic.drawImage(img, x, y, null);
	}
	
}
