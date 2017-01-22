package model.data.level;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class MyObjectLevelLoader implements LevelLoader {
		
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
