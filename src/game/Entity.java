package game;

import java.awt.Point;

public abstract class Entity extends CameraObservedObject {
	private static final double GRAVITY_CONSTANT = 1.0 / 60, MAX_FALLING_SPEED = 1.0 / 6;
	private double dx, dy;
	private final boolean gravitational;
	private boolean grounded;

	public final double getVelocitySlope() {
		return dy / dx;
	}

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
		if (gravitational)
			applyGravityAcceleration();
		updateVelocity();
		move();
		super.act();
	}

	private void applyGravityAcceleration() {
		dy = Math.min(dy + (GRAVITY_CONSTANT * Main.SIZE_FACTOR), MAX_FALLING_SPEED * Main.SIZE_FACTOR);
	}

	public final boolean exclusionPrinciple(Terrain trr) {
		// TODO Move entity to not collide with terrain.
		return false;// TODO Change to return true if object is on top
	}

	public final void setGrounded(boolean grounded) {
		this.grounded = grounded;
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
