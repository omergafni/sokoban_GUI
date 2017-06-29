package model;

import model.data.level.Level;
import model.receivers.move.Direction;

/**
 * Represents a general game model
 */
public interface Model {

	/**
	 * @return Returns the Level
	 */
	Level getLevel();

	/**
	 * Sets the level
	 * @param level Level to set
	 */
	void setLevel(Level level);

	/**
	 * Move the character in a direction
	 * @param direction The direction
	 * @throws Exception
	 */
	void move(Direction direction) throws Exception;

}
