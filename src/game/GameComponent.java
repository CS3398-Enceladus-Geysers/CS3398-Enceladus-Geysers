package game;

import java.util.ArrayList;

/**
 * One independent part of the game, bound to a location.
 */
public abstract class GameComponent {
	/** This exists so that one GameComponent can have multiple graphical parts. */
	private final ArrayList<Graphic> graphics = new ArrayList<Graphic>();
	/** This marks a GameComponent for its graphics list to be checked again. */
	private boolean repaint;

	/**
	 * Add a graphic to
	 * 
	 * @param graphic The graphic to be added.
	 */
	public final void addGraphic(Graphic graphic) {
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