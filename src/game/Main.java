package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
	LEVEL, LEVEL2, MAIN_MENU, OVERWORLD, SETTINGS, TITLE, START_MENU
	}

	/**
	 * A {@link HashSet} which represents the currently pressed keys for as long as
	 * they remain pressed.
	 */
	public static final HashSet<Integer> CURRENTLY_PRESSED_KEYS = new HashSet<Integer>();
	/**
	 * The FPS limit for this game. The main {@link Thread} sleeps until it is time
	 * for the next frame. 
	 */
	public static final int FPS_LIMIT = 30;
	/** This determines how big the game is. */
	public static final Integer SIZE_FACTOR = 60;
	/** This is the dimensions for the panel which is always displayed. */
	public static final Dimension GAME_PANEL_DIMENSION = new Dimension(16 * SIZE_FACTOR, 9 * SIZE_FACTOR);
	private static final JFrame GAME_WINDOW = new JFrame("Lunar Rebellion");
	private static Player player;
	private static Enemy enemy;

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
		transitionScene(ScenesEnum.TITLE);
		GAME_WINDOW.addKeyListener(this);
		GAME_WINDOW.setSize(GAME_PANEL_DIMENSION);
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

	private final void constructScenes() throws Exception {
		// Start level construction.
		SCENES_MAP.put(ScenesEnum.LEVEL, new Scene(1.0 / 2, 2.0 / 3));
		Scene level = SCENES_MAP.get(ScenesEnum.LEVEL);
		player = new Player(level.getCameraLocation());
		enemy = new Enemy(level.getCameraLocation());
		level.setPlayer(player);
		level.addGameObject(player);
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
				// TODO Draw something based on health.
				g.drawRect(1, 1, 150, 25);
				g.setColor(Color.white);
				g.fillRect(1, 1, 150, 25);
				g.drawRect(1, 1, 150, 25);
				g.setColor(Color.red);
				g.fillRect(1, 1, player.getHP(), 25);
			    g.setFont(new Font("TimesRoman", Font.BOLD, 12));
			    g.setColor(Color.black);
				g.drawString("HP: " + player.getHP(), 55, 15);
			}
		};
		level.addGraphic(healthbarGraphic);
		Terrain dirt1 = new Terrain(level.getCameraLocation(), 0, 100.0 / 60, 100.0 / 60, 100.0 / 60,
				"assets/still/block.png", 8, 1);
		level.addGameObject(dirt1);
		Obstacle obs1 = new Obstacle(level.getCameraLocation(), 800.0 / 60, 100.0 / 60, 100.0 / 60, 100.0 / 60,
				"assets/still/spikes.png", 1, 1, 20);
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
				"assets/still/spikes.png", 1, 1, 20);
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
				"assets/still/spikes.png", 1, 1, 20);
		level.addGameObject(obs3);
		Obstacle obs4 = new Obstacle(level.getCameraLocation(), 1900.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
				"assets/still/spikes.png", 1, 1, 20);
		level.addGameObject(obs4);
		Obstacle obs5 = new Obstacle(level.getCameraLocation(), 2000.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
				"assets/still/spikes.png", 1, 1, 20);
		level.addGameObject(obs5);
		Obstacle obs6 = new Obstacle(level.getCameraLocation(), 2100.0 / 60, -100.0 / 60, 100.0 / 60, 100.0 / 60,
				"assets/still/spikes.png", 1, 1, 20);
		level.addGameObject(obs6);
		Terrain dirt15 = new Terrain(level.getCameraLocation(), 1800.0 / 60, 0.0 / 60, 100.0 / 60, 100.0 / 60,
				"assets/still/blockBottom.png", 4, 4);
		level.addGameObject(dirt15);
		Terrain dirt16 = new Terrain(level.getCameraLocation(), 1920.0 / 60, -300.0 / 60, 100.0 / 60, 100.0 / 60,
				"assets/still/block.png", 4, 1);
		level.addGameObject(dirt16);
		Terrain portal = new Terrain(level.getCameraLocation(), 2200.0 / 60, -400.0 / 60, 100.0 / 60, 100.0 / 60,
				"assets/animated/portal/", 1, 1, true);
		level.addGameObject(portal);
		Item gun = new Item(level.getCameraLocation(), 2000.0 / 60, -800 / 60, 100.0 / 60, 100.0 / 60, "gun",
				"assets/still/gun.png", false);
		level.addGameObject(gun);

		Graphic background = new ImageGraphic("assets/still/enceladus.png", 0, 0, 16, 9, false);
		level.addGraphic(background);
		// End level construction.

		// START OF TITLE CONSTRUCTION
		Scene title = SCENES_MAP.get(ScenesEnum.TITLE);

		Graphic backgroundTitle = new ImageGraphic("assets/still/title.png", 0, 0, 16, 8.5, false);

		title.addGraphic(backgroundTitle);

		Graphic start = new Graphic(0, 0, 150, 50, true) {
			private static final long serialVersionUID = 3237106029139727237L;

				@Override
				public void act() {
					// TODO Auto-generated method stub
					repaint();
				}

				@Override
				public void paintComponent(Graphics t) {
					t.setFont(new Font("Venus Rising", Font.BOLD, 28));
					t.setColor(new Color(63,0,255));
					t.drawString("Start", 395, 300);
				}
		     };

    
		Graphic startButton = new ClickableGraphic(start) {


			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				transitionScene(ScenesEnum.START_MENU);

			}

		};
		
		title.addGraphic(starting);
	
		// END OF TITLE CONSTRUCTION
		
		
	    // BEGINNING OF START MENU CONSTRUCTION
	    Scene menu = SCENES_MAP.get(ScenesEnum.START_MENU);
	    
		Graphic backgroundMenu = new ImageGraphic("assets/enceladus.png", 0, 0, 16, 9, false);

		menu.addGraphic(backgroundMenu);
		
	    Graphic menuScene = new Graphic(0, 0, 150, 50) {
	    
	private static final long serialVersionUID = 3237106029139727237L;

	    	@Override
	    	public void act() {
					// FIXME Review this.
	    		repaint();
	    	}

	    	@Override
	    	public void paintComponent(Graphics s) {

	    		s.setFont(new Font("Venus Rising", Font.BOLD, 24));
	    		s.setColor(Color.WHITE);
	    		s.drawString("MAIN MENU", 400, 100);
	    	}
	  
	     };
	  
	     menu.addGraphic(menuScene);
	     
	     Graphic startB = new Graphic(0, 0, 150, 50) {

			private static final long serialVersionUID = 3237106029139727237L;

			@Override
			public void act() {
				// TODO Auto-generated method stub
				repaint();
			}

			@Override
			public void paintComponent(Graphics t) {
				t.setFont(new Font("Venus Rising", Font.BOLD, 18));
				t.setColor(Color.WHITE);
				t.drawString("START GAME", 375, 200);
			}

	     };
	     Graphic startGame = new ClickableGraphic(startB) {

				private static final long serialVersionUID = 3237106029139727237L;

				@Override
				public void mouseClicked(MouseEvent arg0) {
					transitionScene(ScenesEnum.LEVEL);
					
				}
				
			};
		
		 Graphic optionsB = new Graphic(0, 0, 150, 50) {
				private static final long serialVersionUID = 3237106029139727237L;

					@Override
					public void act() {
						// TODO Auto-generated method stub
						repaint();
					}

					@Override
					public void paintComponent(Graphics t) {
						t.setFont(new Font("Venus Rising", Font.BOLD, 18));
						t.setColor(Color.WHITE);
						t.drawString("SETTINGS", 375, 250);
					}
			     };			
			     
		  Graphic options = new ClickableGraphic(optionsB) {

				private static final long serialVersionUID = 3237106029139727237L;

					@Override
					public void mouseClicked(MouseEvent arg0) {
						transitionScene(ScenesEnum.SETTINGS);
							
					}
						
			};
			
			Graphic quitB = new Graphic(0, 0, 150, 50) {
				private static final long serialVersionUID = 3237106029139727237L;

					@Override
					public void act() {
						// TODO Auto-generated method stub
						repaint();
					}

					@Override
					public void paintComponent(Graphics t) {
						t.setFont(new Font("Venus Rising", Font.BOLD, 18));
						t.setColor(Color.WHITE);
						t.drawString("QUIT", 375, 300);
					}
			     };			
			     
		  Graphic quitting = new ClickableGraphic(quitB) {

				private static final long serialVersionUID = 3237106029139727237L;

					@Override
					public void mouseClicked(MouseEvent arg0) {
						transitionScene(ScenesEnum.LEVEL);
							
					}
						
			};
			
		 menu.addGraphic(startGame);
	     menu.addGraphic(options);
	     menu.addGraphic(quitting);
	     
	     // END OF START MENU CONSTRUCTION

	    
	}

   	// BEGINNING OF SETTINGS CONSTRUCTION
	 	
	 	Scene settings = SCENES_MAP.get(ScenesEnum.SETTINGS);
	     
	 	Graphic settingsBackground = new ImageGraphic("assets/space.png", 0, 0, 16, 9, false);
	 	settings.addGraphic(settingsBackground);
	 	
	     Graphic settingsScene = new Graphic(0, 0, 150, 50) {

	     	private static final long serialVersionUID = 3237106029139727237L;

	     	@Override
	     	public void act() {
	 				// FIXME Review this.
	     		repaint();
	     	}

	     	@Override
	     	public void paintComponent(Graphics s) {

	     		s.setFont(new Font("Arial", Font.BOLD, 24));
	     		s.setColor(Color.black);
	     		s.drawString("SETTINGS", 400, 100);
	     	}
	   
	      };
	   
	      settings.addGraphic(settingsScene);
	 	 
	      // END OF SETTINGS CONSTRUCTION
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
