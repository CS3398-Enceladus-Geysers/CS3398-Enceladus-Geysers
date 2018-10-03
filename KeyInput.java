package input;

import game.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(Entity en:game.Handler.entity) {
			switch(key) {
			case KeyEvent.VK_UP:
				if(!en.jumping) {
					en.jumping = true;
					en.gravity = 10.0; 
				}
				break;
			case KeyEvent.VK_DOWN:
				en.setDy(-5);
				break;
			case KeyEvent.VK_LEFT:
				en.setDx(-5);
				break;
			case KeyEvent.VK_RIGHT:
				en.setDx(-5);
				break;
			
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(Entity en:game.Handler.entity) {
			switch(key) {
			case KeyEvent.VK_UP:
				en.setDy(0);
				break;
			case KeyEvent.VK_DOWN:
				en.setDy(0);
				break;
			case KeyEvent.VK_LEFT:
				en.setDx(0);
				break;
			case KeyEvent.VK_RIGHT:
				en.setDx(0);
				break;
			
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}

