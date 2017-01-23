package view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class MyView extends Observable implements View, Initializable {

	@FXML
	private GUIDisplayer GUIDisplayer;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		GUIDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->{ GUIDisplayer.requestFocus(); });

		GUIDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if(event.getCode() == KeyCode.UP) {
					setChanged();
					notifyObservers("move up");
				}
				if(event.getCode() == KeyCode.DOWN) {
					setChanged();
					notifyObservers("move down");
				}
				if(event.getCode() == KeyCode.LEFT) {
					setChanged();
					notifyObservers("move left");
				}
				if(event.getCode() == KeyCode.RIGHT) {
					setChanged();
					notifyObservers("move right");
				}
			}
		});
	}

	@Override
	public void displayLevel() {

		try{ GUIDisplayer.display(); }
		catch(Exception e) {passException(e);}

	}
	
	public void loadLevel() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Open File");
		fc.setInitialDirectory(new File("./resources"));
		File chosen = fc.showOpenDialog(null); // ??
		if(chosen == null)
			return;
		String loadCommand = "load "+chosen.getAbsolutePath();
		setChanged();
		notifyObservers(loadCommand);
		System.out.println("load command sent: " + loadCommand);
	}

	public void saveLevel() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Save File");
		fc.setInitialDirectory(new File("./resources"));
		File chosen = fc.showSaveDialog(null);
		if(chosen == null)
			return;
		String saveCommand = "save "+chosen.getAbsolutePath();
		setChanged();
		notifyObservers(saveCommand);
		System.out.println("save command sent: " + saveCommand);
	}

	public void exit() {
		Platform.exit();
		setChanged();
		notifyObservers("exit");
	}

	public GUIDisplayer getGUIDisplayer() {
		return this.GUIDisplayer;
	}

	public void passException(Exception e) {
		Platform.runLater(() -> {
			if(e.getMessage().equals("save completed!\n")) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Confirm");
				alert.setHeaderText("The file was saved successfully");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
			else if(e.getMessage().equals("You won! good job..")) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Level Completed");
				alert.setHeaderText("Level is completed!");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error occurred!");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		});
	}

}
