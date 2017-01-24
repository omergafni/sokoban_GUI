package controller.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class MyServer extends Observable implements Server {

	private int port; 
	private ClientHandler ch;
	private boolean runServer;
	
	public MyServer(int port, ClientHandler ch) {
		this.port = port;
		this.ch = ch;
		runServer = true;
	}
	
	private void runServer() throws IOException {
		System.out.println("server is online");
		ServerSocket server = new ServerSocket(port);
		while(runServer) {
			try {
				System.out.println("server is listening..");
				Socket aClient = server.accept();
				System.out.println("client accepted");
				ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
				aClient.getInputStream().close();
				aClient.getOutputStream().close();
				aClient.close();
				runServer = false; // remove this statement to keep listening when client is done
				} catch (IOException e) {e.getStackTrace();}
		}
		server.close(); System.out.println("server thread closed");
	}

	@Override
	public void start() {
		new Thread(new Runnable(){
			public void run() {
				try { runServer(); }
				catch (IOException e) {e.getStackTrace();}}
		}).start();
	}

	@Override
	public void stop() {runServer = false;}

	@Override
	public OutputStream getOutputStream() { return this.ch.getOutputStream(); }

	public ClientHandler getClientHandler() {return this.ch;}
	
}
