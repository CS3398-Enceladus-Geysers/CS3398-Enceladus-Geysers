package game;

import java.awt.Point;

public class Enemy extends Character {

	private static final double WIDTH = 50.0 / 60, HEIGHT = 100.0 / 60;
	private static final double MIN_X = 0, MAX_X = 600;
	private ImageGraphic enemy = new ImageGraphic("assets/still/player.png", 0, 0, WIDTH, HEIGHT);

	public Enemy(Point cameraLocation) throws Exception {
		super(cameraLocation, 10, 0, WIDTH, HEIGHT, true);
		addGraphic(enemy);
		setHP(50);
		setDx(-2.0 / 60.0);
	}

	@Override
	public void updateVelocity() {
		if (isGrounded()) {
			if (absoluteLocation.getX() < MIN_X || absoluteLocation.getX() > MAX_X) {
				reverseDirection();
			}
		}
	}

	private final void reverseDirection() {
		setDx(-getDx());
	}
	
	public void doDmgCollision(Character ply) {
		if(ply instanceof Player) {
			if(this.occupiedSpace.collidesWith(ply.occupiedSpace)) {
				ply.takeDmg(35);
			}
		}
	}
	
	@Override
	public void act() {
		super.act();
		doDmgCollision(Main.getPlayer());
	}
}
