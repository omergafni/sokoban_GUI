package model.data.level;

import model.data.worldObjects.*;
import model.data.worldObjects.Character;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * MyTextLevelLoader is a LevelLoader that loads a Sokoban level from a text file
 */
public class MyTextLevelLoader implements LevelLoader{

	BufferedReader reader;
	
	public MyTextLevelLoader() {}

	/**
	 * Loads a level from a text file
	 * @param input an InputStream
	 * @return a Level object
	 * @throws IOException
	 */
	@Override
	public Level loadLevel(InputStream input) throws IOException  
	{	
		if (!(input instanceof FileInputStream))
			throw new IOException("input is not FileInputStream instance");
	
		this.reader = new BufferedReader(new InputStreamReader(input));
		ArrayList<ArrayList<WorldObject>> grid = new ArrayList<ArrayList<WorldObject>>();
		String line;
		int i = 0;
		while ((line = reader.readLine()) != null) 
		{
			grid.add(new ArrayList<WorldObject>());

			for(int j = 0; j < line.length(); j++)
			{

				if (line.charAt(j) == 'x')
					grid.get(i).add(new Wall(new Point(i,j)));
				if (line.charAt(j) == 'B' || line.charAt(j) == 'b')
					grid.get(i).add(new Box(new Point(i,j),false,false,(line.charAt(j)=='b')));
				if (line.charAt(j) == 'R')
					grid.get(i).add(new Box(new Point(i,j),false,true,false));
				if (line.charAt(j) == 'L')
					grid.get(i).add(new Box(new Point(i,j),true,false,false));
				if (line.charAt(j) == '@')
					grid.get(i).add(new Target(new Point(i,j)));
				if (line.charAt(j) == ' ')
					grid.get(i).add(new Floor(new Point(i,j)));
				if (line.charAt(j) == 'A')
					grid.get(i).add(new Character(new Point(i,j),false,false,false));
				if (line.charAt(j) == 'l')
					grid.get(i).add(new Character(new Point(i,j),true,false,false));
				if (line.charAt(j) == 'r')
					grid.get(i).add(new Character(new Point(i,j),false,true,false));
				if (line.charAt(j) == '>')
					grid.get(i).add(new RightDoor(new Point(i,j)));
				if (line.charAt(j) == '<')
					grid.get(i).add(new LeftDoor(new Point(i,j)));
				/*
				if (line.charAt(j) == '#')
					grid.get(i).add(new Wall(new Point(i,j)));
				if (line.charAt(j) == '@')
					grid.get(i).add(new Box(new Point(i,j)));
				if (line.charAt(j) == 'o')
					grid.get(i).add(new Target(new Point(i,j)));
				if (line.charAt(j) == ' ')
					grid.get(i).add(new Floor(new Point(i,j)));
				if (line.charAt(j) == 'A')
					grid.get(i).add(new Character(new Point(i,j)));
				*/
			}
			i++;
		}
		reader.close();		
		Level newLevel = new Level(grid);
		return newLevel;
	}
}
		



