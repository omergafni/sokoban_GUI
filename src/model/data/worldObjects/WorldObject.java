package model.data.worldObjects;

import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Represents a general world object that is a part of the Sokoban level map
 */
public abstract class WorldObject implements Serializable {

	protected Point position;
	protected boolean onTarget = false;
	protected boolean onRightDoor = false;
	protected boolean onLeftDoor = false;
	
	public WorldObject() {}
	public WorldObject(Point position) { this.position = position; }
		
	public abstract boolean onTarget();
	public abstract char getObjRep();
	public abstract WorldObjectType getWorldObjectType();

	public Point getPosition() {return position;}
	public void setPosition(Point position) {this.position = position;}

	public boolean getOnTarget() {return onTarget;}
	public void setOnTarget(boolean onTarget) {this.onTarget = onTarget;}

	public boolean isOnLeftDoor() {
		return onLeftDoor;
	}

	public boolean isOnRightDoor() {
		return onRightDoor;
	}

	
}
