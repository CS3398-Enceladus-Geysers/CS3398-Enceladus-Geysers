package game;

import java.awt.Point;
import java.util.ArrayList;

/**
 * A class to represent the player, controlled by the user's input.
 */
public class Player extends Character {
	private static final double JUMP_VELOCITY = 1.5 / 6, MOVESPEED = 1.0 / 12, INFLUENCE = 1.0 / 120, WIDTH = 50.0 / 60,
			HEIGHT = 100.0 / 60;
	private ImageGraphic sprite = new ImageGraphic("assets/still/player2.png", 0, 0, WIDTH, HEIGHT);
	private int invincibilityFrames = 0;
	private final ArrayList<Item> items = new ArrayList<Item>();

	@Override
	public void act() {
		super.act();
		if (invincibilityFrames > 0)
			invincibilityFrames--;
	}

	@Override
	public void takeDmg(int x) {
		if (invincibilityFrames == 0) {
			super.takeDmg(x);
			invincibilityFrames = 90;
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

	public void addItem(Item itm) {
		items.add(itm);
	}
}