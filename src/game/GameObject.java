package game;

import java.awt.Point;
import java.util.ArrayList;

/**
 * One independent part of the game, bound to a location.
 */
public class GameObject {
	/*
	 * TODO Check if this can be made into an abstract class with act() as an
	 * abstract method.
	 */
	/**
	 * The location of this object in absolute space.
	 */
	protected final Point absoluteLocation;
	private boolean expired;
	/** This exists so that one GameObject can have multiple graphical parts. */
	private final ArrayList<Graphic> graphics = new ArrayList<Graphic>();
	/** This marks a GameComponent for its graphics list to be checked again. */
	private boolean repaint;

	/**
	 * This constructor sets the {@link #absoluteLocation} to match {@code x} and
	 * {@code y}, after scaling them by {@link Main#SIZE_FACTOR}.
	 * 
	 * @param x
	 * @param y
	 */
	public GameObject(double x, double y) {
		absoluteLocation = new Point((int) (x * Main.SIZE_FACTOR), (int) (y * Main.SIZE_FACTOR));
	}

	/**
	 * A method to handle all per-frame operations.
	 */
	public void act() {
	}

	/**
	 * Add a graphic to this {@link GameObject}
	 * 
	 * @param graphic The graphic to be added.
	 */
	protected final void addGraphic(Graphic graphic) {
		graphics.add(graphic);
		repaint();
	}

	/**
	 * Sets this {@link GameObject} and all its {@link Graphic}s to expire.
	 */
	public final void expire() {
		expired = true;
		for (Graphic g : graphics) {
			g.expire();
		}
	}

	/**
	 * @return The array of Graphic objects related to this Game Component.
	 */
	public final ArrayList<Graphic> getGraphics() {
		return graphics;
	}

	/**
	 * @return whether or not this {@link GameObject} is expired.
	 */
	public final boolean isExpired() {
		return expired;
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
}