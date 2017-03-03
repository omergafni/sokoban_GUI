package controller.commands;

import model.MyModel;

public class RestartLevelCommand implements Command {

    private MyModel model;

    public RestartLevelCommand(MyModel model) {this.model = model;}

    @Override
    public void execute() throws Exception {
        model.restartLevel();
        System.out.println("restart command executed");
    }

    @Override
    public void setParams(String[] params) {}
}

