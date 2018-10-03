package game;

import java.awt.Graphics;

import input.Id;

public abstract class Tile {
	public double x, y;
	public int width, height; 
	public boolean solid;
	public int dx,dy;
	public Id id;
	public Handler handler; 
	
	public Tile(int x, int y, int width, int height, boolean solid, Id id,Handler handler){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.id = id;
		this.handler = handler;
	}
	 
	public abstract void render(Graphics g);
	public abstract void movement();
	
	public void die() {
		handler.removeTile(this);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean isSolid() {
		return solid;
	}

	public Id getId() {
		return id;
	}
	
	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

}
