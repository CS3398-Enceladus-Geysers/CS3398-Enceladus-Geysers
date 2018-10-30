package game;

import java.awt.Point;
import java.awt.Rectangle;

import com.sun.accessibility.internal.resources.accessibility;

/**
 * A class to handle collisions.
 */
public class Hitbox {
	Point absoluteLocation;
	double width, height;

	/**
	 * Initializes this {@link Hitbox} with a width and height scaled by
	 * {@link Main#SIZE_FACTOR}, and a {@link Point} that refers to a
	 * {@link GameObject#absoluteLocation} to represent a rectangle that depends on
	 * the location of that {@link GameObject}.
	 * 
	 * @param width            The width of this {@link Hitbox}, before scaling by
	 *                         {@link Main#SIZE_FACTOR}
	 * @param height           The height of this {@link Hitbox}, before scaling by
	 *                         {@link Main#SIZE_FACTOR}
	 * @param absoluteLocation The reference to a
	 *                         {@link GameObject#absoluteLocation}
	 */
	public Hitbox(double width, double height, Point absoluteLocation) {
		this.width = width * Main.SIZE_FACTOR;
		this.height = height * Main.SIZE_FACTOR;
		this.absoluteLocation = absoluteLocation;
	}

	public final boolean collidesWith(Hitbox h) {
		return getBounds().intersects(h.getBounds());
	}

	public final Rectangle getBounds() {
		Rectangle r = new Rectangle();
		r.setLocation(absoluteLocation);
		r.setSize((int) width, (int) height);
		return r;
	}

	public final Point getCorner(boolean top, boolean left) {
		Rectangle r = getBounds();
		double x = left ? r.getMinX() : r.getMaxX();
		double y = top ? r.getMinY() : r.getMaxY();
		return new Point((int) x, (int) y);
	}
}