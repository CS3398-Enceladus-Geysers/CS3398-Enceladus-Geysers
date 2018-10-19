package game;

import java.awt.Point;

public class Obstacle extends Terrain {
	
	public Obstacle(Point cameraLocation, double x, double y, double imageWidth, double imageHeight, String fileName,
			int horizontalImages, int verticalImages) throws Exception {
		super(cameraLocation, x, y, imageWidth, imageHeight, fileName, horizontalImages, verticalImages);
	}
	

}
