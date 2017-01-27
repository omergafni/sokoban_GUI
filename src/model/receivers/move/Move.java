package model.receivers.move;

import model.data.level.Level;
import model.data.worldObjects.Floor;
import model.data.worldObjects.Target;
import model.data.worldObjects.WorldObject;
import model.data.worldObjects.WorldObjectType;

import java.awt.*;

public class Move {

	protected Level level;
	protected WorldObject worldObject;
	Direction direction;
	
	public Move(Level level, WorldObject worldObject, Direction direction) {
		this.level = level;
		this.worldObject = worldObject;
		this.direction = direction;
	}
	
	
	public void move() {

		// Current WorldObject position
		Point currentPosition = worldObject.getPosition();
		int x = (int)currentPosition.getX();
		int y = (int)currentPosition.getY();

		// Current WorldObject onTarget status
		boolean onTarget = worldObject.getOnTarget();

		// Check if the WorldObject is going to cover something
		if (level.getAdjacent(currentPosition,direction).getWorldObjectType() == WorldObjectType.TARGET)
			worldObject.setOnTarget(true);
		if (level.getAdjacent(currentPosition,direction).getWorldObjectType() == WorldObjectType.FLOOR)
			worldObject.setOnTarget(false);

		// Making a move
		makeMove(direction);

		// Recovering a covered place
		if (onTarget) { level.getGrid().get(x).set(y, new Target(currentPosition)); }
		if (!onTarget) { level.getGrid().get(x).set(y, new Floor(currentPosition)); }

		
	}
	
	public void makeMove(Direction direction){

		int x = (int)worldObject.getPosition().getX();
		int y = (int)worldObject.getPosition().getY();

		switch (direction) {
			case UP: 	level.getGrid().get(x-1).set(y, worldObject);
						worldObject.setPosition(new Point(x-1,y));
						break;
						
			case DOWN:	level.getGrid().get(x+1).set(y, worldObject);
						worldObject.setPosition(new Point(x+1,y));
						break;
						
			case LEFT:  level.getGrid().get(x).set(y-1, worldObject);
						worldObject.setPosition(new Point(x,y-1));
						break;
						
			case RIGHT:	level.getGrid().get(x).set(y+1, worldObject);
						worldObject.setPosition(new Point(x,y+1));
						break;
		}
	}
	
	public Direction getDirection(){return direction;}
	
}
