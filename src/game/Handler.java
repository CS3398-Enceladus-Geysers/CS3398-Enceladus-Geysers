package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	public static LinkedList<Entity> entity = new LinkedList<Entity>();
	public LinkedList<Tile> tile = new LinkedList<Tile>();
	
	public void render(Graphics g) {
		for(Entity en:entity) {
			en.render(g);
		}
		
		for(Tile ti:tile) {
			ti.render(g);
		}
	}
	
	public void movement() {
		for(Entity en:entity) {
			en.movement();
		}
		
		for(Tile ti:tile) {
			ti.movement();
		}
	}
	
	public void addEntity(Entity en) {
		entity.add(en);
	}
	
	public void removeEntity(Entity en) {
		entity.remove(en);
	}
	
	public void addTile(Tile ti) {
		tile.add(ti);
	}
	
	public void removeTile(Tile ti) {
		tile.remove(ti);
	}

}
