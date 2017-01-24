package model.receivers.display;

import controller.server.MyServer;
import model.data.level.Level;
import model.data.worldObjects.WorldObject;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CLIDisplayer implements Displayer{
	
	private Level level;
	private OutputStream out;
	private MyServer server;
	
	public CLIDisplayer(MyServer server) {this.server = server; }

	@Override
	public void setLevel(Level level) {this.level = level;}
	
	@Override
	public void display() {
		
		out = server.getOutputStream();
		PrintWriter writer = new PrintWriter(out);
		for (ArrayList<WorldObject> a : level.getGrid())
		{
			for (WorldObject w : a)
				writer.print(w.getObjRep());
			writer.println();
			writer.flush();
		}
	}

}
