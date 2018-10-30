package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.EnumMap;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * The main driver class for the game, also a {@link KeyListener}.
 */
public class Main implements KeyListener {
	/** A list of all the scenes. */
	enum ScenesEnum {
	LEVEL, MAIN_MENU, OVERWORLD, SETTINGS, TITLE, START_MENU
	}

	public static final HashSet<Integer> CURRENTLY_PRESSED_KEYS = new HashSet<Integer>();
	public static final int FPS_LIMIT = 30;
	/** This determines how big the game is. */
	public static final Integer SIZE_FACTOR = 60;
	/** This is the dimensions for the panel which is always displayed. */
	public static final Dimension GAME_PANEL_DIMENSION = new Dimension(16 * SIZE_FACTOR, 9 * SIZE_FACTOR);
	private static final JFrame GAME_WINDOW = new JFrame("Lunar Rebellion");;
	// TODO Add javadocs on everything.
	private static Player player;
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

	public static final Player getPlayer() {
		return player;
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

	/**
	 * Here's the meat of the program.
	 * 
	 * @throws Exception if a file cannot be loaded.
	 */
	public Main() throws Exception {
		for (ScenesEnum s : ScenesEnum.values()) {
			if (s != ScenesEnum.LEVEL)
				SCENES_MAP.put(s, new Scene());
		}
		constructScenes();
		transitionScene(ScenesEnum.LEVEL);
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

	private final void constructScenes() throws Exception {
		// Start level construction.
		SCENES_MAP.put(ScenesEnum.LEVEL, new Scene(1.0 / 2, 2.0 / 3));
		Scene level = SCENES_MAP.get(ScenesEnum.LEVEL);
		player = new Player(level.getCameraLocation());
		level.setPlayer(player);
		level.addGameObject(player);
		Graphic healthbarGraphic = new Graphic(4.0/60, 3.0/60, 150, 50) {
			private static final long serialVersionUID = 3237106029139727237L;
			int lastHP;

			@Override
			public void act() {
				if (player.getHP() != lastHP)
					repaint();
				lastHP = player.getHP();
			}

			@Override
			public void paintComponent(Graphics g) {
				// TODO Draw something based on health.
				g.drawRect(1, 1, 150, 25);
				g.setColor(Color.red);
				g.fillRect(1, 1, player.getHP(), 25);
			}
		};
		level.addGraphic(healthbarGraphic);
		Terrain dirt1 = new Terrain(level.getCameraLocation(), 0, 200.0 / 60, 100.0 / 60, 100.0 / 60, "assets/block.png",
				8, 1);
		level.addGameObject(dirt1);
		Terrain dirt2 = new Terrain(level.getCameraLocation(), 400.0 / 60, -50.0 / 60, 100.0 / 60, 100.0 / 60,
				"assets/dirt.png", 1, 1);
		level.addGameObject(dirt2);
		Terrain dirt3 = new Terrain(level.getCameraLocation(), 700.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
				"assets/dirt.png", 1, 1);
		level.addGameObject(dirt3);
		Terrain dirt4 = new Terrain(level.getCameraLocation(), 900.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
				"assets/dirt.png", 1, 1);
		level.addGameObject(dirt4);
		
		Obstacle obs1 = new Obstacle(level.getCameraLocation(), 1100.0/60, 150.0/60, 100.0/60, 100.0/60, "assets/icicle.png", 1, 1, 20);
		level.addGameObject(obs1);
		// End level construction.
		
		// Start of Title construction
		SCENES_MAP.put(ScenesEnum.TITLE, new Scene(1.0 / 2, 2.0 / 3));
		Scene title = SCENES_MAP.get(ScenesEnum.TITLE);
		Graphic titleScene = new Graphic(100.0/60, 100.0/60, 100.0/60, 100.0/60) {

			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void act() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void paintComponent(Graphics t) {
				t.drawString("Lunar Rebellion", 60, 60);
				
			}
			
		};
		
		/*Graphic startButton = new ClickableGraphic(900.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60) {
			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				transitionScene(ScenesEnum.START_MENU);
				
			}

			@Override
			public void paintComponent(Graphics m) {
				m.drawRect(60, 60, 60, 60);
			
			}
			
		};*/
		
		title.addGraphic(titleScene);
		//title.addGraphic(startButton);
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

	/** Let's not use this one, it's for typing. */
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
}