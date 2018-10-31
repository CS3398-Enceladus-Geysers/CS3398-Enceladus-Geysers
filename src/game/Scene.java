package game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * This class represents a scene which we can swap to using
 * transitionScene(Scenes)
 */
public class Scene extends JPanel {
	private static final long serialVersionUID = -6926746677172868739L;
	/**
	 * The location of the camera for the current scene, which changes how
	 * {@link CameraObservedObject}s are displayed.
	 */
	protected final Point cameraLocation;
	private final boolean followsPlayer;
	/**
	 * The collection of all {@link GameObject}s owned by this {@link Scene}.
	 */
	protected final ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	/**
	 * The collection of all {@link GameObject}s owned by this {@link Scene} that
	 * are gravitational {@link Entity}s.
	 */
	protected final ArrayList<Entity> gravitational = new ArrayList<Entity>();
	private Entity playerRepresentation;
	private final double screenWidthFraction, screenHeightFraction;
	/**
	 * The collection of all {@link GameObject}s owned by this {@link Scene} that
	 * are {@link Terrain} objects.
	 */
	protected final ArrayList<Terrain> terrain = new ArrayList<Terrain>();
	private final GameObject defaultGameObject;

	/**
	 * Adds a {@link Graphic} to the default {@link GameObject}
	 * 
	 * @param g     The {@link Graphic} to be added to the screen, offset from the
	 *              top-left of the scene.
	 * @param depth
	 */
	public final void addGraphic(Graphic g) {
		defaultGameObject.addGraphic(g);
	}

	/**
	 * The default {@link Scene} constructor which will not follow the player.
	 */
	public Scene() {
		defaultGameObject = new GameObject(0, 0);
		addGameObject(defaultGameObject);
		followsPlayer = false;
		playerRepresentation = null;
		screenWidthFraction = 0;
		screenHeightFraction = 0;
		cameraLocation = new Point(0, 0);
		setLayout(null);
		setSize(Main.GAME_PANEL_DIMENSION);
	}

	/**
	 * The {@link Scene} constructor which will follow the player.
	 * 
	 * @param screenWidthFraction  specifies the location of the {@link Scene} that
	 *                             the {@link Player} should be centered at, offset
	 *                             from the top-left corner of the scene in the
	 *                             X-axis.
	 * @param screenHeightFraction specifies the location of the {@link Scene} that
	 *                             the {@link Player} should be centered at, offset
	 *                             from the top-left corner of the scene in the
	 *                             Y-axis.
	 */
	public Scene(double screenWidthFraction, double screenHeightFraction) {
		defaultGameObject = new GameObject(0, 0);
		addGameObject(defaultGameObject);
		followsPlayer = true;
		this.screenWidthFraction = screenWidthFraction;
		this.screenHeightFraction = screenHeightFraction;
		cameraLocation = new Point(0, 0);
		setLayout(null);
		setSize(Main.GAME_PANEL_DIMENSION);
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
					for (int x = 0; x < c.length; x++)
						if (c[x] == g)
							flag = false;
					if (flag) {
						if (go == defaultGameObject)
							add(g, g.isForeground() ? 0 : -1);
						else {
							boolean flag2 = true;
							for (int x = 0; x < getComponentCount() && flag2; x++)
								if (!((Graphic) getComponent(x)).isForeground()) {
									add(g, x);
									flag2 = false;
								}
							if (flag2)
								add(g, -1);
						}
					}
				}
			}
		}
		for (Entity grv : gravitational) {
			boolean grounded = false;
			for (Terrain trr : terrain)
				if (grv.collidesWith(trr))
					grounded |= grv.exclusionPrinciple(trr);
			grv.setGrounded(grounded);
		}
		Component[] c = getComponents();
		for (int x = 0; x < c.length; x++)
			if (((Graphic) c[x]).isExpired())
				remove(c[x]);
		if (followsPlayer)
			followPlayerWithCamera();
		for (GameObject go : gameObjects)
			if (go instanceof CameraObservedObject)
				((CameraObservedObject) go).updateLocation();
		for (Component g : getComponents())
			((Graphic) g).act();
	}

	/**
	 * Adds a {@link GameObject} to the {@link Scene}.
	 * 
	 * @param go The {@link GameObject} to be added to the {@link Scene}.
	 */
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

	/**
	 * @return the camera location {@link Point} for this {@link Scene}.
	 */
	public final Point getCameraLocation() {
		return cameraLocation;
	}

	@Override
	public final Dimension getPreferredSize() {
		return Main.GAME_PANEL_DIMENSION;
	}

	/**
	 * Sets the object that this {@link Scene}'s camera should follow.
	 * 
	 * @param playerRepresentation The representation of the player in this
	 *                             {@link Scene} that the camera ought to follow.
	 */
	public void setPlayer(Entity playerRepresentation) {
		this.playerRepresentation = playerRepresentation;
	}
}
