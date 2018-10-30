package game;

import java.awt.Point;

/**
 * This class represents all characters in the game which fight.
 */
public abstract class Character extends Entity {
	@SuppressWarnings("unused")
	private int armor, maxHP;
	// TODO Add more variables and methods that have to do with fighting.
	private int HP = 150;

	/**
	 * This constructor is the same as
	 * {@link Entity#Entity(Point, double, double, double, double, boolean)}, but
	 * will eventually initialize fighting related variables.
	 * 
	 * @param cameraLocation
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param gravitational
	 */
	public Character(Point cameraLocation, double x, double y, double width, double height, boolean gravitational) {
		super(cameraLocation, x, y, width, height, gravitational);
	}
  
	public void setHP(int x) {
		HP = x;
	}
  
	/**
	 * @return The HP of this character.
	 */
	public final int getHP() {
		return HP;
	}
	
	public void takeDmg(int x) {
		setHP(getHP() - x);
	}
	
	
	@Override
	public boolean exclusionPrinciple(Terrain trr) {
		if(trr instanceof Obstacle) {
			Obstacle obs = (Obstacle) trr;
			takeDmg(obs.dmg);
		}
		return super.exclusionPrinciple(trr);
	}
	
}
