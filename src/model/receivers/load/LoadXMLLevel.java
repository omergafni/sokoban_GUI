package model.receivers.load;

import model.data.level.Level;
import model.data.level.MyXMLLevelLoader;

import java.io.FileInputStream;
import java.io.IOException;

public class LoadXMLLevel implements LoadLevel{

	@Override
	public Level load(String path) throws IOException {
		
		MyXMLLevelLoader loader = new MyXMLLevelLoader();
		FileInputStream in = new FileInputStream(path);
		Level level = loader.loadLevel(in);
		in.close();
		return level;
	
	}
}
