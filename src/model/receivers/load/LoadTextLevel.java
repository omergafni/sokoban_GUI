package model.receivers.load;

import model.data.level.Level;
import model.data.level.MyTextLevelLoader;

import java.io.FileInputStream;
import java.io.IOException;

public class LoadTextLevel implements LoadLevel {

	@Override
	public Level load(String path) throws IOException {
		
		MyTextLevelLoader loader = new MyTextLevelLoader();
		Level level = loader.loadLevel(new FileInputStream(path));
		return level;
	
	}		
}
