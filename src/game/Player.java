package game;

import java.awt.Point;
import java.util.ArrayList;

/**
 * A class to represent the player, controlled by the user's input.
 */
public class Player extends Character {
	private static final double JUMP_VELOCITY = 1.5 / 6, MOVESPEED = 1.0 / 12, INFLUENCE = 1.0 / 120, WIDTH = 50.0 / 60,
			HEIGHT = 100.0 / 60;
	private static final int PLAYER_HEALTH = 150;
	private int invincibilityFrames = 0;
	private final ArrayList<Item> items = new ArrayList<Item>();
	private ImageGraphic sprite = new ImageGraphic("assets/still/player2.png", 0, 0, WIDTH, HEIGHT);

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
		setHP(PLAYER_HEALTH);
	}

	@Override
	public void act() {
		super.act();
		if (invincibilityFrames > 0)
			invincibilityFrames--;
		checkBounds();
	}

	public void addItem(Item itm) {
		items.add(itm);
	}

	public void checkBounds() {

		if (absoluteLocation.getY() > 200) {
		takeDmg(PLAYER_HEALTH);
		}
	}

	@Override
	public void takeDmg(int x) {
		if (invincibilityFrames == 0) {
			super.takeDmg(x);
			invincibilityFrames = 60;
		}

		if (getHP() <= 0) {
			try {
				Main.constructLevel();
				Main.transitionScene(Main.ScenesEnum.LEVEL);
			} catch (Exception e) {

			}
		}
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