package game;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EnumMap;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * The main driver class for the game, also a {@link KeyListener}.
 */
public class Main implements KeyListener {
	private Player player;
	public static final HashSet<Integer> CURRENTLY_PRESSED_KEYS = new HashSet<Integer>();
	public static final int FPS_LIMIT = 30;

	/** A list of all the scenes. */
	enum ScenesEnum {
		LEVEL, MAIN_MENU, OVERWORLD, SETTINGS, TITLE
	};

	/** This determines how big the game is. */
	public static final Integer SIZE_FACTOR = 60;

	/** This is the dimensions for the panel which is always displayed. */
	public static final Dimension GAME_PANEL_DIMENSION = new Dimension(16 * SIZE_FACTOR, 9 * SIZE_FACTOR);
	private static final JFrame GAME_WINDOW = new JFrame("Lunar Rebellion");
	/** This variable tells us which scene we're currently in. */
	private static ScenesEnum scene;
	/**
	 * This is a Map for holding scenes, which are just JPanels which get switched
	 * from title to game, etc.
	 */
	private static final EnumMap<ScenesEnum, Scene> SCENES_MAP = new EnumMap<ScenesEnum, Scene>(ScenesEnum.class);

	private static final Scene currentScene() {
		return SCENES_MAP.get(scene);
	}

	/**
	 * Here's the meat of the program.
	 * 
	 * @throws Exception if a file cannot be loaded.
	 */
	public Main() throws Exception {
		for (ScenesEnum s : ScenesEnum.values()) {
			if (s == ScenesEnum.LEVEL) {
				SCENES_MAP.put(s, new Scene() {
					private static final long serialVersionUID = 6758001109836539535L;

					@Override
					public void act() {
						super.act();
						// Act for just level
						Rectangle r = player.occupiedSpace.getBounds();
						Point p = new Point((int) r.getCenterX(), (int) r.getCenterY());
						p.translate((int) (-GAME_PANEL_DIMENSION.getWidth() / 2),
								(int) (-GAME_PANEL_DIMENSION.getHeight() * 2 / 3));
						cameraLocation.setLocation(p);
						// TODO Do physics on terrain.
					}
				});
			} else
				SCENES_MAP.put(s, new Scene());
		}
		// TODO Add components to each other scene.
		transitionScene(ScenesEnum.LEVEL);
		Scene level = SCENES_MAP.get(ScenesEnum.LEVEL);
		player = new Player(level.getCameraLocation());
		level.addGameComponent(player);
		// TODO Add components to the level.
		// Demonstration.
		GAME_WINDOW.addKeyListener(this);
		GAME_WINDOW.pack();
		GAME_WINDOW.setResizable(false);
		GAME_WINDOW.setLocationRelativeTo(null);
		GAME_WINDOW.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		GAME_WINDOW.setVisible(true);
		long lastFrameTime = System.currentTimeMillis();
		while (true) {
			if (System.currentTimeMillis() - lastFrameTime < 1000.0 / FPS_LIMIT)
				Thread.sleep((int) (1000.0 / FPS_LIMIT - System.currentTimeMillis() + lastFrameTime));
			lastFrameTime = System.currentTimeMillis();
			currentScene().act();
		}
	}

	/** We can use this method to listen for keyboard input from our window. */
	@Override
	public void keyPressed(KeyEvent e) {
		CURRENTLY_PRESSED_KEYS.add(e.getKeyCode());
	}

	/** We can use this method to listen for keyboard input from our window. */
	@Override
	public void keyReleased(KeyEvent e) {
		CURRENTLY_PRESSED_KEYS.remove(e.getKeyCode());
	}

	/** Let's not use this one, it still must be implemented though. */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/** Swap out scenes to the scene specified in the parameter. */
	private void transitionScene(ScenesEnum scene) {
		if (Main.scene != null)
			GAME_WINDOW.remove(SCENES_MAP.get(Main.scene));
		Main.scene = scene;
		GAME_WINDOW.add(SCENES_MAP.get(scene));
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