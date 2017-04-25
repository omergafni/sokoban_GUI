package model.receivers.load;

import model.data.level.Level;
import model.data.level.MyTextLevelLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class LoadTextLevel implements LoadLevel {

	@Override
	public Level load(String path) throws IOException {
		
		MyTextLevelLoader loader = new MyTextLevelLoader();
		Level level = loader.loadLevel(new FileInputStream(path));

		// Parsing the file name and set the level name
		String fileName = Paths.get(path).getFileName().toString();
		if(fileName.lastIndexOf(".") > 0){
			fileName = fileName.substring(0,fileName.lastIndexOf("."));
		}
		level.setLevelName(fileName);

		return level;
	
	}		
}
