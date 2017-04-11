package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;


public class HighScoresWindowController implements Initializable {

    @FXML private TableView table;
    @FXML private TableColumn columnName;
    @FXML private TableColumn columnTime;
    @FXML private TableColumn columnSteps;
    private ObservableList data;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildData();
    }

    private void buildData() {

        ManagePlayer mp = new ManagePlayer();
        List s = mp.selectFrom("Player");

        data = FXCollections.observableArrayList();
        for (Iterator it = s.iterator(); it.hasNext(); ) {
            data.add(it.next());
        }
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures c) {
                Set s = ((Player) c.getValue()).getScores();
                Iterator it = s.iterator();
                Score score = (Score) it.next();
                return new SimpleStringProperty(score.getTime().toString());
            }
        });
        columnSteps.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures c) {
                Set s = ((Player) c.getValue()).getScores();
                Iterator it = s.iterator();
                Score score = (Score) it.next();
                return new SimpleStringProperty(Integer.toString(score.getSteps()));
            }
        });
        //table.setItems(null);
        table.setItems(data);
    }
}
