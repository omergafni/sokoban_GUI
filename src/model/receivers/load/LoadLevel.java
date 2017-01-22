package model.receivers.load;

import model.data.level.Level;

import java.io.IOException;

public interface LoadLevel {

	public Level load(String path) throws IOException;
	
}
