package view;

import javax.persistence.*;
import java.util.Set;


public class Player {


    private int id;
    private String name;
    private Set scores;

    public Player() {}
    public Player(String name) {
        this.name = name;
    }


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Set getScores() {return scores;}
    public void setScores(Set scores) {this.scores = scores;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}


}
