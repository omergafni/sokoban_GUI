package controller.commands;

import model.Model;
import model.receivers.display.Displayer;

public class DisplayCommand implements Command {
	
	Displayer displayer;
	Model model;
	
	public DisplayCommand(Model model, Displayer displayer) { 
		this.model = model;
		this.displayer = displayer;	
	}

	@Override
	public void execute() {
		System.out.println("display command excecuted");
		displayer.setLevel(model.getLevel());
		displayer.display();
	}

	@Override
	public void setParams(String[] params) {}

}
