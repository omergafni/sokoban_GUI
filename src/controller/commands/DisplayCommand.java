package controller.commands;

import model.Model;
import model.receivers.display.Displayer;

public class DisplayCommand implements Command {
	
	private Displayer displayer;
	private Model model;
	
	public DisplayCommand(Model model, Displayer displayer) { 
		this.model = model;
		this.displayer = displayer;	
	}

	@Override
	public void execute() throws Exception {
		displayer.setLevel(model.getLevel());
		displayer.display();
	}

	@Override
	public void setParams(String[] params) {}

}
