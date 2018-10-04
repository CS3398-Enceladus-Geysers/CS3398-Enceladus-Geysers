package game;

import java.awt.Point;

public class Terrain extends CameraObservedObject {
	public Terrain(Point cameraLocation, double x, double y, double width, double height) {// TODO Add more params to
																							// copy graphics/expand
																							// terrain.
		super(cameraLocation, x, y, width, height);
	}
}