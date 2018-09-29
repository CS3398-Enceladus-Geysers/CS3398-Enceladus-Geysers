package game;

import java.awt.Point;

/**
 * A {@link GameComponent} that depends on the game camera.
 */
public class LevelComponent extends GameComponent {
	private final Point absoluteLocation;
	private final Point cameraLocation;

	/**
	 * This constructor creates a reference to the related {@link Main.Scene} camera
	 * {@link Point}
	 * 
	 * @param cameraLocation   The scene camera's current location.
	 * @param absoluteLocation The object's current location, independent of the
	 *                         camera.
	 */
	public LevelComponent(Point cameraLocation, Point absoluteLocation) {
		this.cameraLocation = cameraLocation;
		this.absoluteLocation = absoluteLocation;
	}

	/**
	 * @see game.GameComponent#updateBounds()
	 */
	@Override
	protected final void updateBounds() {
		for (Graphic graphic : getGraphics()) {
			graphic.setLocation((int) (absoluteLocation.getX() - cameraLocation.getX()),
					(int) (absoluteLocation.getY() - cameraLocation.getY()));
		}
	}
}