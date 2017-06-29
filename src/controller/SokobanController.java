package controller;

import controller.commands.*;
import controller.server.MyServer;
import model.Model;
import model.MyModel;
import view.MyView;
import view.View;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * SokobanController is a Controller that runs sokoban commands and connects between the sokoban view and model
 */
public class SokobanController implements Controller, Observer {

	private MyModel myModel;
	private MyView myView;
	private MyServer myServer;
	private HashMap<String,Command> commands = new HashMap<>();
	private BlockingQueue<Command> myQueue = new ArrayBlockingQueue<>(128);
	private boolean isRunning = true;

	/**
	 * Constructor for GUI mode, initiates the sokoban commands map
	 * @param model a Model
	 * @param view a View
	 */

	public SokobanController(Model model, View view) {
		this.myModel = (MyModel)model;
		this.myView = (MyView)view;
		this.myServer = null;
		commands.put("load", new LoadCommand(myModel));
		commands.put("save", new SaveCommand(myModel));
		commands.put("move", new MoveCommand(myModel));
		commands.put("exit", new ExitCommand(this));
		commands.put("display", new DisplayCommand(model,myView/*myView.getGUIDisplayer()*/));
		commands.put("restart",new RestartLevelCommand(myModel));
		commands.put("savescore",new SaveScoreCommand(myView,myModel));
		commands.put("solve",new SolveCommand(myModel,myView));
		start();
	}

	/**
	 * Constructor for server mode, initiates the sokoban commands map
	 * @param model a Model
	 * @param server a Server
	 */
	public SokobanController(Model model, MyServer server) {
		this.myModel = (MyModel)model;
		this.myView = null;
		this.myServer = server;
		myServer.start();
		commands.put("load", new LoadCommand(myModel));
		commands.put("save", new SaveCommand(myModel));
		commands.put("move", new MoveCommand(myModel));
		commands.put("exit", new ExitCommand(this));
		commands.put("display", new DisplayCommand(myModel,myView/*new CLIDisplayer(myServer)*/));
		commands.put("restart",new RestartLevelCommand(myModel));
		commands.put("solve",new SolveCommand(myModel,myView));
		start();
	}

	/**
	 * Catches the command that the Observable threw
	 * @param o The Observable that threw the command
	 * @param arg Is a string that contains the command and the parameters
	 */
	@Override
	public void update(Observable o, Object arg) {
		try {
			insertCommand(commandProcessor((String)arg));
        }
        catch(IOException e)
		{
			if(myServer != null)
		  		exceptionHandler(e,myServer.getOutputStream());
			else
				myView.passException(e);
		}
	}

	/**
	 * Processes the command - splits it to the command name and the parameters
	 * @param input command string
	 * @return returns a Command object
	 * @throws IOException
	 */
	private Command commandProcessor(String input) throws IOException{
		String toProcess = input;
		toProcess = toProcess.toLowerCase();
		String[] params = toProcess.split(" ");
		Command c = commands.get(params[0]);
		if(c == null)
			throw new IOException("invalid command");
		c.setParams(params);
		return c;
	}

	/**
	 * Runs the blocking queues that executes the commands
	 */
	@Override
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isRunning) {
					try {
						myQueue.take().execute();
					}
					catch (Exception e)
					{
						if(myServer != null)
							exceptionHandler(e,myServer.getOutputStream());
						else
							myView.passException(e);
					}
				}
				System.out.println("controller thread is close");
			}
		}
		).start();
	}

	/**
	 * Stops the blocking queue
	 */
	@Override
	public void stop() {this.isRunning = false;}

	/**
	 * Inserts a command into the blocking queue
	 * @param command
	 */
	private void insertCommand(Command command) {
		try
		{
			if (command != null)
				myQueue.put(command);
		}
		catch (InterruptedException e) { e.printStackTrace(); }
	}

	public MyServer getServer() {return myServer;}

	/**
	 * Sends an exception to an output stream
	 * @param e The exception to send
	 * @param out The output stream to send the exception to
	 */
	private void exceptionHandler(Exception e, OutputStream out) {
		PrintWriter writer = new PrintWriter(out);
		writer.println(e.getMessage());
		writer.flush();
	}
}

