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
		
		if (checkIfMovePossible())
		{
			if (checkIfNeedPush())
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
	
	private boolean checkIfMovePossible() {
		if (wallCollision(player,direction))
			return false;

		if (level.getAdjacent(player.getPosition(),direction).getWorldObjectType() == WorldObjectType.BOX)
			return checkIfNeedPush();
		
		return true;
	}
	
	private boolean checkIfNeedPush() {
		
		WorldObject potentialBox = level.getAdjacent(player.getPosition(),direction);
		WorldObject potentialFloor = level.getAdjacent(potentialBox.getPosition(),direction);
		
		if (potentialBox.getWorldObjectType() == WorldObjectType.BOX) 
		{
			if (potentialFloor.getWorldObjectType() == WorldObjectType.FLOOR)
			{
				return true;
			}
			if (potentialFloor.getWorldObjectType() == WorldObjectType.TARGET)
			{
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