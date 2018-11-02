package game;

import java.io.Serializable;

/**
 * {@link AbstractMethodError} {@link Serializable} object meant to store the
 * game state in a save file.
 */
public class GameSave implements Serializable {
	private static final long serialVersionUID = 1L;
	// private Items items;
	@SuppressWarnings("unused")
	private int HP;
	@SuppressWarnings("unused")
	private int level;
}
