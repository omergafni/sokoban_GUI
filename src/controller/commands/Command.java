package controller.commands;

import java.io.IOException;

public interface Command {

	void execute() throws Exception;
	void setParams(String[] params) throws IOException;
	
	
}
