package controller.commands;

import model.MyModel;
import model.data.level.Level;
import model.receivers.load.LoadLevel;
import model.receivers.load.LoadObjectLevel;
import model.receivers.load.LoadTextLevel;
import model.receivers.load.LoadXMLLevel;

import java.io.IOException;
import java.util.HashMap;

/**
 * LoadCommand loads a level from a file
 */
public class LoadCommand implements Command {

	private HashMap<String,LoadLevel> levelLoaderFactory = new HashMap<>();
	private Level level = null;
    private String type = null;
	private String path = null;
	private MyModel model = null;

	/**
	 * Constructor, initiates the level loaders
	 * @param model is a Model
	 */
	public LoadCommand(MyModel model) {
		this.model = model;
		levelLoaderFactory.put("txt",new LoadTextLevel());
		levelLoaderFactory.put("obj",new LoadObjectLevel());
		levelLoaderFactory.put("xml",new LoadXMLLevel());
	}

	/**
	 * Sets the command's parameters
	 * @param params Contains the file path
	 * @throws IOException Throws when a file isn't found
	 */
	@Override
	public void setParams(String[] params) throws IOException
	{
		if(params.length == 1) {
			throw new IOException("error loading a file: please provide a path");
		}
		this.path = params[1];
		this.type = path.substring(path.lastIndexOf('.')+1);
	}

	/**
	 * Loads the file
	 * @throws IOException
	 */
	@Override
	public void execute() throws IOException {
		if (!(type.equals("txt") || type.equals("obj") || type.equals("xml")))
		{
			throw new IOException("invalid file name");
		}
		
		level = levelLoaderFactory.get(type).load(path);
		if(level == null) {
			throw new IOException("can't load level from this file\n");
		}
    	model.setLevel(level);
		model.setCurrentLevelPath(path);
		System.out.println("load completed!");
	}


}
