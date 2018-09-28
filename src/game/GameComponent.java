package game;

import java.util.ArrayList;

public class GameComponent {
	// GameWindow)
	private final ArrayList<Graphic> graphics = new ArrayList<Graphic>();
							private boolean repaint;// TODO Use this to add components back once they're not visible. (In

	public final ArrayList<Graphic> getGraphics() {
		return graphics;
	}
}