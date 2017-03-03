package model.data.worldObjects;


import java.awt.*;
import java.io.Serializable;

public class LeftDoor extends WorldObject implements Serializable{

    public LeftDoor() {}
    public LeftDoor(Point position) {	super(position); }

    @Override
    public WorldObjectType getWorldObjectType() {return WorldObjectType.LEFT_DOOR;}

    @Override
    public char getObjRep() {return '<';}

    @Override
    public boolean onTarget() {return false;}



}
