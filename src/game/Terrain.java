package game;

import java.awt.Point;

public class Terrain extends CameraObservedObject {
	public final void exclusionPrinciple(Entity ent) {
		// TODO Bounce the entity out here using corners
	}

	public Terrain(Point cameraLocation, double x, double y, double width, double height) {
		super(cameraLocation, x, y, width, height);
	}
}