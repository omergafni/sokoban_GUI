package view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	//private Level level;

	
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
		GUIDisplayer.redraw();
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
		System.out.println(countObservers());
	}

	public void saveLevel() {


	}

	public GUIDisplayer getGUIDisplayer() {
		return this.GUIDisplayer;
	}

}
