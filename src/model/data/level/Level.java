package model.data.level;

import model.data.worldObjects.Character;
import model.data.worldObjects.WorldObject;
import model.data.worldObjects.WorldObjectType;
import model.receivers.move.Direction;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Level implements Serializable {

	private String levelName;
	private ArrayList<ArrayList<WorldObject>> grid;
	private ArrayList<Point> solutionCoordinates = new ArrayList<>();
	private Character character = null;
	private int maxHeight;
	private int maxWidth;
	private int stepsCounter;
	private char[][] levelAsChar = null;

	public Level() {}
	public Level(ArrayList<ArrayList<WorldObject>> grid) {
		try 
		{			
			this.grid = grid;
			Point tempPlayerPosition = getCharacter().getPosition();
			this.setCharacter(new Character(tempPlayerPosition,false,false,false));
			findWidthHeight();
			findSolCoordinates();
			this.stepsCounter = 0;
		}
		catch (Exception s) { System.out.println(s.getMessage()); }
	}
	
	// Finding all TARGET's coordinates
	private void findSolCoordinates() {
		for(int i = 0; i < grid.size(); i++) 
			for(int j = 0; j < grid.get(i).size(); j++) {
				WorldObject w = grid.get(i).get(j);
				if (w.getWorldObjectType() == WorldObjectType.TARGET) { solutionCoordinates.add(w.getPosition()); }
			}
	}
	
	public Character getCharacter() {
		if (character != null)
		{
			return character;
		}
		for (int i = 0; i < grid.size(); i++)
		{
			for (int j = 0; j < grid.get(i).size(); j++)
			{
				if (grid.get(i).get(j).getWorldObjectType() == WorldObjectType.PLAYER)
					return ((Character)grid.get(i).get(j));
			}
		}
		return null;
	}
	private void setCharacter(Character character) {this.character = character;}
	
	public final ArrayList<ArrayList<WorldObject>> getGrid() {return grid;}
	public void setGrid(ArrayList<ArrayList<WorldObject>> grid) {this.grid = grid;}
	
	public ArrayList<Point> getSolutionCoordinates() {return solutionCoordinates;}
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

	public char[][] getLevelAsCharMatrix(){
		if(levelAsChar != null) return levelAsChar;
		char[][] matrix = new char[maxHeight][maxWidth];
		for(int i = 0; i < grid.size(); i++) {
			for(int j = 0; j < grid.get(i).size(); j++) {
				matrix[i][j] = grid.get(i).get(j).getObjRep();
			}
		}
		return matrix;
	}

	// Return adjacent of the given position and direction
	public WorldObject getAdjacent(Point position, Direction direction) {
				
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		switch (direction) {
		
			case up:	return 	grid.get(x-1).get(y);
			
			case down:	return 	grid.get(x+1).get(y);
			
			case left:	return 	grid.get(x).get(y-1);
			
			case right:	return 	grid.get(x).get(y+1);
			
			default:	return null;
		} 
	}

	// Setting the maxWidth and maxHeight variables to the maximum grid's height and width
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

	public int getStepsCounter() {return stepsCounter;}
	public void setStepsCounter(int stepsCounter) {this.stepsCounter = stepsCounter;}

	//public int getTimer() {return timer;}
	//public void setTimer(int timer) {this.timer = timer;}


	public String getLevelName() {return levelName;}
	public void setLevelName(String levelName) {this.levelName = levelName;}

}

