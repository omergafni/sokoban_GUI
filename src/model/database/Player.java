package model.database;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="Players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "player")
    private Set<Score> scores; //init?

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
