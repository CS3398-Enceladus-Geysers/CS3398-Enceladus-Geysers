package game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EnumMap;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * The main driver class for the game, also a {@link KeyListener}.
 */
public class Main implements KeyListener {
	/**
	 * This class represents a scene which we can swap to using
	 * transitionScene(Scenes)
	 */
	class Scene extends JPanel {
		private static final long serialVersionUID = -6926746677172868739L;
		private final Point cameraLocation;
		private final LinkedList<GameComponent> gameComponents = new LinkedList<GameComponent>();

		public Scene() {
			cameraLocation = new Point(0, 0);
			setLayout(null);
		}

		/** Will be called on every frame. */
		public void act() {
			for (GameComponent gc : gameComponents) {
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
				gc.updateBounds();
			}
			Component[] c = getComponents();
			for (int x = 0; x < c.length; x++) {
				if (((Graphic) c[x]).isExpired())
					remove(c[x]);
			}
		}

		public void addGameComponent(GameComponent gc) {
			gameComponents.add(gc);
			gc.repaint();
		}

		@Override
		public final Dimension getPreferredSize() {
			return GAME_PANEL_DIMENSION;
		}
	}

	/** A list of all the scenes. */
	enum Scenes {
		LEVEL, MAIN_MENU, OVERWORLD, SETTINGS, TITLE
	};

	/** This determines how big the game is. */
	public static final Integer SIZE_FACTOR = 60;

	/** This is the dimensions for the panel which is always displayed. */
	private static final Dimension GAME_PANEL_DIMENSION = new Dimension(16 * SIZE_FACTOR, 9 * SIZE_FACTOR);
	private static final JFrame GAME_WINDOW = new JFrame("Title TBA");
	/** This variable tells us which scene we're currently in. */
	private static Scenes scene;
	/**
	 * This is a Map for holding scenes, which are just JPanels which get switched
	 * from title to game, etc.
	 */
	private static final EnumMap<Scenes, Scene> SCENES = new EnumMap<Scenes, Scene>(Scenes.class);

	/**
	 * Here's the meat of the program.
	 * 
	 * @throws Exception if a file cannot be loaded.
	 */
	public Main() throws Exception {
		for (Scenes s : Scenes.values()) {
			SCENES.put(s, new Scene());
		}
		// TODO Add components to each other scene.
		Scene level = SCENES.get(Scenes.LEVEL);// Get other scenes similarly.
		LevelComponent gc = new LevelComponent(level.cameraLocation, new Point(0, 0));
		gc.addGraphic(new ImageGraphic("assets/Scribble.png", 1 / .6, 1 / .6));
		level.addGameComponent(gc);
		// TODO Add components to the level.
		transitionScene(Scenes.LEVEL);// Demonstration.
		GAME_WINDOW.addKeyListener(this);
		GAME_WINDOW.pack();
		GAME_WINDOW.setResizable(false);
		GAME_WINDOW.setLocationRelativeTo(null);
		GAME_WINDOW.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		GAME_WINDOW.setVisible(true);
		while (true) {
			level.act();
		}
	}

	/** We can use this method to listen for keyboard input from our window. */
	@Override
	public void keyPressed(KeyEvent e) {
		Point camera = SCENES.get(Scenes.LEVEL).cameraLocation;
		switch (e.getKeyCode()) {
		case 37:// Left
			camera.translate(5, 0);
			break;
		case 38:// Up
			camera.translate(0, 5);// Y+ axis is down.
			break;
		case 39:// Right
			camera.translate(-5, 0);
			break;
		case 40:// Down
			camera.translate(0, -5);
			break;
		default:
		}
	}

	/** Let's not use this one, it still must be implemented though. */
	@Override
	public void keyReleased(KeyEvent e) {
	}

	/** Let's not use this one, it still must be implemented though. */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/** Swap out scenes to the scene specified in the parameter. */
	private void transitionScene(Scenes scene) {
		if (Main.scene != null)
			GAME_WINDOW.remove(SCENES.get(Main.scene));
		Main.scene = scene;
		GAME_WINDOW.add(SCENES.get(scene));
		GAME_WINDOW.repaint();
	}

	/**
	 * Just instantiate a Main Object here.
	 * 
	 * @param args arguments passed by the command line, which are ignored.
	 * @throws Exception if a file cannot be loaded.
	 */
	public static void main(String[] args) throws Exception {
		new Main();
	}
}