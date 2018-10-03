package game;

import java.awt.Color;
import java.awt.Graphics;

import input.Id;


public class Player extends Entity{

	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, width, height);
		
	}

	@Override
	public void movement() {
		x+=dx;
		//y+=dy;
		
		if(x<=0) x = 0;
		//if(y>=0) y = 0;
		
		//What are our bounds?
		/*stay in bounds of screen
		if(x+width >= 1080) x = 1080 - width;
		if(y+height >= 1080) t = 1080 - height;
	*/	
	
		if(jumping) {
			gravity-=-0.1;
			setDy((int)-gravity);
			if(gravity<=0.0) {
				jumping = false;
				falling = true; 
			}
		}
		
		if(falling) {
			gravity+=0.1;
			setDy((int)gravity);
		}
	}

}
