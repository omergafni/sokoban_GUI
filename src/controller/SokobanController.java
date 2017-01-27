package controller;

import controller.commands.*;
import controller.server.MyServer;
import model.Model;
import model.MyModel;
import model.receivers.display.CLIDisplayer;
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

public class SokobanController implements Controller, Observer {

	private MyModel myModel;
	private MyView myView;
	private MyServer myServer;
	private HashMap<String,Command> commands = new HashMap<>();
	private BlockingQueue<Command> myQueue = new ArrayBlockingQueue<>(128);
	private boolean isRunning = true;

	/**
	 * @param model
	 * @param view
	 *
	 * Two different constructor are provided,
	 * one for running without a server, and the second to run with a server and without GUI
	 *
	 */

	public SokobanController(Model model, View view) {
		this.myModel = (MyModel)model;
		this.myView = (MyView)view;
		this.myServer = null;
		commands.put("load", new LoadCommand(myModel));
		commands.put("save", new SaveCommand(myModel));
		commands.put("move", new MoveCommand(myModel));
		commands.put("exit", new ExitCommand(this));
		commands.put("display", new DisplayCommand(model,myView.getGUIDisplayer()));
		commands.put("restart",new RestartLevelCommand(myModel));
		start();
	}
	
	public SokobanController(Model model, MyServer server) {
		this.myModel = (MyModel)model;
		this.myView = null;
		this.myServer = server;
		myServer.start();
		commands.put("load", new LoadCommand(myModel));
		commands.put("save", new SaveCommand(myModel));
		commands.put("move", new MoveCommand(myModel));
		commands.put("exit", new ExitCommand(this));
		commands.put("display", new DisplayCommand(myModel,new CLIDisplayer(myServer)));
		commands.put("restart",new RestartLevelCommand(myModel));
		start();
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
				insertCommand(commandProcessor((String)arg));
        } catch(IOException e)
		  {
			if(myServer != null)
		  		exceptionHandler(e,myServer.getOutputStream());
			else
				myView.passException(e);
		  }
	}
	
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

	@Override
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isRunning) {
					try {
						myQueue.take().execute();
					} catch (Exception e)
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

	@Override
	public void stop() {this.isRunning = false;}

	private void insertCommand(Command command) {
		try
		{
			if (command != null)
				myQueue.put(command);
		}
		catch (InterruptedException e) { e.printStackTrace(); }
	}

	public MyServer getServer() {return myServer;}

	private void exceptionHandler(Exception e, OutputStream out) {
		PrintWriter writer = new PrintWriter(out);
		writer.println(e.getMessage());
		writer.flush();
	}

}

