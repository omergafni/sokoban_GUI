package view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.util.function.Predicate;


public class HighScoresWindowController implements Initializable {

    @FXML private TextField filterField;
    @FXML private TableView table;
    @FXML private TableColumn columnName;
    @FXML private TableColumn columnTime;
    @FXML private TableColumn columnSteps;
    @FXML private TableColumn columnLevelName;
    @FXML private Button searchPlayerButton;
    @FXML private Button searchLevelButton;
    @FXML private TextField searchPlayerField;
    @FXML private TextField searchLevelField;
    private ObservableList data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // When user double-click on a row, this will open a new window with user records from DB
        table.setRowFactory( tv -> {
            TableRow<Tuple> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                    Tuple clickedRow = row.getItem();
                    showPlayerScoreTable(clickedRow.getName());
                }
            });
            return row;
        });

        searchPlayerButton.setOnMouseClicked(event -> {
            String playerName = searchPlayerField.getText();
            showPlayerScoreTable(playerName);
        });

        searchLevelButton.setOnMouseClicked(event -> {
            String levelName = searchLevelField.getText();
            showLevelScoreTable(levelName);

        });




    }

    public void buildData(String levelName) {

        // Creating table data using query for the current loaded level:
        ManagePlayer mp = new ManagePlayer();
        List<Object[]> list = (List<Object[]>)mp.select("SELECT p.name, s.time, s.steps, s.levelName " +
                "FROM Player p INNER JOIN p.scores s " +
                "WHERE s.levelName like "+"'"+levelName+"' " +
                "ORDER BY s.steps"); //ManagePlayer.select() is used to create a custom HQL query
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

        // Filtering table data using the top TextField:
        FilteredList<Tuple> filteredData = new FilteredList<Tuple>(data, e -> true);
        filterField.setOnKeyReleased(e -> {
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Tuple>) tuple -> {
                    if(newValue == null || newValue.isEmpty())
                        return true;
                    //String lowerCaseFilter = newValue.toLowerCase();
                    if(tuple.getName().contains(newValue))
                        return true;
                    //else if(tuple.getLevelName().toLowerCase().contains(lowerCaseFilter))
                    //    return true;
                    return false;
                });
            });
            SortedList<Tuple> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });


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
                stage.setTitle(playerName+" High Scores");
                stage.showAndWait();
            } catch (IOException e) {e.printStackTrace();}
        });
    }

    public void showLevelScoreTable(String levelName){
        Platform.runLater( () -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LevelScoresWindow.fxml"));
                Parent root = fxmlLoader.load();
                LevelScoresWindowController c = fxmlLoader.getController();
                c.buildData(levelName);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle(levelName+" High Scores");
                stage.showAndWait();
            } catch (IOException e) {e.printStackTrace();}
        });
    }


}
