package controller.commands;

import controller.Controller;
import controller.SokobanController;

public class ExitCommand implements Command {

	private Controller controller = null;

	public ExitCommand(Controller controller) {this.controller = controller;}
	
	@Override
	public void execute() {
		controller.stop();
		if(((SokobanController)controller).getServer() != null) {
			((SokobanController)controller).getServer().stop();
			System.exit(0);
		}
		System.out.println("exit command executed");
	}
	
	public void setController(Controller controller) {this.controller = controller;}

	@Override
	public void setParams(String[] params) {}

}
