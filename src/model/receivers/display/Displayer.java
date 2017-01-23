package model.receivers.display;

import model.data.level.Level;

public interface Displayer {

	void display() throws Exception;
	void setLevel(Level level);
	
}
