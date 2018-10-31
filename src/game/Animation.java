package game;

import java.awt.Graphics;

/**
 * A graphic which implements a frame by frame animation drawing.
 */
public class Animation extends Graphic {
	private static final long serialVersionUID = 3016242110618863938L;
	// TODO Allocate Variables for assets.

	/**
	 * A constructor for creating an animation.
	 * 
	 * @param width  The width of the animation
	 * @param height The height of the animation
	 */
	public Animation(double width, double height) {
		super(width, height);
		// TODO Load and transform assets.
	}

	/**
	 * A constructor for creating an animation.
	 * 
	 * @param xoffset The distance from the left of the GameObject in the X+
	 *                direction.
	 * @param yoffset The distance from the left of the GameObject in the X+
	 *                direction.
	 * @param width   The width of the animation
	 * @param height  The height of the animation
	 */
	public Animation(double xoffset, double yoffset, double width, double height) {
		super(xoffset, yoffset, width, height);
		// TODO Load and transform assets.
	}

	@Override
	public void act() {
		// TODO Next frame;
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Paint current frame;
	}
}