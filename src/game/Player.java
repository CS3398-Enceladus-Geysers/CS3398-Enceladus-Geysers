package game;

import java.awt.Point;

public class Player extends Character {
	private static final double JUMP_VELOCITY = 1.5 / 6, MOVESPEED = 1.0 / 12, INFLUENCE = 1.0 / 120, WIDTH = 50.0 / 60,
			HEIGHT = 100.0 / 60;

	public Player(Point cameraLocation) throws Exception {
		super(cameraLocation, 0, 0, WIDTH, HEIGHT, true);
		addGraphic(new ImageGraphic("assets/player.png", 0, 0, WIDTH, HEIGHT));
		setHP(150);
	}

	@Override
	public void updateVelocity() {
		if (isGrounded()) {
			setDx(0);
			if (Main.CURRENTLY_PRESSED_KEYS.contains(37)) {
				setDx(-MOVESPEED);
			}
			if (Main.CURRENTLY_PRESSED_KEYS.contains(38)) {
				setDy(-JUMP_VELOCITY);
			}
			if (Main.CURRENTLY_PRESSED_KEYS.contains(39)) {
				setDx(MOVESPEED);
			}
//			if (Main.CURRENTLY_PRESSED_KEYS.contains(40)) {//Down Key
//			}
		} else {
			if (Main.CURRENTLY_PRESSED_KEYS.contains(37)) {
				accelerate(false, -INFLUENCE, -MOVESPEED);
			} else if (Main.CURRENTLY_PRESSED_KEYS.contains(39)) {
				accelerate(false, INFLUENCE, MOVESPEED);
			} else {
				accelerate(false, INFLUENCE, 0);
			}
		}
	}
	
	public boolean exclusionPrinciple(Obstacle trr) {
		return super.exclusionPrinciple(trr);
	}
}