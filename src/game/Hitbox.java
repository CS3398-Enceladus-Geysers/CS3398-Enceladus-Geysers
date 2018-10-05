package game;

import java.awt.Point;
import java.awt.Rectangle;

public class Hitbox {
	double width, height;
	Point absoluteLocation;

	public final Point getCorner(boolean top, boolean left) {
		Rectangle r = getBounds();
		double x = left ? r.getMinX() : r.getMaxX();
		double y = top ? r.getMinY() : r.getMaxY();
		return new Point((int) x, (int) y);
	}

	public final Rectangle getBounds() {
		Rectangle r = new Rectangle();
		r.setLocation(absoluteLocation);
		r.setSize((int) width, (int) height);
		return r;
	}

	public Hitbox(double width, double height, Point absoluteLocation) {
		this.width = width * Main.SIZE_FACTOR;
		this.height = height * Main.SIZE_FACTOR;
		this.absoluteLocation = absoluteLocation;
	}

	public final boolean collidesWith(Hitbox h) {
		return getBounds().intersects(h.getBounds());
	}
}