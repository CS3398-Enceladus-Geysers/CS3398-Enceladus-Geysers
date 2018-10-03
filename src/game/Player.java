package game;

import java.awt.Point;

public class Player extends Character {
	private static final double JUMP_VELOCITY = -1.0 / 6, MOVESPEED = 1.0 / 12, WIDTH = 50.0 / 60, HEIGHT = 100.0 / 60;

	public Player(Point cameraLocation) throws Exception {
		super(cameraLocation, 0, 0, WIDTH, HEIGHT, true);
		addGraphic(new ImageGraphic("assets/player.png", WIDTH, HEIGHT));
	}

	@Override
	public void updateVelocity() {
		if (Main.CURRENTLY_PRESSED_KEYS.contains(37)) {
			setDx(-MOVESPEED * Main.SIZE_FACTOR);
		}
		if (Main.CURRENTLY_PRESSED_KEYS.contains(38)) {
			if (isGrounded()) {
				jump();
			}
		}
		if (Main.CURRENTLY_PRESSED_KEYS.contains(39)) {
			setDx(MOVESPEED * Main.SIZE_FACTOR);
		}
		if (Main.CURRENTLY_PRESSED_KEYS.contains(40)) {
		}
	}

	private void jump() {
		setDy(-JUMP_VELOCITY * Main.SIZE_FACTOR);
	}
}