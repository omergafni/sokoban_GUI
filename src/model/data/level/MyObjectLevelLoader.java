package model.data.level;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * MyObjectLevelLoader is a LevelLoader that loads a Sokoban level from and obj file
 */
public class MyObjectLevelLoader implements LevelLoader {

	/**
	 * Loadint a level from an InputStream
	 * @param input an InputStream
	 * @return returns a Level object
	 * @throws IOException
	 */
	@Override
	public Level loadLevel(InputStream input) throws IOException {
		if (!(input instanceof ObjectInputStream))
			throw new IOException("input is not ObjectInputStream instance");
		try 
		{
			Level newLevel = (Level)(((ObjectInputStream)input).readObject());
			return newLevel;
		} catch (ClassNotFoundException e) {e.printStackTrace();}
		return null;
	}

}
