package model.data.worldObjects;

import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Wall extends WorldObject implements Serializable{

	public Wall() {}
	public Wall(Point position) { super(position); }

	@Override
	public WorldObjectType getWorldObjectType() {return WorldObjectType.WALL;}

	@Override
	public char getObjRep() {return '#';}

	@Override
	public boolean onTarget() {return false;} // wall can't be on target


}
