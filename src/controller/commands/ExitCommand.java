package controller.commands;

import controller.Controller;

public class ExitCommand implements Command {

	Controller controller = null;
		
	
	public ExitCommand(Controller ctrlr) {
		this.controller = ctrlr;
	}
	
	@Override
	public void execute() {
		//controller.stop();
		//System.out.println("exit command excecuted");
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void setParams(String[] params) {}
	
	

}
