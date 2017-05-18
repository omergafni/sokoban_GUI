package controller.commands;

import model.MyModel;
import model.database.ManagePlayer;
import model.database.Score;
import view.MyView;

import java.io.IOException;

public class SaveScoreCommand implements Command{

    private ManagePlayer mp;
    private MyView myView;
    private MyModel myModel;
    private String playerName = null;

    public SaveScoreCommand(MyView myView, MyModel myModel){
        this.myView = myView;
        this.myModel = myModel;
    }

    @Override
    public void execute() throws Exception {
        mp = new ManagePlayer();
        Score score = new Score(myView.getTimeStamp(),myModel.getLevel().getStepsCounter(),myModel.getLevel().getLevelName());
        mp.addPlayer(playerName,score);
    }

    @Override
    public void setParams(String[] params) throws IOException {
        if(params.length == 1) {
            throw new IOException("error saving user data: please provide a name");
        }
        this.playerName = params[1];
    }
}
