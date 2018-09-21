package game;

import java.awt.Dimension;
import java.util.EnumMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	/** A list of all the scenes. */
	enum Scenes {
	TITLE, MAIN_MENU, SETTINGS, OVERWORLD, LEVEL
	};

	/** This determines how big the game is. */
	private static final int SIZE_FACTOR = 60;
	/** This is the dimensions for the panel which is always displayed. */
	private static final Dimension GAME_PANEL_DIMENSION = new Dimension(16 * SIZE_FACTOR, 9 * SIZE_FACTOR);
	/**
	 * This is a Map for holding scenes, which are just JPanels which get switched
	 * from title to game, etc.
	 */
	private static final EnumMap<Scenes, Scene> SCENES = new EnumMap<Scenes, Scene>(Scenes.class);
	/** This variable tells us which scene we're currently in. */
	private static Scenes scene;
	/** This is the panel which will always be displayed in the window. */
	private static final JPanel GAME_PANEL = new JPanel() {
		private static final long serialVersionUID = -2993152027099833672L;

		@Override
		public Dimension getPreferredSize() {
			return GAME_PANEL_DIMENSION;
		}
	};;

	/**
	 * This class represents a scene which we can swap to using
	 * transitionScene(Scenes)
	 */
	class Scene extends JPanel {
		private static final long serialVersionUID = -6926746677172868739L;
		private final Dimension size;

		public final Dimension getPreferredSize() {
			return size;
		}

		public Scene(Dimension size) {
			this.size = size;
		}
	}

	/** Here's the meat of the program. */
	public Main() {
		for (Scenes s : Scenes.values()) {
			SCENES.put(s, new Scene(GAME_PANEL_DIMENSION));
		}
		// TODO Add components to each other scene.
		Scene level = SCENES.get(Scenes.LEVEL);// Get other scenes similarly.
		level.add(new JLabel("Hello World!"));// Demonstration.
		// TODO Add components to the level.
		JFrame gameWindow = new JFrame("Title TBA");
		transitionScene(Scenes.LEVEL);// Demonstration.
		gameWindow.add(GAME_PANEL);
		gameWindow.pack();
		gameWindow.setResizable(false);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setVisible(true);
	}

	/** Swap out scenes to the scene specified in the parameter. */
	private void transitionScene(Scenes scene) {
		if (this.scene != null)
			GAME_PANEL.remove(SCENES.get(Main.scene));
		Main.scene = scene;
		GAME_PANEL.add(SCENES.get(scene));
		GAME_PANEL.repaint();
	}

	/** Just instantiate a Main Object here. */
	public static void main(String[] args) {
		new Main();
	}
}