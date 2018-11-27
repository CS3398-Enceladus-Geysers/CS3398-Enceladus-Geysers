package game;

import java.awt.Point;

/**
 * A class of {@link Terrain} that represents obstacles which will damage a
 * {@link Character} when touched.
 */
public class Obstacle extends Terrain {
	/**
	 * The amount of damage a {@link Character} is to receive upon touching the
	 * {@link Obstacle}.
	 */
	public int dmg;

	/**
	 * See
	 * {@link Terrain#Terrain(Point, double, double, double, double, String, int, int)}
	 * This constructor just adds a damage variable.
	 * 
	 * @param cameraLocation
	 * @param x
	 * @param y
	 * @param imageWidth
	 * @param imageHeight
	 * @param fileName
	 * @param horizontalImages
	 * @param verticalImages
	 * @param damage
	 * @throws Exception
	 */
	public Obstacle(Point cameraLocation, double x, double y, double imageWidth, double imageHeight, String fileName,
			int horizontalImages, int verticalImages, int damage) throws Exception {
		super(cameraLocation, x, y, imageWidth, imageHeight, fileName, horizontalImages, verticalImages);
		dmg = damage;
	}
}
