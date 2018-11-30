package game;

import java.awt.Point;

public class Enemy extends Character {

	private static final double WIDTH = 50.0 / 60, HEIGHT = 100.0 / 60;
	private static final double MIN_X = 5.0 / 60.0;
	private ImageGraphic enemy = new ImageGraphic("assets/still/player.png", 0, 0, WIDTH, HEIGHT);

	public Enemy(Point cameraLocation) throws Exception {
		super(cameraLocation, 10, 0, WIDTH, HEIGHT, true);
		addGraphic(enemy);
		setHP(50);
	}

	@Override
	public void updateVelocity() {
		if (isGrounded()) {
			setDx(-2.0 / 60.0);
			if (absoluteLocation.getX() <= MIN_X) {
				setDx(0.0 / 60.0);
			} 
		}
	}
}
