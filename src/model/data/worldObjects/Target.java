package model.data.worldObjects;

import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Represents a target world object
 */
public class Target extends WorldObject implements Serializable {

	public Target() {}

	/**
	 * Constructor
	 * @param position position
	 */
	public Target(Point position) {	super(position); }

	@Override
	public WorldObjectType getWorldObjectType() {return WorldObjectType.TARGET;}

	@Override
	public char getObjRep() {return '@';}

	@Override
	public boolean onTarget() {return false;} // target can't be on a target

}
