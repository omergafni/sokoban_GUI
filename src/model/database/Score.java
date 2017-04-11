package model.database;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "Scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "time")
    private Time time;
    @Column(name = "steps")
    private int steps;
    @Column(name = "level_name")
    private String levelName;
    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Score() {}
    public Score(Time time, int steps, String levelName) {
        this.time = time;
        this.steps = steps;
        this.levelName = levelName;
    }

    public Time getTime() {return time;}
    public void setTime(Time time) {this.time = time;}

    public int getSteps() {return steps;}
    public void setSteps(int steps) {this.steps = steps;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getLevelName() {return levelName;}
    public void setLevelName(String levelName) {this.levelName = levelName;}

    //public int getPlayer_id() {return player_id;}
    //public void setPlayer_id(int player_id) {this.player_id = player_id;}



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
