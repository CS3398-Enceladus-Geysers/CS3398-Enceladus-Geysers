package game;

import java.awt.Point;

/**
 * A {@link GameObject} that depends on the game camera.
 */
public abstract class CameraObservedObject extends GameObject {
	private final Point cameraLocation;
	protected Hitbox occupiedSpace;

	/**
	 * This constructor creates a reference to the related {@link Main.Scene} camera
	 * {@link Point}
	 * 
	 * @param cameraLocation   The scene camera's current location.
	 * @param absoluteLocation The object's current location, independent of the
	 *                         camera.
	 */
	public CameraObservedObject(Point cameraLocation, double x, double y, double width, double height) {
		super(x, y);
		this.cameraLocation = cameraLocation;
		occupiedSpace = new Hitbox(width, height, absoluteLocation);
	}

	@Override
	public void act() {
		updateLocation();
	}

	public final boolean collidesWith(CameraObservedObject lc) {
		return occupiedSpace.collidesWith(lc.occupiedSpace);
	}

	private final Point getScreenLocation() {
		return new Point((int) (absoluteLocation.getX() - cameraLocation.getX()),
				(int) (absoluteLocation.getY() - cameraLocation.getY()));
	}

	protected final void updateLocation() {
		for (Graphic graphic : getGraphics()) {
			graphic.setLocation(getScreenLocation());
		}
	}
}