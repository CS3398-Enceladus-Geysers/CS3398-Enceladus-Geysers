package game;

import java.awt.Point;

/**
 * A CameraObservedObject that can move and may be affected by gravity and
 * terrain collision.
 */
public abstract class Entity extends CameraObservedObject {
	enum Direction {
		DOWN, LEFT, RIGHT, UP;
	}

	private static final double GRAVITY_CONSTANT = 1.0 / 60, MAX_FALLING_SPEED = 1.0 / 6;
	private double dx, dy;
	private final boolean gravitational;
	private boolean grounded;

	/**
	 * @param cameraLocation
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param gravitational  Whether or not the entity is subject to gravity and
	 *                       terrain collision.
	 */
	public Entity(Point cameraLocation, double x, double y, double width, double height, boolean gravitational) {
		super(cameraLocation, x, y, width, height);
		this.gravitational = gravitational;
	}

	/**
	 * Accelerates this entity in a target axis, up to a specified maximum velocity.
	 * 
	 * @param isVertical           Specifies the axis of acceleration
	 * @param accelerationConstant Specifies the constant rate of acceleration, may
	 *                             be negative
	 * @param maximumVelocity      Specifies the maximum velocity, may be negative
	 */
	protected final void accelerate(boolean isVertical, double accelerationConstant, double maximumVelocity) {
		if (maximumVelocity == 0) {
			if (isVertical) {
				double deltaSign = Math.signum(dy);
				if (deltaSign == 0)
					return;
				else if (deltaSign == 1.0) {
					dy = Math.max(dy - (accelerationConstant * Main.sizeFactor), 0);
				} else if (deltaSign == -1.0) {
					dy = Math.min(dy + (accelerationConstant * Main.sizeFactor), 0);
				}
			} else {
				double deltaSign = Math.signum(dx);
				if (deltaSign == 0)
					return;
				else if (deltaSign == 1.0) {
					dx = Math.max(dx - (accelerationConstant * Main.sizeFactor), 0);
				} else if (deltaSign == -1.0) {
					dx = Math.min(dx + (accelerationConstant * Main.sizeFactor), 0);
				}
			}
		} else if (accelerationConstant > 0) {
			if (isVertical)
				dy = Math.min(dy + (accelerationConstant * Main.sizeFactor), maximumVelocity * Main.sizeFactor);
			else
				dx = Math.min(dx + (accelerationConstant * Main.sizeFactor), maximumVelocity * Main.sizeFactor);
		} else {
			if (isVertical)
				dy = Math.max(dy + (accelerationConstant * Main.sizeFactor), maximumVelocity * Main.sizeFactor);
			else
				dx = Math.max(dx + (accelerationConstant * Main.sizeFactor), maximumVelocity * Main.sizeFactor);
		}
	}

	@Override
	public void act() {
		if (gravitational)
			applyGravityAcceleration();
		updateVelocity();
		move();
	}

	private void applyGravityAcceleration() {
		accelerate(true, GRAVITY_CONSTANT, MAX_FALLING_SPEED);
	}

	/**
	 * Moves this entity to not collide with {@code trr}
	 * 
	 * @param trr The {@link Terrain} object that this {@link Entity} collides with.
	 * @return True if this entity is on top of the terrain and is therefore now
	 *         grounded.
	 */
	public boolean exclusionPrinciple(Terrain trr) {
		Point[] entityCorners = { occupiedSpace.getCorner(true, true), occupiedSpace.getCorner(true, false),
				occupiedSpace.getCorner(false, true), occupiedSpace.getCorner(false, false) };// TODO Make this a single
																								// call getCorners();
		Point[] trrCorners = { trr.occupiedSpace.getCorner(true, true), trr.occupiedSpace.getCorner(true, false),
				trr.occupiedSpace.getCorner(false, true), trr.occupiedSpace.getCorner(false, false) };
		boolean[] containedCorners = { trr.occupiedSpace.getBounds().contains(entityCorners[0]),
				trr.occupiedSpace.getBounds().contains(entityCorners[1]),
				trr.occupiedSpace.getBounds().contains(entityCorners[2]),
				trr.occupiedSpace.getBounds().contains(entityCorners[3]) };
		Direction toMove = null;
		if (getDx() == 0) {
			if (getDy() > 0)
				toMove = Direction.UP;
			else
				toMove = Direction.DOWN;
		} else if (getDy() == 0) {
			if (getDx() > 0)
				toMove = Direction.LEFT;
			else
				toMove = Direction.RIGHT;
		} else if (containedCorners[0]) {
			if (containedCorners[1])
				toMove = Direction.DOWN;
			else if (containedCorners[3])
				toMove = Direction.RIGHT;
			else {
				if (!movingRight() && (trrCorners[3].getX() - entityCorners[0].getX()) * getVelocitySlope()
						+ entityCorners[0].getY() < trrCorners[3].getY())
					toMove = Direction.RIGHT;
				else
					toMove = Direction.DOWN;
			}
		} else if (containedCorners[1]) {
			if (containedCorners[3])
				toMove = Direction.LEFT;
			else {
				if (movingRight() && (trrCorners[2].getX() - entityCorners[1].getX()) * getVelocitySlope()
						+ entityCorners[1].getY() < trrCorners[2].getY())
					toMove = Direction.LEFT;
				else
					toMove = Direction.DOWN;
			}
		} else if (containedCorners[2]) {
			if (containedCorners[3])
				toMove = Direction.UP;
			else {
				if (!movingRight() && (trrCorners[1].getX() - entityCorners[2].getX()) * getVelocitySlope()
						+ entityCorners[2].getY() > trrCorners[1].getY())
					toMove = Direction.RIGHT;
				else
					toMove = Direction.UP;
			}
		} else if (containedCorners[3]) {
			if (movingRight() && (trrCorners[0].getX() - entityCorners[3].getX()) * getVelocitySlope()
					+ entityCorners[3].getY() > trrCorners[0].getY())
				toMove = Direction.LEFT;
			else
				toMove = Direction.UP;
		}
		if (toMove == Direction.UP) {
			setDy(0);
			while (collidesWith(trr))
				absoluteLocation.translate(0, -1);
		} else if (toMove == Direction.DOWN) {
			setDy(0);
			while (collidesWith(trr))
				absoluteLocation.translate(0, 1);
		} else if (toMove == Direction.LEFT) {
			setDx(0);
			while (collidesWith(trr))
				absoluteLocation.translate(-1, 0);
		} else if (toMove == Direction.RIGHT) {
			setDx(0);
			while (collidesWith(trr))
				absoluteLocation.translate(1, 0);
		}
		return toMove == Direction.UP;
	}

	/**
	 * @return The velocity of this object in the X-axis.
	 */
	protected double getDx() {
		return dx / Main.sizeFactor;
	}

	/**
	 * @return The velocity of this object in the Y-axis.
	 */
	protected double getDy() {
		return dy / Main.sizeFactor;
	}

	/**
	 * @return {@code dy/dx} the slope of this object's velocity
	 */
	public final double getVelocitySlope() {
		return dy / dx;
	}

	/**
	 * @return true if this object is subject to gravity and {@link Terrain}
	 *         collision.
	 */
	public final boolean isGravitational() {
		return gravitational;
	}

	/**
	 * @return true if this object is currently on top of {@link Terrain}.
	 */
	protected final boolean isGrounded() {
		return grounded;
	}

	private final void move() {
		absoluteLocation.translate((int) dx, (int) dy);
	}

	private final boolean movingRight() {
		return dx > 0;
	}

	/**
	 * Sets the velocity of this {@link Entity} in the X-axis, after scaling it by
	 * {@link Main#sizeFactor}.
	 * 
	 * @param dx the velocity to be set, before scaling by {@link Main#sizeFactor}.
	 */
	protected void setDx(double dx) {
		this.dx = dx * Main.sizeFactor;
	}

	/**
	 * Sets the velocity of this {@link Entity} in the Y-axis, after scaling it by
	 * {@link Main#sizeFactor}.
	 * 
	 * @param dy the velocity to be set, before scaling by {@link Main#sizeFactor}.
	 */
	protected void setDy(double dy) {
		this.dy = dy * Main.sizeFactor;
	}

	/**
	 * Sets whether or not this {@link Entity} is currently on top of
	 * {@link Terrain}
	 * 
	 * @param grounded Whether or not this {@link Entity} is currently on top of
	 *                 {@link Terrain}
	 */
	public final void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}

	/**
	 * An abstract method to be overridden in order to allow for changes in
	 * velocity.
	 */
	public abstract void updateVelocity();
}
