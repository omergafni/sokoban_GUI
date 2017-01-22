package controller.server;

import java.io.*;
import java.util.Observable;

public class CLI extends Observable implements ClientHandler {

	private String userInput;
	OutputStream out;
	boolean run = true;

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
	
		this.out = outToClient;
		PrintWriter writer = new PrintWriter(outToClient);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inFromClient));

		showMenu(writer);
		run = true;
		try {
		while(run) {
			
			userInput = reader.readLine();
			userInput = userInput.toLowerCase();
			System.out.println(userInput);
			switch(userInput) {
					
			case "exit": 	run = false;
							/* This exit string must be equals to the server string */
							writer.println("thanks for playing... bye bye :)");
							writer.flush(); 
							setChanged();
							notifyObservers(userInput);
							break;
									
			case "menu": 	showMenu(writer);
							break;
									
			default:		setChanged();
							notifyObservers(userInput);
							break;
			}
		} 
		} catch (IOException e) { e.printStackTrace();}
	}
	
	public void showMenu(PrintWriter writer) {
		
		writer.println("Welcome to Sokoban game!");
		writer.println("game commands are:"+"\n"
					  +">Load 'filepath'"+"\n"
					  +">Display"+"\n"
					  +">Move {up, down, left, right}"+"\n"
					  +">Save 'filepath'"+" {*.obj, *.xml}"+"\n"
					  +">Menu"+"\n"
					  +">Exit"+"\n");
		writer.flush();
	}

	@Override
	public OutputStream getOutputStream() {
		return out;
	}

}
