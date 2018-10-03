package game;

import java.awt.Point;

public abstract class Entity extends LevelObject {
	private static final double GRAVITY_CONSTANT = 1.0 / 60, MAX_FALLING_SPEED = 1.0 / 6;
	private double dx, dy;
	private boolean gravitational;
	private boolean grounded;

	public final boolean isGravitational() {
		return gravitational;
	}

	protected final boolean isGrounded() {
		return grounded;
	}

	public Entity(Point cameraLocation, double x, double y, double width, double height, boolean gravitational) {
		super(cameraLocation, x, y, width, height);
		this.gravitational = gravitational;
	}

	@Override
	public void act() {
		super.act();
		if (gravitational)
			applyGravityAcceleration();
		updateVelocity();
		move();
	}

	private void applyGravityAcceleration() {
		dy = Math.min(dy + (GRAVITY_CONSTANT * Main.SIZE_FACTOR), MAX_FALLING_SPEED * Main.SIZE_FACTOR);
	}

	public final void ground(LevelObject lc) {// TODO Change to terrain instead of LevelComponent.
		// TODO Move to not collide with lc
		grounded = true;
	}

	public abstract void updateVelocity();

	private final void move() {
		absoluteLocation.translate((int) dx, (int) dy);
	}

	protected double getDx() {
		return dx;
	}

	protected double getDy() {
		return dy;
	}

	protected void setDx(double dx) {
		this.dx = dx * Main.SIZE_FACTOR;
	}

	protected void setDy(double dy) {
		this.dy = dy * Main.SIZE_FACTOR;
	}
}
