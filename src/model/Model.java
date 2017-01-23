package model;

import model.data.level.Level;
import model.receivers.move.Direction;

public interface Model {

	Level getLevel();
	void setLevel(Level level);
	void move(Direction direction) throws Exception;

}
