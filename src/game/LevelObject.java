package game;

import java.awt.Point;

/**
 * A {@link GameComponent} that depends on the game camera.
 */
public abstract class LevelObject extends GameObject {
	protected Hitbox occupiedSpace;
	private final Point cameraLocation;

	public final boolean collidesWith(LevelObject lc) {
		return occupiedSpace.collidesWith(lc.occupiedSpace);
	}

	private final Point getScreenLocation() {
		return new Point((int) (absoluteLocation.getX() - cameraLocation.getX()),
				(int) (absoluteLocation.getY() - cameraLocation.getY()));
	}

	/**
	 * This constructor creates a reference to the related {@link Main.Scene} camera
	 * {@link Point}
	 * 
	 * @param cameraLocation   The scene camera's current location.
	 * @param absoluteLocation The object's current location, independent of the
	 *                         camera.
	 */
	public LevelObject(Point cameraLocation, double x, double y, double width, double height) {
		super(x, y);
		this.cameraLocation = cameraLocation;
		occupiedSpace = new Hitbox(width, height, absoluteLocation);
	}

	/**
	 * @see game.GameComponent#updateBounds()
	 */
	@Override
	protected final void updateBounds() {
		for (Graphic graphic : getGraphics()) {
			graphic.setLocation(getScreenLocation());
		}
	}
}