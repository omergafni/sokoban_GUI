package controller.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CLIclient {

	private void readInputsAndSend(BufferedReader in, PrintWriter out, String exitStr) { 
		try {
			String line;
			while(!(line = in.readLine()).equals(exitStr)) {
				out.println(line);
				out.flush();
			}
		} catch (IOException e) {e.getStackTrace();}
					
	}
		
	private Thread aSyncReadInputsAndSend(BufferedReader in, PrintWriter out, String exitStr) {
		Thread t = new Thread(new Runnable(){
			public void run() { readInputsAndSend(in, out, exitStr); }
		});
		t.start();
		return t;
	}

	public void start(String ip, int port){
        try{
            Socket theServer=new Socket(ip, port);
            System.out.println("connected to server");

            BufferedReader userInput=new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput=new BufferedReader(new InputStreamReader(theServer.getInputStream()));

            PrintWriter outToScreen=new PrintWriter(System.out);
            PrintWriter outToServer=new PrintWriter(theServer.getOutputStream());

            Thread t1= aSyncReadInputsAndSend(userInput,outToServer,"exit"); // Thread to client
            Thread t2= aSyncReadInputsAndSend(serverInput,outToScreen,"bye"); // Thread to the server

            t1.join(); t2.join(); // Wait for threads to end

            userInput.close();
            serverInput.close();
            outToServer.close();
            outToScreen.close();
            theServer.close();

        } catch(IOException | InterruptedException e) {e.getStackTrace();}
	
	}
	
}
