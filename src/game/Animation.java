package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * A graphic which implements a frame by frame animation drawing.
 */
public class Animation extends Graphic {
	private static final long serialVersionUID = 3016242110618863938L;
	private static final HashMap<String, Image[]> RESOURCES = new HashMap<String, Image[]>();
	private int numFrames = 0;
	private Image[] frames;
	private int facing = 1;
	AffineTransformOp flipHorizontal = new AffineTransformOp(new AffineTransform(-1, 0, 0, 1, getWidth(), 0),
			AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	private int currentFrame = 0;

	private final void loadAndFlip(String pathName) {
		if (RESOURCES.containsKey(pathName)) {
			frames = RESOURCES.get(pathName);
			numFrames=frames.length/2;
		} else {
			ArrayList<BufferedInputStream> frameStreams = new ArrayList<BufferedInputStream>();
			try {
				do {
					frameStreams.add(new BufferedInputStream(new FileInputStream(pathName + numFrames + ".png")));
					numFrames++;
				} while (true);
			} catch (Throwable t) {
			}
			frames = new Image[numFrames * 2];
			for (int x = 0; x < numFrames; x++) {
				try {
					frames[numFrames + x] = ImageIO.read(frameStreams.get(x)).getScaledInstance(getWidth(), getHeight(),
							Image.SCALE_AREA_AVERAGING);
				} catch (Throwable t) {
				}
				BufferedImage b = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
				b.getGraphics().drawImage(frames[numFrames + x], 0, 0, null);
				frames[x] = flipHorizontal.filter(b, null);
				RESOURCES.put(pathName, frames);
			}
		}
	}

	/**
	 * A constructor for creating an animation.
	 * 
	 * @param width  The width of the animation
	 * @param height The height of the animation
	 */
	public Animation(String pathName, double width, double height) {
		super(width, height);
		loadAndFlip(pathName);
	}

	/**
	 * A constructor for creating an animation.
	 * 
	 * @param pathName
	 * 
	 * @param xoffset  The distance from the left of the GameObject in the X+
	 *                 direction.
	 * @param yoffset  The distance from the left of the GameObject in the X+
	 *                 direction.
	 * @param width    The width of the animation
	 * @param height   The height of the animation
	 */
	public Animation(String pathName, double xoffset, double yoffset, double width, double height) {
		super(xoffset, yoffset, width, height);
		loadAndFlip(pathName);
	}

	@Override
	public void act() {
		currentFrame = (currentFrame + 1) % numFrames;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(frames[facing * numFrames + currentFrame], 0, 0, null);
	}
}