package model.data.level;

import model.data.worldObjects.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MyTextLevelLoader implements LevelLoader{

	BufferedReader reader;
	
	public MyTextLevelLoader() {}
	
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
				if (line.charAt(j) == '#')
					grid.get(i).add(new Wall(new Point(i,j)));
				if (line.charAt(j) == '@')
					grid.get(i).add(new Box(new Point(i,j)));
				if (line.charAt(j) == 'o')
					grid.get(i).add(new Target(new Point(i,j)));
				if (line.charAt(j) == ' ')
					grid.get(i).add(new Floor(new Point(i,j)));
				if (line.charAt(j) == 'A')
					grid.get(i).add(new Player(new Point(i,j)));
			}
			i++;
		}
		reader.close();		
		Level newLevel = new Level(grid);
		return newLevel;
	}
}
		



