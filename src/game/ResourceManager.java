package game;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A class to be statically called in order to save and load
 * {@link Serializable} objects.
 */
public class ResourceManager {
	/**
	 * Load a {@link Serializable} object from a file specified by {@code fileName}
	 * 
	 * @param fileName The name of the file to be loaded from.
	 * @return The {@link Serializable} object that was loaded from a file.
	 * @throws Exception in the case of IO failure.
	 */
	public static Object Load(String fileName) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
		Object ret = ois.readObject();
		ois.close();
		return ret;
	}

	/**
	 * Save a {@link Serializable} object to a file specified by {@code fileName}
	 * 
	 * @param data     The {@link Serializable} object to be saved to a file.
	 * @param fileName The name of the file to be saved to.
	 * @throws Exception in the case of IO failure.
	 */
	public static void save(Serializable data, String fileName) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		oos.writeObject(data);
		oos.close();
	}
}