package model.data.worldObjects;

import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Player extends WorldObject implements Serializable {

	public Player() {}
	public Player(Point position, boolean onLeftDoor, boolean onRightDoor, boolean onTarget) {
		super(position);
		this.onTarget = onTarget;
		this.onRightDoor = onRightDoor;
		this.onLeftDoor = onLeftDoor;
	}
	
	@Override
	public WorldObjectType getWorldObjectType() {return WorldObjectType.PLAYER;}

	@Override
	public char getObjRep() {return 'A';}

	@Override
	public boolean onTarget() {return onTarget;}


}
