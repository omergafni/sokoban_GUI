package testEx;

import model.data.worldObjects.*;
import model.policy.MySokobanPolicy;
import model.receivers.move.Direction;

import java.awt.*;
import java.util.ArrayList;

public class MyPolicyImplementor implements PolicyImplementor {

    @Override
    public boolean isLegal(char[][] level, Direction d) {
        model.data.level.Level myLevel = fromMatrixToLevel(level);
        MySokobanPolicy myPolicy = new MySokobanPolicy(myLevel);

        return myPolicy.checkIfMovePossible(d);

    }

    public model.data.level.Level fromMatrixToLevel(char[][] matrix) {
        ArrayList<ArrayList<WorldObject>> grid = new ArrayList<>();
        int height = matrix.length;
        int width = matrix[0].length;
        char c;
        if(matrix == null) return null;

        for(int i = 0 ; i < height; i++) {
            grid.add(new ArrayList<WorldObject>());

            for(int j = 0; j < width; j++) {
               c = matrix[i][j];

                if (c == 'x')
                    grid.get(i).add(new Wall(new Point(i,j)));
                if (c == 'B' || c == 'b')
                    grid.get(i).add(new Box(new Point(i,j),false,false,(c=='b')));
                if (c == 'R')
                    grid.get(i).add(new Box(new Point(i,j),false,true,false));
                if (c == 'L')
                    grid.get(i).add(new Box(new Point(i,j),true,false,false));
                if (c == '@')
                    grid.get(i).add(new Target(new Point(i,j)));
                if (c == ' ')
                    grid.get(i).add(new Floor(new Point(i,j)));
                if (c == 'A')
                    grid.get(i).add(new Player(new Point(i,j),false,false,false));
                if (c == 'l')
                    grid.get(i).add(new Player(new Point(i,j),true,false,false));
                if (c == 'r')
                    grid.get(i).add(new Player(new Point(i,j),false,true,false));
                if (c == '>')
                    grid.get(i).add(new RightDoor(new Point(i,j)));
                if (c == '<')
                    grid.get(i).add(new LeftDoor(new Point(i,j)));

            }
        }

        return new model.data.level.Level(grid);
    }

}
