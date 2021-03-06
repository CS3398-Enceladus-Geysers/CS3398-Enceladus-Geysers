package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * A graphic which is represented by a still image, as opposed to an animation.
 */
public class ImageGraphic extends Graphic {
	public static HashMap<String, Image[]> RESOURCES = new HashMap<String, Image[]>();
	private static final long serialVersionUID = 7604033494188278910L;
	private int facing = 1;
	AffineTransformOp flipHorizontal = new AffineTransformOp(new AffineTransform(-1, 0, 0, 1, getWidth(), 0),
			AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	private Image[] image = new Image[2];

	public ImageGraphic(String fileName, double width, double height) throws Exception {
		super(width, height);
		loadAndFlip(fileName);
	}

	/**
	 * This constructor loads and scales an image, and also sets its size.
	 * 
	 * @param fileName File name for image to be loaded.
	 * @param xoffset  The distance for this image to be rendered measured in the
	 *                 X-axis from the top-left corner.
	 * @param yoffset  The distance for this image to be rendered measured in the
	 *                 Y-axis from the top-left corner.
	 * @param width    Width of this graphic, before scaling by
	 *                 {@link Main#sizeFactor}
	 * @param height   Height of this graphic, before scaling by
	 *                 {@link Main#sizeFactor}
	 * @throws Exception In case a file is missing.
	 */
	public ImageGraphic(String fileName, double xoffset, double yoffset, double width, double height) throws Exception {
		super(xoffset, yoffset, width, height);
		loadAndFlip(fileName);
	}

	public ImageGraphic(String fileName, double xoffset, double yoffset, double width, double height,
			boolean foreground) throws Exception {
		super(xoffset, yoffset, width, height, foreground);
		loadAndFlip(fileName);
	}

	@Override
	public void act() {
	}

	public final void faceLeft() {
		if (facing == 1) {
			flip();
		}
	}

	public final void faceRight() {
		if (facing == 0) {
			flip();
		}
	}

	public final void flip() {
		facing = 1 - facing;
		repaint();
	}

	private final void loadAndFlip(String fileName) throws Exception {
		if (RESOURCES.containsKey(fileName)) {
			image = RESOURCES.get(fileName);
		} else {
			image[1] = ImageIO.read(new BufferedInputStream(new FileInputStream(fileName)))
					.getScaledInstance(getWidth(), getHeight(), Image.SCALE_AREA_AVERAGING);
			BufferedImage b = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			b.getGraphics().drawImage(image[1], 0, 0, null);
			image[0] = flipHorizontal.filter(b, null);
			RESOURCES.put(fileName, image);
		}
	}

	/**
	 * Overrides paintComponent to paint the still image.
	 * 
	 * @see game.Graphic#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image[facing], 0, 0, null);
	}
}