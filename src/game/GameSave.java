package game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * {@link AbstractMethodError} {@link Serializable} object meant to store the
 * game state in a save file.
 */
public class GameSave implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int HP;
	private final int level;
	private final ArrayList<Item> Items;

	public GameSave(int HP, int level, ArrayList<Item> Items) {
		this.HP = HP;
		this.level = level;
		this.Items = Items;
	}

	public int getHP() {
		return HP;
	}

	public int getLevel() {
		return level;
	}

	public ArrayList<Item> getItems() {
		return Items;
	}
}
