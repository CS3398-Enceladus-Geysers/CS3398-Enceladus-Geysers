package game;

import java.awt.Point;

public class Enemy extends Character {

	private static final double WIDTH = 50.0 / 60, HEIGHT = 100.0 / 60;
	private ImageGraphic enemy = new ImageGraphic("assets/still/player.png", 0, 0, WIDTH, HEIGHT);

	public Enemy(Point cameraLocation) throws Exception {
		super(cameraLocation, 0, 0, WIDTH, HEIGHT, true);
		addGraphic(enemy);
		setHP(50);
	}

	@Override
	public void updateVelocity() {
		if (isGrounded()) {
			setDx(2.0 / 60.0);
		}
		if (getDx() > 0) {
			enemy.faceRight();
		} else if (getDx() < 0) {
			enemy.faceLeft();
		}

	}
}
