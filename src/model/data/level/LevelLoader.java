package model.data.level;

import java.io.IOException;
import java.io.InputStream;

public interface LevelLoader {

	public Level loadLevel(InputStream input) throws IOException;
	
}

/*
 *    A) The separation is created in interface level. each LevelLoader must implements loadLevel method to match
 *		 the correct type of file that we want to load data from.
 *    B) Class LevelLoader contains a method who create a Level type object from a type of InputStream.
 *	  	 All level's data is part of class Level and contains all data to represent a level instance.
 *    	 This way we don't have to change class Level if we like to change the way type Level object is created.
 *		 The only thing we have to do is to implements LevelLoader interface and create our own LevelLoader according to desire file type.
 *	  C) LSP is saving in this way of implements because we created an abstract interface called LevelLoader that define a method
 *       each LevelLoader MUST implement. Later, we can use any type of LevelLoader to choose the way we want to load a new level
 *		 according to its type of file.
 *	  D) InputStream is a superclass of many type of input stream objects. Using InputStream interface in our LevelLoader interface
 *		 will let us implement a various types of loaders without changing our code (open-close principle).
 */