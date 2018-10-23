package game;

import java.awt.Point;

/**
 * This class represents all characters in the game which fight.
 */
public abstract class Character extends Entity {
	@SuppressWarnings("unused")
	private static int armor, maxHP;
	// TODO Add more variables and methods that have to do with fighting.
	private int HP;

	/**
	 * This constructor is the same as
	 * {@link Entity#Entity(Point, double, double, double, double, boolean)}, but
	 * will eventually initialize fighting related variables.
	 * 
	 * @param cameraLocation
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param gravitational
	 */
	public Character(Point cameraLocation, double x, double y, double width, double height, boolean gravitational) {
		super(cameraLocation, x, y, width, height, gravitational);
	}

	/**
	 * @return The HP of this character.
	 */
	public final int getHP() {
		return HP;
	}
}
