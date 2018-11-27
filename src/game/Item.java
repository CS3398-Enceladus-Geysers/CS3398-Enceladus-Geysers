package game;

import java.awt.Point;

public class Item extends Entity {
	private final String name;

	public Item(Point cameraLocation, double x, double y, double width, double height, String name, String fileName,
			boolean animated) {
		super(cameraLocation, x, y, width, height, true);
		this.name = name;
		if (animated)
			addGraphic(new Animation(fileName, width, height));
		else {
			try {
				addGraphic(new ImageGraphic(fileName, width, height));
			} catch (Exception e) {
			}
		}
	}

	public final String getName() {
		return name;
	}

	@Override
	public void updateVelocity() {
	}
}