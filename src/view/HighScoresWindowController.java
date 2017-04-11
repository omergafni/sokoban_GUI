package view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.database.ManagePlayer;
import model.database.Tuple;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;


public class HighScoresWindowController implements Initializable {

    @FXML private TableView table;
    @FXML private TableColumn columnName;
    @FXML private TableColumn columnTime;
    @FXML private TableColumn columnSteps;
    @FXML private TableColumn columnLevelName;
    private ObservableList data;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setRowFactory( tv -> {
            TableRow<Tuple> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                    Tuple clickedRow = row.getItem();
                    showPlayerScoreTable(clickedRow.getName());
                    System.out.println(clickedRow.getName());
                }
            });
            return row;
        });


    }

    public void buildData(String levelName) {

        ManagePlayer mp = new ManagePlayer();
        // ManagePlayer class has select() method that allowed to create query
        List<Object[]> list = (List<Object[]>)mp.select("SELECT p.name, s.time, s.steps, s.levelName " +
                "FROM Player p INNER JOIN p.scores s " + "WHERE s.levelName like "+"'"+levelName+"' "
                +"ORDER BY s.steps");
        data = FXCollections.observableArrayList();
        for(Object[] o : list){
            Tuple t = new Tuple();
            t.setName((String)o[0]);
            t.setTime((Time)o[1]);
            t.setSteps((int)o[2]);
            t.setLevelName((String)o[3]);
            data.add(t);
        }
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnSteps.setCellValueFactory(new PropertyValueFactory<>("steps"));
        columnLevelName.setCellValueFactory(new PropertyValueFactory<>("levelName"));

        table.setItems(data);
        /*
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
        columnLevelName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures c) {
                Set s = ((Player) c.getValue()).getScores();
                Iterator it = s.iterator();
                Score score = (Score) it.next();
                return new SimpleStringProperty(score.getLevelName());
            }
        });
        */
        //table.setItems(null);

    }

    public void showPlayerScoreTable(String playerName){
        Platform.runLater( () -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlayerScoresWindow.fxml"));
                Parent root = fxmlLoader.load();
                PlayerScoresWindowController c = fxmlLoader.getController();
                c.buildData(playerName);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Player High Scores");
                stage.showAndWait();
            } catch (IOException e) {e.printStackTrace();}
        });
    }


}
