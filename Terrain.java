package game;

import java.awt.Point;

public class Terrain extends CameraObservedObject {
	public Terrain(Point cameraLocation, double x, double y, double imageWidth, double imageHeight, String fileName,
			int horizontalImages, int verticalImages) throws Exception {
		super(cameraLocation, x, y, imageWidth * horizontalImages, imageHeight * verticalImages);
		for (int imageXIndex = 0; imageXIndex < horizontalImages; imageXIndex++) {
			for (int imageYIndex = 0; imageYIndex < verticalImages; imageYIndex++) {
				addGraphic(new ImageGraphic(fileName, imageWidth * imageXIndex, imageHeight * imageYIndex, imageWidth,
						imageHeight));
			}
		}
	}

	@Override
	public void act() {
	}
}