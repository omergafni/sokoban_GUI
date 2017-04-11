package model.database;

import java.sql.Time;

public class Tuple {

    private String name;
    private Time time;
    private int steps;
    private String levelName;

    public Tuple() {}
    public Tuple(String name, Time time, int steps) {this.name = name; this.time = time; this.steps = steps;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Time getTime() {return time;}
    public void setTime(Time time) {this.time = time;}

    public int getSteps() {return steps;}
    public void setSteps(int steps) {this.steps = steps;}

    public String getLevelName() {return levelName;}
    public void setLevelName(String levelName) {this.levelName = levelName;}

}
