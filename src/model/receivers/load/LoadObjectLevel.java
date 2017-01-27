package model.receivers.load;

import model.data.level.Level;
import model.data.level.MyObjectLevelLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadObjectLevel implements LoadLevel {

	@Override
	public Level load(String path) throws IOException {
		
		MyObjectLevelLoader loader = new MyObjectLevelLoader();
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
		Level level = loader.loadLevel(in);
		in.close();
		return level;
		
	}
}
