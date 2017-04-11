package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.database.ManagePlayer;
import model.database.Tuple;

import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerScoresWindowController implements Initializable{

    @FXML
    private TableView table;
    @FXML private TableColumn columnTime;
    @FXML private TableColumn columnSteps;
    @FXML private TableColumn columnLevelName;
    private ObservableList data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buildData(String playerName) {

        ManagePlayer mp = new ManagePlayer();
        // ManagePlayer class has select() method that allowed to create query
        List<Object[]> list = (List<Object[]>)mp.select("SELECT p.name, s.time, s.steps, s.levelName " +
                "FROM Player p INNER JOIN p.scores s "+"WHERE p.name like "+"'"+playerName+"'");
        data = FXCollections.observableArrayList();
        for(Object[] o : list){
            Tuple t = new Tuple();
            t.setTime((Time)o[1]);
            t.setSteps((int)o[2]);
            t.setLevelName((String)o[3]);
            data.add(t);
        }
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnSteps.setCellValueFactory(new PropertyValueFactory<>("steps"));
        columnLevelName.setCellValueFactory(new PropertyValueFactory<>("levelName"));

        table.setItems(data);

    }



}
