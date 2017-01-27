package model.receivers.load;

import model.data.level.Level;

import java.io.IOException;

public interface LoadLevel {

	Level load(String path) throws IOException;
	
}
