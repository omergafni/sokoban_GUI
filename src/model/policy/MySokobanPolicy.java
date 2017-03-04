package model.policy;

import model.data.level.Level;
import model.data.worldObjects.Box;
import model.data.worldObjects.Player;
import model.data.worldObjects.WorldObject;
import model.data.worldObjects.WorldObjectType;
import model.receivers.move.Direction;
import model.receivers.move.Move;

public class MySokobanPolicy implements Policy {

	private Level level;
	private Player player;
	private Direction direction;

	public MySokobanPolicy(Level level) {this.level = level; this.player = level.getPlayer();}
	
	@Override
	public void execute(Move moveCommand) throws Exception {
		
		this.direction = moveCommand.getDirection();
		
		if (checkIfMovePossible(direction))
		{
			if (checkIfNeedPush(direction))
			{
				push((Box)level.getAdjacent(player.getPosition(), direction),direction);
				moveCommand.move();
			}
			else 
			{
				moveCommand.move();
			}
		}
		level.setStepsCounter(level.getStepsCounter()+1);
	}
	
	private void push(Box box, Direction direction) {
				
		if (!wallCollision(box,direction))
		{
			Move pushCommand = new Move(level,box,direction);
			pushCommand.move();
		}
		
	}

	public boolean checkIfMovePossible(Direction direction) {
		if (wallCollision(player,direction))
			return false;

		if (level.getAdjacent(player.getPosition(),direction).getWorldObjectType() == WorldObjectType.BOX)
			return checkIfNeedPush(direction);

		if (level.getAdjacent(player.getPosition(),direction).getWorldObjectType() == WorldObjectType.RIGHT_DOOR)
			if (direction == Direction.right) return true;

		if (level.getAdjacent(player.getPosition(),direction).getWorldObjectType() == WorldObjectType.LEFT_DOOR)
			if (direction == Direction.left) return true;


		return true;
	}

	/*
	public boolean checkIfMovePossible() {
		if (wallCollision(player,direction))
			return false;

		if (level.getAdjacent(player.getPosition(),direction).getWorldObjectType() == WorldObjectType.BOX)
			return checkIfNeedPush();
		
		return true;
	}
	*/
	private boolean checkIfNeedPush(Direction direction) {
		
		WorldObject potentialBox = level.getAdjacent(player.getPosition(),direction);
		WorldObject potentialFloor = level.getAdjacent(potentialBox.getPosition(),direction);
		WorldObject potentialRightDoor = level.getAdjacent(potentialBox.getPosition(),direction);
		WorldObject potentialLeftDoor = level.getAdjacent(potentialBox.getPosition(),direction);


		if (potentialBox.getWorldObjectType() == WorldObjectType.BOX) 
		{
			if (potentialLeftDoor.getWorldObjectType() == WorldObjectType.LEFT_DOOR) {
				if (direction == Direction.left) return true;
			}
			if (potentialRightDoor.getWorldObjectType() == WorldObjectType.RIGHT_DOOR) {
				if (direction == Direction.right) return true;
			}
			if (potentialFloor.getWorldObjectType() == WorldObjectType.FLOOR || potentialFloor.getWorldObjectType() == WorldObjectType.TARGET)
			{
				if (potentialBox.isOnLeftDoor() && direction != Direction.left)
					return false;
				if (potentialBox.isOnRightDoor() && direction != Direction.right)
					return false;
				return true;
			}

		}
		return false;
	}
	
	private boolean wallCollision(WorldObject worldObject, Direction direction) {
		
		if(level.getAdjacent(worldObject.getPosition(), direction).getWorldObjectType() == WorldObjectType.WALL)
		{
			return true;
		}
				
		return false;
	}

}