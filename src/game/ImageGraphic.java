package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class ImageGraphic extends Graphic {
	private static final long serialVersionUID = 7604033494188278910L;
	private static Integer sizeFactor;
	Image image;

	public ImageGraphic(Integer sizeFactor, String fileName) throws Exception {
		ImageGraphic.sizeFactor = sizeFactor;
		Image i = ImageIO.read(new BufferedInputStream(new FileInputStream(fileName)));
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}