package controller.commands;

import java.io.IOException;

/**
 * Command interface
 */
public interface Command {
	/**
	 * Command's execution method
	 * @throws Exception
	 */
	void execute() throws Exception;

	/**
	 * Setting the command parameters
	 * @param params String array of values
	 * @throws IOException
	 */
	void setParams(String[] params) throws IOException;
	
	
}
