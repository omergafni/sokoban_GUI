package controller;

import controller.commands.Command;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Controller {

	private BlockingQueue<Command> myQueue = new ArrayBlockingQueue<Command>(128);
	boolean isRunning = true;
	
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(isRunning) {
					try {
						myQueue.take().execute();
					} 
					catch (Exception e) {e.printStackTrace();} 
				}
				System.out.println("controller thread is close");
			}		
		}
	).start();
		
	}
	public void stop() { this.isRunning = false; }
	
	public void insertCommand(Command command) {
			try 
			{
				if (command != null)
					myQueue.put(command);
			} 
			catch (InterruptedException e) { e.printStackTrace(); }
		}
		
}
