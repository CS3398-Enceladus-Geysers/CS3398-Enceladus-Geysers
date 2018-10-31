package game;

import java.awt.Point;

/**
 * A {@link CameraObservedObject} which represents terrain that gravitational
 * entities will collide with and stand on.
 */
public class Terrain extends CameraObservedObject {
	/**
	 * See
	 * {@link CameraObservedObject#CameraObservedObject(Point, double, double, double, double)}.
	 * This constructor adds functionality to create multiple {@link ImageGraphic}s
	 * in order to make a single object that is of a larger size and tiles images.
	 * 
	 * @param cameraLocation
	 * @param x
	 * @param y
	 * @param imageWidth
	 * @param imageHeight
	 * @param fileName         The name of the image file to be loaded from assets
	 *                         and copied.
	 * @param horizontalImages The width of the {@link Terrain} object in image
	 *                         units e.g. how many images wide this object is.
	 * @param verticalImages   The height of the {@link Terrain} object in image
	 *                         units e.g. how many images high this object is.
	 * @throws Exception in case of IO failure.
	 */
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