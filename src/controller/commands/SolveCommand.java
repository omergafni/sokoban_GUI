package controller.commands;

import model.MyModel;
import model.data.level.LevelConverter;
import common.LevelGrid;
import view.MyView;

import java.io.*;
import java.net.Socket;

public class SolveCommand implements Command {

    private MyModel model;
    private MyView view;

    public SolveCommand(MyModel model, MyView view) {
        this.model = model;
        this.view = view;

    }

    @Override
    public void execute() throws Exception {
        LevelGrid level = LevelConverter.convertLevel(model.getLevel());

        // Connecting to the SokobanSolver server
        Socket theServer = new Socket("127.0.0.1",5555);
        ObjectOutputStream outToServer = new ObjectOutputStream(theServer.getOutputStream());
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(theServer.getInputStream()));

        // Sending the to the server
        outToServer.writeObject(level);

        // Compressed solution arrived from the server
        String compressedSolution = fromServer.readLine();
        // If the solution is null / the server has closed the connection
        if(compressedSolution == null || compressedSolution.equals("")){
            view.solutionHandler(new String[0]);
            theServer.close();
            return;
        }
        // else..
        // Send solution to the View
        String[] uncompressedSolution = decompressedSolution(compressedSolution);
        view.solutionHandler(uncompressedSolution);

        // Close the connection
        theServer.close();
    }

    @Override
    public void setParams(String[] params) throws IOException {
        // None
    }

    private String[] decompressedSolution(String compSol){

        String[] sol = new String[compSol.length()];
        int i = 0;
        for(char s : compSol.toCharArray()){
            switch(s){
                case 'u':   sol[i] = "move up";
                            break;
                case 'd':   sol[i] = "move down";
                            break;
                case 'r':   sol[i] = "move right";
                            break;
                case 'l':   sol[i] = "move left";
                            break;
            }
            i++;
        }
        return sol;
    }

}
