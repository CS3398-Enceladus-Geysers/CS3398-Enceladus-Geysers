package game;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * A class to handle collisions.
 */
public class Hitbox {
	Point absoluteLocation;
	double width, height;

	/**
	 * Initializes this {@link Hitbox} with a width and height scaled by
	 * {@link Main#sizeFactor}, and a {@link Point} that refers to a
	 * {@link GameObject#absoluteLocation} to represent a rectangle that depends on
	 * the location of that {@link GameObject}.
	 * 
	 * @param width            The width of this {@link Hitbox}, before scaling by
	 *                         {@link Main#sizeFactor}
	 * @param height           The height of this {@link Hitbox}, before scaling by
	 *                         {@link Main#sizeFactor}
	 * @param absoluteLocation The reference to a
	 *                         {@link GameObject#absoluteLocation}
	 */
	public Hitbox(double width, double height, Point absoluteLocation) {
		this.width = width * Main.sizeFactor;
		this.height = height * Main.sizeFactor;
		this.absoluteLocation = absoluteLocation;
	}

	/**
	 * @param h The {@link Hitbox} to test for collision.
	 * @return True if this hitbox collides with {@code h}
	 */
	public final boolean collidesWith(Hitbox h) {
		return getBounds().intersects(h.getBounds());
	}

	/**
	 * @return A {@link Rectangle} representing the bounds of this {@link Hitbox}
	 */
	public final Rectangle getBounds() {
		Rectangle r = new Rectangle();
		r.setLocation(absoluteLocation);
		r.setSize((int) width, (int) height);
		return r;
	}

	/**
	 * @param top  Specifies if the corner to be returned is on the top edge, true
	 *             if it is, false if it is not.
	 * @param left Specifies if the corner to be returned is on the left edge, true
	 *             if it is, false if it is not.
	 * @return A {@link Point} which represents the requested corner of this
	 *         {@link Hitbox}.
	 */
	public final Point getCorner(boolean top, boolean left) {
		Rectangle r = getBounds();
		double x = left ? r.getMinX() : r.getMaxX();
		double y = top ? r.getMinY() : r.getMaxY();
		return new Point((int) x, (int) y);
	}
}