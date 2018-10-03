package game;

import java.awt.Point;

public abstract class Character extends Entity {
	private int hp;
	private int armor;
	private int idkaddstuffhere;

	public Character(Point cameraLocation, double x, double y, double width, double height, boolean gravitational) {
		super(cameraLocation, x, y, width, height, gravitational);
	}
}
