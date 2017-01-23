package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.data.level.Level;
import model.receivers.display.Displayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GUIDisplayer extends Canvas implements Displayer
{

	private Level level;
	private int maxHeight;
	private int maxWidth;
	private StringProperty wallFileName;
	private StringProperty floorFileName;
	private StringProperty boxFileName;
	private StringProperty targetFileName;
	private StringProperty playerFileName;

	public GUIDisplayer() {
		wallFileName = new SimpleStringProperty();
		floorFileName = new SimpleStringProperty();
		boxFileName = new SimpleStringProperty();
		targetFileName = new SimpleStringProperty();
		playerFileName = new SimpleStringProperty();
	}


	private void redraw() throws Exception {
		if(level == null)
			return;
		double displayerHeight = this.getHeight();
		double displayerWidth = this.getWidth();
		double cellHeight = displayerHeight/maxHeight;
		double cellWidth = displayerWidth/maxWidth;
		
		GraphicsContext gc = getGraphicsContext2D();
		
		Image wall = null;
		Image player = null;
		Image target = null;
		Image box = null;
		Image floor = null;

		try {
			wall = new Image(new FileInputStream(wallFileName.get()));
			player = new Image(new FileInputStream(playerFileName.get()));
			target = new Image(new FileInputStream(targetFileName.get()));
			box = new Image(new FileInputStream(boxFileName.get()));
			floor = new Image(new FileInputStream(floorFileName.get()));
		} 
		catch(FileNotFoundException e) { e.printStackTrace(); }

		gc.clearRect(0, 0, displayerWidth, displayerHeight);
		
		for(int i = 0; i < level.getGrid().size(); i++) {
			for(int j = 0; j < level.getGrid().get(i).size(); j++) {
				char c = level.getGrid().get(i).get(j).getObjRep();
				if(c == ' '){
					gc.drawImage(floor,cellWidth*j, cellHeight*i, cellWidth, cellHeight);
				}
				if(c == '#'){
					//gc.fillRect(cellWidth*j, cellHeight*i, cellWidth, cellHeight);
					gc.drawImage(wall,cellWidth*j, cellHeight*i, cellWidth, cellHeight);
				}
				if(c == 'o'){
					gc.drawImage(target,cellWidth*j, cellHeight*i, cellWidth, cellHeight);
				}
				if(c == '@'){
					gc.drawImage(box,cellWidth*j, cellHeight*i, cellWidth, cellHeight);
				}
				if(c == 'A'){
					gc.drawImage(player,cellWidth*j, cellHeight*i, cellWidth, cellHeight);
				}

			}
		}
		if(level.checkIfWin()) {
			throw new Exception("You won! good job..");
		}
	}

	@Override
	public void display() throws Exception{
		redraw();
	}

	@Override
	public void setLevel(Level level) {
		this.level = level;
		maxHeight = level.getMaxHeight();
		maxWidth = level.getMaxWidth();
	}

	public String getWallFileName() { return wallFileName.get(); }
	public String getFloorFileName() { return floorFileName.get(); }
	public String getBoxFileName() { return boxFileName.get(); }
	public String getTargetFileName() { return targetFileName.get(); }
	public String getPlayerFileName() { return playerFileName.get(); }
	
	public void setWallFileName(String path) { this.wallFileName.set(path); }
	public void setFloorFileName(String path) { this.floorFileName.set(path); }
	public void setBoxFileName(String path) { this.boxFileName.set(path); }
	public void setTargetFileName(String path) { this.targetFileName.set(path); }
	public void setPlayerFileName(String path) { this.playerFileName.set(path); }

}
