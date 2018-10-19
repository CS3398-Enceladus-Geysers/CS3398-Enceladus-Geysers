package game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JPanel;

/**
 * This class represents a scene which we can swap to using
 * transitionScene(Scenes)
 */
public class Scene extends JPanel {
	private static final long serialVersionUID = -6926746677172868739L;
	protected final Point cameraLocation;
	private final boolean followsPlayer;
	protected final LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	protected final LinkedList<Entity> gravitational = new LinkedList<Entity>();
	private Entity playerRepresentation;
	private final double screenWidthFraction, screenHeightFraction;
	protected final LinkedList<Terrain> terrain = new LinkedList<Terrain>();
	private final GameObject defaultGameObject;

	public final void addGraphic(Graphic g) {
		defaultGameObject.addGraphic(g);
	}

	public Scene() {
		defaultGameObject = new GameObject(0, 0);
		addGameObject(defaultGameObject);
		followsPlayer = false;
		playerRepresentation = null;
		screenWidthFraction = 0;
		screenHeightFraction = 0;
		cameraLocation = new Point(0, 0);
		setLayout(null);
	}

	public Scene(double screenWidthFraction, double screenHeightFraction) {
		defaultGameObject = new GameObject(0, 0);
		addGameObject(defaultGameObject);
		followsPlayer = true;
		this.screenWidthFraction = screenWidthFraction;
		this.screenHeightFraction = screenHeightFraction;
		cameraLocation = new Point(0, 0);
		setLayout(null);
	}

	/** Will be called on every frame. */
	public void act() {
		for (GameObject go : gameObjects) {
			if (go.isExpired()) {
				gameObjects.remove(go);
				if (go instanceof Entity) {
					Entity ent = (Entity) go;
					if (ent.isGravitational())
						gravitational.remove(ent);
				} else if (go instanceof Terrain) {
					Terrain trr = (Terrain) go;
					terrain.remove(trr);
				}
			} else
				go.act();
			if (go.needsPainting()) {
				go.markPainted();
				for (Graphic g : go.getGraphics()) {
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
		for (Entity grv : gravitational) {
			boolean grounded = false;
			for (Terrain trr : terrain) {
				if (grv.collidesWith(trr)) {
					grounded |= grv.exclusionPrinciple(trr);
				}
			}
			grv.setGrounded(grounded);
		}
		Component[] c = getComponents();
		for (int x = 0; x < c.length; x++) {
			if (((Graphic) c[x]).isExpired())
				remove(c[x]);
		}
		if (followsPlayer)
			followPlayerWithCamera();
		for (GameObject go : gameObjects) {
			if (go instanceof CameraObservedObject)
				((CameraObservedObject) go).updateLocation();
		}
	}

	public void addGameObject(GameObject go) {
		gameObjects.add(go);
		go.repaint();
		if (go instanceof Entity) {
			Entity ent = (Entity) go;
			if (ent.isGravitational())
				gravitational.add(ent);
		} else if (go instanceof Terrain) {
			Terrain trr = (Terrain) go;
			terrain.add(trr);
		}
	}

	private final void followPlayerWithCamera() {
		Rectangle r = playerRepresentation.occupiedSpace.getBounds();
		Point p = new Point((int) r.getCenterX(), (int) r.getCenterY());
		p.translate((int) (-Main.GAME_PANEL_DIMENSION.getWidth() * screenWidthFraction),
				(int) (-Main.GAME_PANEL_DIMENSION.getHeight() * screenHeightFraction));
		cameraLocation.setLocation(p);
	}

	public final Point getCameraLocation() {
		return cameraLocation;
	}

	@Override
	public final Dimension getPreferredSize() {
		return Main.GAME_PANEL_DIMENSION;
	}

	public void setPlayer(Entity playerRepresentation) {
		this.playerRepresentation = playerRepresentation;
	}
}
