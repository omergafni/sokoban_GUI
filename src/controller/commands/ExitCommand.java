package controller.commands;

import controller.Controller;
import controller.SokobanController;

/**
 * ExitCommand closes the application and stops the server
 */
public class ExitCommand implements Command {

	private Controller controller = null;

	/**
	 * Constructor
	 * @param controller a Controller
	 */
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

	/**
	 * Unused
	 * @param params String array of values
	 */
	@Override
	public void setParams(String[] params) {}

}
