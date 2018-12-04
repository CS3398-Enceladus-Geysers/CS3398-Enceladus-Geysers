package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.EnumMap;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * The main driver class for the game, also a {@link KeyListener}.
 */
public class Main implements KeyListener, WindowListener {
	/** A list of all the scenes. */
	public enum ScenesEnum {
	LEVEL, MAIN_MENU, CHARACTERS, SETTINGS, START_MENU, TITLE
	}

	public GameSave save;

	/**
	 * A {@link HashSet} which represents the currently pressed keys for as long as
	 * they remain pressed.
	 */
	public static final HashSet<Integer> CURRENTLY_PRESSED_KEYS = new HashSet<Integer>();
	private static Enemy enemy;
	/**
	 * The FPS limit for this game. The main {@link Thread} sleeps until it is time
	 * for the next frame.
	 */
	public static final int FPS_LIMIT = 30;
	/** This is the dimensions for the panel which is always displayed. */
	/** This determines how big the game is. */
	public static Integer sizeFactor = 60;
	public static Dimension gamePanelDimension = new Dimension(16 * sizeFactor, 9 * sizeFactor);
	private static final JFrame GAME_WINDOW = new JFrame("Lunar Rebellion");
	private static Player player;
	/** This variable tells us which scene we're currently in. */
	private static ScenesEnum scene;
	public static int currentLevel;
	/**
	 * This is a Map for holding scenes, which are just JPanels which get switched
	 * from title to game, etc.
	 */
	private static final EnumMap<ScenesEnum, Scene> SCENES_MAP = new EnumMap<ScenesEnum, Scene>(ScenesEnum.class);

	public static void constructLevel() throws Exception {
		// Start level construction.
		SCENES_MAP.put(ScenesEnum.LEVEL, new Scene(1.0 / 2, 2.0 / 3));
		Scene level = SCENES_MAP.get(ScenesEnum.LEVEL);
		player = new Player(level.getCameraLocation());
		level.setPlayer(player);
		level.addGameObject(player);
		switch (currentLevel) {
		case 1:
			enemy = new Enemy(level.getCameraLocation());
			level.addGameObject(enemy);

			Graphic healthbarGraphic = new Graphic(4.0 / 60, 3.0 / 60, 150, 50) {
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
					g.drawRect(1, 1, 200, 25);
					g.setColor(Color.white);
					g.fillRect(1, 1, 200, 25);
					g.drawRect(1, 1, 200, 25);
					g.setColor(Color.red);
					g.fillRect(1, 1, player.getHP(), 25);
					g.setFont(new Font("Venus Rising", Font.BOLD, 12));
					g.setColor(Color.black);
					g.drawString("HP: " + player.getHP(), 65, 15);
				}
			};
			level.addGraphic(healthbarGraphic);
			Terrain dirt1 = new Terrain(level.getCameraLocation(), 0, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/block.png", 8, 1);
			level.addGameObject(dirt1);
			Obstacle obs1 = new Obstacle(level.getCameraLocation(), 800.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/spikes.png", 1, 1, 50);
			level.addGameObject(obs1);
			Terrain dirt3 = new Terrain(level.getCameraLocation(), 900.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/block.png", 2, 1);
			level.addGameObject(dirt3);
			Terrain dirt4 = new Terrain(level.getCameraLocation(), 1100.0 / 60, 0.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/block.png", 1, 1);
			level.addGameObject(dirt4);
			Terrain dirt5 = new Terrain(level.getCameraLocation(), 1100.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/blockBottom.png", 1, 1);
			level.addGameObject(dirt5);
			Terrain dirt6 = new Terrain(level.getCameraLocation(), 1200.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/block.png", 1, 1);
			level.addGameObject(dirt6);
			Obstacle obs2 = new Obstacle(level.getCameraLocation(), 1300.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/spikes.png", 1, 1, 50);
			level.addGameObject(obs2);
			Terrain dirt7 = new Terrain(level.getCameraLocation(), 1300.0 / 60, -120.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/block.png", 1, 1);
			level.addGameObject(dirt7);
			Terrain dirt8 = new Terrain(level.getCameraLocation(), 1400.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/block.png", 1, 1);
			level.addGameObject(dirt8);
			Terrain dirt9 = new Terrain(level.getCameraLocation(), 1500.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/blockBottom.png", 1, 1);
			level.addGameObject(dirt9);
			Terrain dirt10 = new Terrain(level.getCameraLocation(), 1500.0 / 60, 0.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/block.png", 1, 1);
			level.addGameObject(dirt10);
			Terrain dirt11 = new Terrain(level.getCameraLocation(), 1600.0 / 60, 0.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/blockBottom.png", 1, 2);
			level.addGameObject(dirt11);
			Terrain dirt12 = new Terrain(level.getCameraLocation(), 1600.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/block.png", 1, 1);
			level.addGameObject(dirt12);
			Terrain dirt13 = new Terrain(level.getCameraLocation(), 1700.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/blockBottom.png", 1, 3);
			level.addGameObject(dirt13);
			Terrain dirt14 = new Terrain(level.getCameraLocation(), 1700.0 / 60, -200.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/block.png", 1, 1);
			level.addGameObject(dirt14);
			Obstacle obs3 = new Obstacle(level.getCameraLocation(), 1800.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/spikes.png", 1, 1, 50);
			level.addGameObject(obs3);
			Obstacle obs4 = new Obstacle(level.getCameraLocation(), 1900.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/spikes.png", 1, 1, 50);
			level.addGameObject(obs4);
			Obstacle obs5 = new Obstacle(level.getCameraLocation(), 2000.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/spikes.png", 1, 1, 50);
			level.addGameObject(obs5);
			Obstacle obs6 = new Obstacle(level.getCameraLocation(), 2100.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/spikes.png", 1, 1, 50);
			level.addGameObject(obs6);
			Terrain dirt15 = new Terrain(level.getCameraLocation(), 1800.0 / 60, 0.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/blockBottom.png", 4, 4);
			level.addGameObject(dirt15);
			Terrain dirt16 = new Terrain(level.getCameraLocation(), 1920.0 / 60, -300.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/block.png", 4, 1);
			level.addGameObject(dirt16);
			Terrain portal = new Portal(level.getCameraLocation(), 2200.0 / 60, -400.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/animated/portal/", 2);
			level.addGameObject(portal);
			Item gun = new Item(level.getCameraLocation(), 2000.0 / 60, -800 / 60, 100.0 / 60, 100.0 / 60, "gun",
					"assets/still/gun.png", false);
			level.addGameObject(gun);
			Graphic background = new ImageGraphic("assets/still/enceladus.png", 0, 0, 16, 9, false);
			level.addGraphic(background);
			// End level construction.
			break;
		case 2:
			Graphic background1 = new ImageGraphic("assets/still/titan.png", 0, 0, 16, 9, false);
			level.addGraphic(background1);

			enemy = new Enemy(level.getCameraLocation());
			level.addGameObject(enemy);
			Graphic healthbarGraphic1 = new Graphic(4.0 / 60, 3.0 / 60, 150, 50) {
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
					g.drawRect(1, 1, 200, 25);
					g.setColor(Color.white);
					g.fillRect(1, 1, 200, 25);
					g.drawRect(1, 1, 200, 25);
					g.setColor(Color.red);
					g.fillRect(1, 1, player.getHP(), 25);
					g.setFont(new Font("Venus Rising", Font.BOLD, 12));
					g.setColor(Color.black);
					g.drawString("HP: " + player.getHP(), 65, 15);
				}
			};

			level.addGraphic(healthbarGraphic1);
			Terrain terr1 = new Terrain(level.getCameraLocation(), 0, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/red.png", 8, 1);
			level.addGameObject(terr1);
			Obstacle lava1 = new Obstacle(level.getCameraLocation(), 800.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/animated/lava/", 1, 1, 50, true);
			level.addGameObject(lava1);
			Terrain terr2 = new Terrain(level.getCameraLocation(), 900.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/red.png", 2, 1);
			level.addGameObject(terr2);
			Terrain terr3 = new Terrain(level.getCameraLocation(), 1100.0 / 60, 0.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/red.png", 1, 1);
			level.addGameObject(terr3);
			Terrain terr4 = new Terrain(level.getCameraLocation(), 1100.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/redBottom.png", 1, 1);
			level.addGameObject(terr4);
			Terrain terr5 = new Terrain(level.getCameraLocation(), 1200.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/red.png", 1, 1);
			level.addGameObject(terr5);
			Obstacle lava2 = new Obstacle(level.getCameraLocation(), 1300.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/animated/lava/", 1, 1, 50, true);
			level.addGameObject(lava2);
			Terrain terr6 = new Terrain(level.getCameraLocation(), 1400.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/red.png", 1, 1);
			level.addGameObject(terr6);
			Terrain terr7 = new Terrain(level.getCameraLocation(), 1500.0 / 60, 0.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/red.png", 1, 1);
			level.addGameObject(terr7);
			Terrain terr8 = new Terrain(level.getCameraLocation(), 1500.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/redBottom.png", 1, 1);
			level.addGameObject(terr8);
			Terrain terr9 = new Terrain(level.getCameraLocation(), 1600.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/red.png", 1, 1);
			level.addGameObject(terr9);
			Terrain terr10 = new Terrain(level.getCameraLocation(), 1600.0 / 60, 0.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/redBottom.png", 1, 1);
			level.addGameObject(terr10);
			Terrain terr11 = new Terrain(level.getCameraLocation(), 1600.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/redBottom.png", 1, 1);
			level.addGameObject(terr11);
			Terrain terr12 = new Terrain(level.getCameraLocation(), 1700.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/red.png", 1, 1);
			level.addGameObject(terr12);
			Terrain terr13 = new Terrain(level.getCameraLocation(), 1700.0 / 60, 0.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/redBottom.png", 1, 1);
			level.addGameObject(terr13);
			Terrain terr14 = new Terrain(level.getCameraLocation(), 1700.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/redBottom.png", 1, 1);
			level.addGameObject(terr14);
			Terrain terr15 = new Terrain(level.getCameraLocation(), 1800.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/red.png", 1, 1);
			level.addGameObject(terr15);
			Terrain terr16 = new Terrain(level.getCameraLocation(), 1900.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
					"assets/still/red.png", 1, 1);
			level.addGameObject(terr16);
			// TODO Level 2 construction.
			break;
		}
	}

	private static final Scene currentScene() {
		return SCENES_MAP.get(scene);
	}

	/**
	 * @return the {@link Player} object.
	 */
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

	/** Swap out scenes to the scene specified in the parameter. */
	public static void transitionScene(ScenesEnum scene) {
		if (Main.scene != null)
			GAME_WINDOW.remove(SCENES_MAP.get(Main.scene));
		Main.scene = scene;
		GAME_WINDOW.add(SCENES_MAP.get(scene));
		GAME_WINDOW.repaint();
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
		currentLevel = 1;
		save = load();
		constructScenes();
		transitionScene(ScenesEnum.TITLE);
		GAME_WINDOW.addKeyListener(this);
		GAME_WINDOW.addWindowListener(this);
		GAME_WINDOW.setSize(gamePanelDimension);
		GAME_WINDOW.setResizable(false);
		GAME_WINDOW.setLocationRelativeTo(null);
		GAME_WINDOW.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		currentScene().act();
		GAME_WINDOW.setVisible(true);
		long lastFrameTime = System.currentTimeMillis();
		while (true) {
			if (System.currentTimeMillis() - lastFrameTime < 1000.0 / FPS_LIMIT)
				Thread.sleep((int) (1000.0 / FPS_LIMIT - System.currentTimeMillis() + lastFrameTime));
			lastFrameTime = System.currentTimeMillis();
			currentScene().act();
		}
	}

	private GameSave load() {
		try {
			return (GameSave) ResourceManager.Load("game.sav");
		} catch (Exception e) {
			return null;
		}
	}

	private final void constructScenes() throws Exception {
		if (save != null)
			currentLevel = save.getLevel();
		constructLevel();

		Scene title = SCENES_MAP.get(ScenesEnum.TITLE);
		Graphic backgroundTitle = new ImageGraphic("assets/still/title.png", 0, 0, 16, 8.5, false);
		title.addGraphic(backgroundTitle);
		Graphic start = new TextGraphic(425.0 / 60, 275.0 / 60, 150.0 / 60, 24.0 / 60, "START", "Comic Sans",
				Color.GREEN);

		Graphic startButton = new ClickableGraphic(start) {
			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				transitionScene(ScenesEnum.START_MENU);
			}
		};

		title.addGraphic(startButton);

		// END OF TITLE CONSTRUCTION

		// BEGINNING OF START MENU CONSTRUCTION
		Scene menu = SCENES_MAP.get(ScenesEnum.START_MENU);

		Graphic backgroundMenu = new ImageGraphic("assets/still/enceladus.png", 0, 0, 16, 9, false);
		menu.addGraphic(backgroundMenu);
		Graphic menuScene = new TextGraphic(375.0 / 60, 100.0 / 60, 150.0 / 60, 24.0 / 60, "MAIN MENU", "Comic Sans",
				Color.WHITE);

		menu.addGraphic(menuScene);

		Graphic startB = new TextGraphic(375.0 / 60, 200.0 / 60, 175.0 / 60, 24.0 / 60, "START GAME", "Comic Sans",
				Color.WHITE);

		Graphic startGame = new ClickableGraphic(startB) {
			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				transitionScene(ScenesEnum.LEVEL);

			}

		};

		Graphic optionsB = new TextGraphic(375.0 / 60, 250.0 / 60, 175.0 / 60, 24.0 / 60, "SETTINGS", "Comic Sans",
				Color.WHITE);

		Graphic options = new ClickableGraphic(optionsB) {
			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				transitionScene(ScenesEnum.SETTINGS);

			}

		};

		Graphic quitB = new TextGraphic(375.0 / 60, 300.0 / 60, 175.0 / 60, 24.0 / 60, "QUIT", "Comic Sans",
				Color.WHITE);

		Graphic quitting = new ClickableGraphic(quitB) {
			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}

		};

		menu.addGraphic(startGame);
		menu.addGraphic(options);
		menu.addGraphic(quitting);
		// END OF START MENU CONSTRUCTION
		// START SETTINGS CONSTRUCTION

		Scene settings = SCENES_MAP.get(ScenesEnum.SETTINGS);

		Graphic backgroundSettings = new ImageGraphic("assets/still/enceladus.png", 0, 0, 16, 9, false);
		settings.addGraphic(backgroundSettings);
		Graphic settingScene = new TextGraphic(375.0 / 60, 100.0 / 60, 150.0 / 60, 24.0 / 60, "SETTINGS", "Comic Sans",
				Color.WHITE);

		settings.addGraphic(settingScene);

		Graphic resizeB = new TextGraphic(375.0 / 60, 200.0 / 60, 175.0 / 60, 24.0 / 60, "FULL SCREEN", "Comic Sans",
				Color.WHITE);

		Graphic resize = new ClickableGraphic(resizeB) {
			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				fullscreen();
			}

		};

		settings.addGraphic(resize);

		Graphic newCharacterB = new TextGraphic(375.0 / 60, 250.0 / 60, 350.0 / 60, 24.0 / 60, "CHOOSE NEW CHARACTER",
				"Comic Sans", Color.WHITE);

		Graphic newCharacter = new ClickableGraphic(newCharacterB) {
			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				transitionScene(ScenesEnum.CHARACTERS);
			}
		};
		Graphic backM = new TextGraphic(375.0 / 60, 375.0 / 60, 275.0 / 60, 24.0 / 60, "Back to Main Menu", "Comic Sans",
				Color.WHITE);

		Graphic back = new ClickableGraphic(backM) {
			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				transitionScene(ScenesEnum.START_MENU);
			}

		};
		settings.addGraphic(back);
		settings.addGraphic(newCharacter);
		// END OF SETTINGS CONSTRUCTION
		// BEGINNING OF CHARACTERS CONSTRUCTION
		Scene characters = SCENES_MAP.get(ScenesEnum.CHARACTERS);

		Graphic backgroundCharacters = new ImageGraphic("assets/still/enceladus.png", 0, 0, 16, 9, false);
		characters.addGraphic(backgroundCharacters);
		Graphic charactersScene = new TextGraphic(375.0 / 60, 100.0 / 60, 175.0 / 60, 24.0 / 60, "CHARACTERS", "Comic Sans",
				Color.WHITE);
        characters.addGraphic(charactersScene);
        
        Graphic backtoM = new TextGraphic(400.0 / 60, 375.0 / 60, 275.0 / 60, 24.0 / 60, "Back to Main Menu", "Comic Sans",
				Color.WHITE);

		Graphic backMenu = new ClickableGraphic(backtoM) {
			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				transitionScene(ScenesEnum.START_MENU);
			}

		};
		characters.addGraphic(backMenu);
		// END OF CHARACTERS CONSTRUCTION
		if (save != null) {
			for (Item itm : save.getItems())
				getPlayer().addItem(itm);
			getPlayer().setHP(save.getHP());
		}
	}

	protected void fullscreen() {
		// TODO Do fullscreen 2560x1600
		// TODO Also remember to get rid of it if esc is pressed.
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

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		save();
	}

	private void save() {
		try {
			ResourceManager.save(new GameSave(getPlayer().getHP(), currentLevel, getPlayer().getItems()), "game.sav");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}
