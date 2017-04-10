package controller.commands;

import model.Model;
import view.View;

public class DisplayCommand implements Command {

	private View view;
	//private Displayer displayer;
	private Model model;
	
	public DisplayCommand(Model model, View view/*, Displayer displayer*/) {
		this.model = model;
		this.view = view;
		//this.displayer = displayer;
	}

	@Override
	public void execute() throws Exception {
		//displayer.setLevel(model.getLevel());
		//displayer.display();
		view.getDisplayer().setLevel(model.getLevel());
		view.displayLevel();
	}

	@Override
	public void setParams(String[] params) {}

}
