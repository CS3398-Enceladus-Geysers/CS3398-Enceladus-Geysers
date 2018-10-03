package game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JPanel;

/**
 * This class represents a scene which we can swap to using
 * transitionScene(Scenes)
 */
public class Scene extends JPanel {
	private static final long serialVersionUID = -6926746677172868739L;
	protected final Point cameraLocation;
	private final LinkedList<GameObject> gameComponents = new LinkedList<GameObject>();

	public final Point getCameraLocation() {
		return cameraLocation;
	}

	public Scene() {
		cameraLocation = new Point(0, 0);
		setLayout(null);
	}

	/** Will be called on every frame. */
	public void act() {
		for (GameObject gc : gameComponents) {
			gc.act();
			if (gc.needsPainting()) {
				gc.markPainted();
				for (Graphic g : gc.getGraphics()) {
					boolean flag = true;
					Component[] c = getComponents();
					for (int x = 0; x < c.length; x++) {
						if (c[x] == g) {
							flag = false;
						}
					}
					if (flag)
						add(g);
				}
			}
		}
		Component[] c = getComponents();
		for (int x = 0; x < c.length; x++) {
			if (((Graphic) c[x]).isExpired())
				remove(c[x]);
		}
	}

	public void addGameComponent(GameObject gc) {
		gameComponents.add(gc);
		gc.repaint();
	}

	@Override
	public final Dimension getPreferredSize() {
		return Main.GAME_PANEL_DIMENSION;
	}
}
