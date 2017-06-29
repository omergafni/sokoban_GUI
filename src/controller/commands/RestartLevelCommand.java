package controller.commands;

import model.MyModel;

/**
 * RestartLevelCommand restarts the level
 */
public class RestartLevelCommand implements Command {

    private MyModel model;

    /**
     * Constructor
     * @param model is a Model
     */
    public RestartLevelCommand(MyModel model) {this.model = model;}

    /**
     * Calls model's restart method
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        model.restartLevel();
        System.out.println("restart command executed");
    }

    /**
     * Unused
     * @param params String array of values
     */
    @Override
    public void setParams(String[] params) {}
}

