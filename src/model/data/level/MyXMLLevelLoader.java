package model.data.level;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * MyXMLLevelLoader is a LevelLoader that loads a Sokoban level from an XML file
 */
public class MyXMLLevelLoader implements LevelLoader {

	/**
	 * Loads a level from an input stream
	 * @param input an InputStream
	 * @return returns a Level object
	 * @throws IOException
	 */
	@Override
	public Level loadLevel(InputStream input) throws IOException {

		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream((input)));
		Level newLevel = (Level)decoder.readObject();
		decoder.close();
		return newLevel;
	}

}
