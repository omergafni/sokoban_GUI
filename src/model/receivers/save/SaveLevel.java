package model.receivers.save;

import model.data.level.Level;

import java.io.IOException;

public interface SaveLevel {

	void save(Level level, String path) throws IOException;
	
}
