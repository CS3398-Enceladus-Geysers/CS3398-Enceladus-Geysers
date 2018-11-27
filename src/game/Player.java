package game;

import java.awt.Point;

/**
 * A class to represent the player, controlled by the user's input.
 */
public class Player extends Character {
	private static final double JUMP_VELOCITY = 1.5 / 6, MOVESPEED = 1.0 / 12, INFLUENCE = 1.0 / 120, WIDTH = 50.0 / 60,
			HEIGHT = 100.0 / 60;
	private ImageGraphic sprite = new ImageGraphic("assets/still/player2.png", 0, 0, WIDTH, HEIGHT);
	private int invincibleFrames = 0;

	@Override
	public void act() {
		super.act();
		if (invincibleFrames > 0)
			invincibleFrames--;
	}

	@Override
	public void takeDmg(int x) {
		if (invincibleFrames == 0) {
			setHP(getHP() - x);
			invincibleFrames = 90;
		}
	}

	/**
	 * Creates a {@link Player} object using the reserved player assets and
	 * predefined player stats.
	 * 
	 * @param cameraLocation
	 * @throws Exception
	 */
	public Player(Point cameraLocation) throws Exception {
		super(cameraLocation, 0, 0, WIDTH, HEIGHT, true);
		addGraphic(sprite);
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
		if (getDx() > 0) {
			sprite.faceRight();
		} else if (getDx() < 0) {
			sprite.faceLeft();
		}
	}
}