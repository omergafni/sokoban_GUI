package testEx;

import model.receivers.move.Direction;

public interface PolicyImplementor {

   // public enum Direction{up,down,left,right}
    boolean isLegal(char[][] level, Direction d);


}
