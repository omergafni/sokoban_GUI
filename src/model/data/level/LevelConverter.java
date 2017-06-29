package model.data.level;

import common.LevelGrid;
import model.data.worldObjects.WorldObject;
import java.util.ArrayList;

/**
 * Converts sokoban Level class to a LevelGrid class.
 * LevelGrid is a serializable object used in the communication between the sokoban client and the server.
 */
public class LevelConverter {

    /**
     * Converts level to a LevelGrid object
     * @param level is the sokoban Level object
     * @return a new LevelGrid object
     */
   static public LevelGrid convertLevel(Level level){

       ArrayList<ArrayList<WorldObject>> levelGrid = level.getGrid();

       ArrayList<char[]> grid = new ArrayList<>();

       for (ArrayList<WorldObject> row : levelGrid) {
           grid.add(rowToCharArray(row));
       }

       LevelGrid retGrid = new LevelGrid(level.getLevelName(),grid);
       return retGrid;
   }

    /**
     * Converts a row to a char array
     * @param row an ArrayList<WorldObject> row
     * @return returns a char[]
     */
    static private char[] rowToCharArray(ArrayList<WorldObject> row) {

        char[] newRow = new char[row.size()];
        int i = 0;

        for (WorldObject tile : row) {
            newRow[i] = tile.getObjRep();
            i++;
        }

        return newRow;
    }


}
