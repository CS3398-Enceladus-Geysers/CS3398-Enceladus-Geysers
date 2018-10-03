package game;

import java.awt.Color;
import java.awt.Graphics;

import input.Id;

public class Wall extends Tile {

	public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, width, height);
	}
	
	public void movement() {
		
	}

}
