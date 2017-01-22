package model.receivers.display;

import controller.server.MyServer;
import model.data.level.Level;
import model.data.worldObjects.WorldObject;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CLIDisplayer implements Displayer{
	
	Level level;
	OutputStream out;
	MyServer server;
	
	public CLIDisplayer(MyServer server) {this.server = server; }
	
	public void setOutputStream(OutputStream out) {
		this.out = out;
	}
	
	@Override
	public void setLevel(Level level) {
		this.level = level;
	}
	
	@Override
	public void display() {
		
		out = server.getCH().getOutputStream();
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
