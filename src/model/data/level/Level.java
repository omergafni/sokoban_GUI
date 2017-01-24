package model.data.level;

import model.data.worldObjects.Player;
import model.data.worldObjects.WorldObject;
import model.data.worldObjects.WorldObjectType;
import model.receivers.move.Direction;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Level implements Serializable {

	private ArrayList<ArrayList<WorldObject>> grid;
	private ArrayList<Point> solutionCoordinates = new ArrayList<>();
	private Player player = null;
	private int maxHeight;
	private int maxWidth;

	public int getStepsCounter() {
		return stepsCounter;
	}

	public void setStepsCounter(int stepsCounter) {
		this.stepsCounter = stepsCounter;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	private int stepsCounter;
	private int timer;


	public Level() {}
	public Level(ArrayList<ArrayList<WorldObject>> grid) {
		try 
		{			
			this.grid = grid;
			Point tempPlayerPosition = getPlayer().getPosition();
			this.setPlayer(new Player(tempPlayerPosition));
			findWidthHeight();
			findSolCoordinates();
			this.stepsCounter = 0;
		}
		catch (Exception s) { System.out.println(s.getMessage()); }
	}
	
	// Finding all TARGETs coordinates
	private void findSolCoordinates() {
		for(int i = 0; i < grid.size(); i++) 
			for(int j = 0; j < grid.get(i).size(); j++) {
				WorldObject w = grid.get(i).get(j);
				if (w.getWorldObjectType() == WorldObjectType.TARGET) { solutionCoordinates.add(w.getPosition()); }
			}
	}
	
	public Player getPlayer() {
		if (player != null)
		{
			return player;
		}
		for (int i = 0; i < grid.size(); i++)
		{
			for (int j = 0; j < grid.get(i).size(); j++)
			{
				if (grid.get(i).get(j).getWorldObjectType() == WorldObjectType.PLAYER)
					return ((Player)grid.get(i).get(j));
			}
		}
		return null;
	}
	
	private void setPlayer(Player player) {this.player = player;}
	
	public final ArrayList<ArrayList<WorldObject>> getGrid() {return grid;}
	
	public void setGrid(ArrayList<ArrayList<WorldObject>> grid) {this.grid = grid;}
	
	public ArrayList<Point> getSolutionCoordinates() {return solutionCoordinates;} //was List<Point>
	
	public void setSolutionCoordinates(ArrayList<Point> solutionCoordinates) {
		this.solutionCoordinates = solutionCoordinates;
	}
	
	public boolean checkIfWin() {

		for(Point p : solutionCoordinates) {
			int x = (int)p.getX();
			int y = (int)p.getY();
			if(grid.get(x).get(y).getWorldObjectType() != WorldObjectType.BOX) {
				return false;
			}
		}
		return true;
	}

	public WorldObject getWorldObject(Point position) {return grid.get((int)position.getX()).get((int)position.getY());}
	
	// Return adjacent of the given position and direction
	public WorldObject getAdjacent(Point position, Direction direction) {
				
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		switch (direction) {
		
			case UP:	return 	grid.get(x-1).get(y);	
			
			case DOWN:	return 	grid.get(x+1).get(y);	
			
			case LEFT:	return 	grid.get(x).get(y-1);
			
			case RIGHT:	return 	grid.get(x).get(y+1);
			
			default:	return null;
		} 
	}

	private void findWidthHeight() {
		maxHeight = grid.size();
		maxWidth = 0;
		for(int i = 0; i<grid.size(); i++) {
			if(grid.get(i).size() > maxWidth) maxWidth = grid.get(i).size();
		}
	}

	public int getMaxHeight() {return maxHeight;}
	public int getMaxWidth() {return maxWidth;}

	public void setMaxHeight(int maxHeight) {this.maxHeight = maxHeight;}
	public void setMaxWidth(int maxWidth) {this.maxWidth = maxWidth;}
}

