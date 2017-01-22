package controller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class MyServer extends Observable {

	private int port; 
	private ClientHandler ch;
	private boolean runServer;
	
	public MyServer(int port, ClientHandler ch) {
		this.port = port;
		this.ch = ch;
		runServer = true;
	}
	
	public void runServer() throws IOException {
		System.out.println("server is online");
		ServerSocket server = new ServerSocket(port);
	//	server.setSoTimeout(1000);
		while(runServer) {
			try {
				System.out.println("server is listening..");
				Socket aClient = server.accept();
				System.out.println("client accepted");
				ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
				aClient.getInputStream().close();
				aClient.getOutputStream().close();
				aClient.close();
				} catch (IOException e) {e.getStackTrace();}
		}
		server.close(); System.out.println("server thread closed");
	}
	
	public void start() {
		new Thread(new Runnable(){
			public void run() {
				try { runServer(); }
				catch (IOException e) {e.getStackTrace();}}
		}).start();
	}
	
	 public void stop() {
	        runServer = false;
	    }
	 
	 public ClientHandler getCH() { return this.ch; }

	
}
