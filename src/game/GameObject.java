package game;

import java.awt.Point;
import java.util.ArrayList;

/**
 * One independent part of the game, bound to a location.
 */
public abstract class GameObject {
	protected final Point absoluteLocation;
	/** This exists so that one GameObject can have multiple graphical parts. */
	private final ArrayList<Graphic> graphics = new ArrayList<Graphic>();
	/** This marks a GameComponent for its graphics list to be checked again. */
	private boolean repaint;
	private boolean expired;

	public GameObject(double x, double y) {
		absoluteLocation = new Point((int) (x * Main.SIZE_FACTOR), (int) (y * Main.SIZE_FACTOR));
	}

	public final boolean isExpired() {
		return expired;
	}

	public final void expire() {
		expired = true;
		for (Graphic g : graphics) {
			g.expire();
		}
	}

	public void act() {
		updateBounds();
	}

	/**
	 * Add a graphic to
	 * 
	 * @param graphic The graphic to be added.
	 */
	protected final void addGraphic(Graphic graphic) {
		graphics.add(graphic);
		repaint();
	}

	/**
	 * @return The array of Graphic objects related to this Game Component.
	 */
	public final ArrayList<Graphic> getGraphics() {
		return graphics;
	}

	/**
	 * Take this component off the schedule for painting.
	 */
	public final void markPainted() {
		repaint = false;
	}

	/**
	 * Boolean which tells the scene this component has new graphical components.
	 * 
	 * @return whether or not this component needs painting.
	 */
	public final boolean needsPainting() {
		return repaint;
	}

	/**
	 * Schedule this component for adding graphics to the scene.
	 */
	public final void repaint() {
		repaint = true;
	}

	/**
	 * Update the bounds of all attached graphics.
	 */
	protected abstract void updateBounds();
}