package model.data.worldObjects;

import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Box extends WorldObject implements Serializable {

	public Box() {}
	public Box(Point position) { super(position); }

	@Override
	public WorldObjectType getWorldObjectType() {
		return WorldObjectType.BOX;
	}

	@Override
	public char getObjRep() {return '@';}

	@Override
	public boolean onTarget() {return onTarget;}
	
}
