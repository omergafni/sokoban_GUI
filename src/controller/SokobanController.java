package controller;

import controller.commands.*;
import controller.server.MyServer;
import model.Model;
import model.receivers.display.CLIDisplayer;
import view.MyView;
import view.View;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class SokobanController implements Observer {

	private Model myModel;
	private View myView;
	private Controller myController;
	private MyServer myServer;
	private HashMap<String,Command> commands = new HashMap<String,Command>();
	//charGrid is using to pass level data from myModel to myView
	//private ArrayList<ArrayList<Character>> charGrid = new ArrayList<ArrayList<Character>>();

	
	public SokobanController(Model model, View view) {
		this.myModel = model;
		this.myView = view;
		this.myController = new Controller();
		this.myServer = null;
		commands.put("load", new LoadCommand(myModel));
		commands.put("save", new SaveCommand(myModel));
		commands.put("move", new MoveCommand(myModel));
		commands.put("exit", new ExitCommand(myController));
		commands.put("display", new DisplayCommand(model,((MyView)myView).getGUIDisplayer()));
	}
	
	public SokobanController(Model model, MyServer server) {
		this.myModel = model;
		this.myView = null;
		this.myController = new Controller();
		this.myServer = server;
		myServer.start();
		myController.start();
		commands.put("load", new LoadCommand(myModel));
		commands.put("save", new SaveCommand(myModel));
		commands.put("move", new MoveCommand(myModel));
		commands.put("exit", new ExitCommand(myController));
		commands.put("display", new DisplayCommand(myModel,new CLIDisplayer(myServer)));
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		try {
			    myController.insertCommand(commandProcessor((String)arg));

        } catch(IOException e) { exceptionHandler(e,myServer.getCH().getOutputStream()); }
	}
	
	public Command commandProcessor(String input) throws IOException{
		String toProcess = input;
		toProcess = toProcess.toLowerCase();
		String[] params = toProcess.split(" ");
		Command c = commands.get(params[0]);
		if(c == null)
			throw new IOException("invalid command");
		c.setParams(params);
		return c;
	}
	
	public MyServer getServer() {return myServer;}

	public void setServer(MyServer server) {this.myServer = server;}

	public Controller getController() {return this.myController;}
	
	public void exceptionHandler(IOException e, OutputStream out) {
		PrintWriter writer = new PrintWriter(out);
		writer.println(e.getMessage());
		writer.flush();
	}
	
	
	
	
}

