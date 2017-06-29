package model.data.worldObjects;

import java.awt.*;
import java.io.Serializable;

/**
 * Represents a floor world object
 */
@SuppressWarnings("serial")
public class Floor extends WorldObject implements Serializable {

	public Floor() {}

	/**
	 * Constructor
	 * @param position position
	 */
	public Floor(Point position) { super(position); }

	@Override
	public WorldObjectType getWorldObjectType() {return WorldObjectType.FLOOR;}

	@Override
	public char getObjRep() {return ' ';}

	@Override
	public boolean onTarget() {return false;} // floor can't be on a target
 
	
}
