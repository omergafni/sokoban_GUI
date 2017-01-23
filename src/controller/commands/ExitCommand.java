package controller.commands;

import controller.Controller;

public class ExitCommand implements Command {

	private Controller controller = null;

	public ExitCommand(Controller ctrlr) {
		this.controller = ctrlr;
	}
	
	@Override
	public void execute() {
		//controller.stop();
		System.out.printf("exit command executed");
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void setParams(String[] params) {}

}
