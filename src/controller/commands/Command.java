package controller.commands;

public interface Command {

	void execute() throws Exception;
	void setParams(String[] params);
	
	
}
