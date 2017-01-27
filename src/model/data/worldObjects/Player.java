package model.data.worldObjects;

import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Player extends WorldObject implements Serializable{

	public Player() {}
	public Player(Point position) {	super(position); }
	
	@Override
	public WorldObjectType getWorldObjectType() {return WorldObjectType.PLAYER;}

	@Override
	public char getObjRep() {return 'A';}

	@Override
	public boolean onTarget() {return onTarget;}

}
