package model.data.worldObjects;

import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Box extends WorldObject implements Serializable {

	public Box() {}
	public Box(Point position, boolean onLeftDoor, boolean onRightDoor, boolean onTarget) {
		super(position);
		this.onTarget = onTarget;
		this.onRightDoor = onRightDoor;
		this.onLeftDoor = onLeftDoor;
	}

	@Override
	public WorldObjectType getWorldObjectType() {return WorldObjectType.BOX;}

	@Override
	public char getObjRep() {
		if(onTarget)
			return 'b';
		return 'B';
	}

	@Override
	public boolean onTarget() {return onTarget;}


	
}
