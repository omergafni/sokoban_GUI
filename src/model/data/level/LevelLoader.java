package model.data.level;

import java.io.IOException;
import java.io.InputStream;

/**
 * Level loader describes a general level loader
 */
public interface LevelLoader {

	/**
	 * Loads a level from a file and returns a Level object
	 * @param input an InputStream
	 * @return returns a level object
	 * @throws IOException
	 */
	Level loadLevel(InputStream input) throws IOException;
	
}