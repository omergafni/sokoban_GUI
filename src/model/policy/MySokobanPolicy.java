package model.policy;

import model.data.level.Level;
import model.data.worldObjects.Box;
import model.data.worldObjects.Character;
import model.data.worldObjects.WorldObject;
import model.data.worldObjects.WorldObjectType;
import model.receivers.move.Direction;
import model.receivers.move.Move;

/**
 * MySokobanPolicy defines rules for a sokoban game
 */
public class MySokobanPolicy implements Policy {

	private Level level;
	private Character character;
	private Direction direction;

	/**
	 * Constructor
	 * @param level Level
	 */
	public MySokobanPolicy(Level level) {this.level = level; this.character = level.getCharacter();}

	/**
	 * Checks if a move commands is legal according to the policy
	 * @param moveCommand Move command
	 * @throws Exception
	 */
	@Override
	public void execute(Move moveCommand) throws Exception {
		
		this.direction = moveCommand.getDirection();
		
		if (checkIfMovePossible(direction))
		{
			if (checkIfNeedPush(direction))
			{
				push((Box)level.getAdjacent(character.getPosition(), direction),direction);
				moveCommand.move();
                level.setStepsCounter(level.getStepsCounter()+1);
            }
			else 
			{
				moveCommand.move();
                level.setStepsCounter(level.getStepsCounter()+1);
            }
		}
		//System.out.println(level.getStepsCounter());
	}

	/**
	 * Pushes a box in a direction
	 * @param box A box object
	 * @param direction A direction to push in
	 */
	private void push(Box box, Direction direction) {
				
		if (!wallCollision(box,direction))
		{
			Move pushCommand = new Move(level,box,direction);
			pushCommand.move();
		}
		
	}

	/**
	 * Checks if a move is possible in a direction
	 * @param direction
	 * @return
	 */
	public boolean checkIfMovePossible(Direction direction) {
		if (wallCollision(character,direction))
			return false;

		if (level.getAdjacent(character.getPosition(),direction).getWorldObjectType() == WorldObjectType.BOX)
			return checkIfNeedPush(direction);

		if (level.getAdjacent(character.getPosition(),direction).getWorldObjectType() == WorldObjectType.RIGHT_DOOR)
			if (direction == Direction.right) return true;

		if (level.getAdjacent(character.getPosition(),direction).getWorldObjectType() == WorldObjectType.LEFT_DOOR)
			if (direction == Direction.left) return true;


		return true;
	}

	/*
	public boolean checkIfMovePossible() {
		if (wallCollision(character,direction))
			return false;

		if (level.getAdjacent(character.getPosition(),direction).getWorldObjectType() == WorldObjectType.BOX)
			return checkIfNeedPush();
		
		return true;
	}
	*/

	/**
	 * Checks if a box push is needed after a player move in a direction
	 * @param direction A direction
	 * @return
	 */
	private boolean checkIfNeedPush(Direction direction) {
		
		WorldObject potentialBox = level.getAdjacent(character.getPosition(),direction);
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

	/**
	 * Checks if there is a wall in the way
	 * @param worldObject a World object
	 * @param direction A direction
	 * @return
	 */
	private boolean wallCollision(WorldObject worldObject, Direction direction) {
		
		if(level.getAdjacent(worldObject.getPosition(), direction).getWorldObjectType() == WorldObjectType.WALL)
		{
			return true;
		}
				
		return false;
	}

}