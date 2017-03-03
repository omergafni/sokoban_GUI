package model.data.worldObjects;


import java.awt.*;
import java.io.Serializable;

public class RightDoor extends WorldObject implements Serializable{

    public RightDoor() {}
    public RightDoor(Point position) {	super(position); }

    @Override
    public WorldObjectType getWorldObjectType() {return WorldObjectType.RIGHT_DOOR;}

    @Override
    public char getObjRep() {return '>';}

    @Override
    public boolean onTarget() {return false;}


}
