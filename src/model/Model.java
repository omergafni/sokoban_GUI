package model;

import model.data.level.Level;
import model.receivers.move.Direction;

public interface Model {
	
	
	public Level getLevel();
	public void setLevel(Level level);
	public void move(Direction direction);
	
	
//	public Policy getCurrentPolicy();	
	
}
