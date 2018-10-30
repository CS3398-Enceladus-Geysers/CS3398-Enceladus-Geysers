package game;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceManager {
	
	public static void save(Serializable data, String fileName) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		oos.writeObject(data);
	}

	public static Object Load(String fileName) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName))); 
			return ois.readObject();
	}
}