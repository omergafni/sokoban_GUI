package view;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Time;

public class Score {

    private int id;
    private Time time;
    private int steps;

    public Score() {}
    public Score(Time time, int steps) {
        this.time = time;
        this.steps = steps;
    }

    public Time getTime() {return time;}
    public void setTime(Time time) {this.time = time;}

    public int getSteps() {return steps;}
    public void setSteps(int steps) {this.steps = steps;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

   // public int getPlayerId() {return playerId;}
   // public void setPlayerId(int playerId) {this.playerId = playerId;}


    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!this.getClass().equals(obj.getClass())) return false;

        Score obj2 = (Score)obj;
        if(this.id == obj2.getId()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int tmp = Integer.hashCode(id + steps);
        return tmp;
    }
}
