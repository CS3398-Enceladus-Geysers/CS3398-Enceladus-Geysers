package game;

import java.awt.Point;

public abstract class Character extends Entity {
	@SuppressWarnings("unused")
	private static int armor, maxHP;
	// TODO Add more variables and methods that have to do with fighting.

	private int HP;
	public Character(Point cameraLocation, double x, double y, double width, double height, boolean gravitational) {
		super(cameraLocation, x, y, width, height, gravitational);
	}

	public final int getHP() {
		return HP;
	}
}
