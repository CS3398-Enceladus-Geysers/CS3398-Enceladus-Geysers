package game;

import java.awt.Point;

/**
 * A {@link GameObject} that depends on the game camera.
 */
public abstract class CameraObservedObject extends GameObject {
	private final Point cameraLocation;
	/**
	 * The hitbox representing the space occupied by this
	 * {@link CameraObservedObject}
	 */
	protected Hitbox occupiedSpace;

	/**
	 * This constructor creates a reference to the related
	 * {@link Scene#cameraLocation}.
	 * 
	 * @param cameraLocation The scene camera's current location
	 * @param x              The x coordinate in absolute space of this Object
	 * @param y              The y coordinate in absolute space of this Object
	 * @param width          The width of this object, before scaling by
	 *                       {@link Main#sizeFactor}
	 * @param height         The width of this object, before scaling by
	 *                       {@link Main#sizeFactor}
	 */
	public CameraObservedObject(Point cameraLocation, double x, double y, double width, double height) {
		super(x, y);
		this.cameraLocation = cameraLocation;
		occupiedSpace = new Hitbox(width, height, absoluteLocation);
	}

	@Override
	public abstract void act();

	/**
	 * @param coo
	 * @return {@code true} if this object collides with {@code coo}
	 */
	public final boolean collidesWith(CameraObservedObject coo) {
		return occupiedSpace.collidesWith(coo.occupiedSpace);
	}

	private final Point getScreenLocation() {
		return new Point((int) (absoluteLocation.getX() - cameraLocation.getX()),
				(int) (absoluteLocation.getY() - cameraLocation.getY()));
	}

	/**
	 * Updates the graphic locations on the screen to match the current location of
	 * this object on the screen.
	 */
	public final void updateLocation() {
		for (Graphic graphic : getGraphics()) {
			graphic.setLocation(getScreenLocation());
		}
	}
}