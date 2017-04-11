package model;

import model.data.level.Level;
import model.policy.MySokobanPolicy;
import model.policy.Policy;
import model.receivers.move.Direction;
import model.receivers.move.Move;

import java.util.Observable;

public class MyModel extends Observable implements Model {

	private Level level = null;
	private Move moveUp = null;
	private Move moveDown = null;
	private Move moveRight = null;
	private Move moveLeft = null;
	private Policy policy = null;
	private String currentLevelPath= null;

	@Override
	public void move(Direction direction) throws Exception {
		if (level == null){
			//System.out.println("there is no level loaded");
			return;
		}
		
		switch(direction) {
		
		case up:	policy.execute(moveUp);
					break;
			 
		case down:  policy.execute(moveDown);
					break;
			
		case right: policy.execute(moveRight);
					break;
			
		case left:  policy.execute(moveLeft);
					break;
			
		default:	System.out.println("invalid direction");
					break;
		}		
		setChanged();
		notifyObservers("display");
	
	}

	@Override
	public Level getLevel() {return level;}

	@Override
	public void setLevel(Level level) {
		this.level = level;
		policy = new MySokobanPolicy(level);
	
		moveUp = new Move(level,level.getCharacter(),Direction.up);
		moveDown = new Move(level,level.getCharacter(),Direction.down);
		moveLeft = new Move(level,level.getCharacter(),Direction.left);
		moveRight = new Move(level,level.getCharacter(),Direction.right);
		
		setChanged();
		notifyObservers("display");
		
	}

	public void setCurrentLevelPath(String path) {this.currentLevelPath=path;}

	public void restartLevel() {
		String loadCommand = "load "+currentLevelPath;
		setChanged();
		notifyObservers(loadCommand);
	}
}
