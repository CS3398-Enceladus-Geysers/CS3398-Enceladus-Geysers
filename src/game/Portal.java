package game;

import java.awt.Point;

public class Portal extends Terrain {
	private int levelNumberToGoTo;

	public Portal(Point cameraLocation, double x, double y, double width, double height, String pathName,
			int levelNumberToGoTo) throws Exception {
		super(cameraLocation, x, y, width, height, pathName, 1, 1, true);
		this.levelNumberToGoTo = levelNumberToGoTo;
	}

	public final void teleport() {
		try {
			Main.constructLevel(levelNumberToGoTo);
		} catch (Exception e) {
		}
		Main.transitionScene(Main.ScenesEnum.LEVEL);
	}
}