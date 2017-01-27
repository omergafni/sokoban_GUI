package view;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.data.level.Level;
import model.receivers.display.Displayer;

public class GUIDisplayer extends Canvas implements Displayer
{
	protected Level level;
	private int maxHeight;
	private int maxWidth;

	public GUIDisplayer() {}

	private void redraw() throws Exception {
		if(level == null)
			return;
		double displayerHeight = this.getHeight();
		double displayerWidth = this.getWidth();
		double cellHeight = displayerHeight/maxHeight;
		double cellWidth = displayerWidth/maxWidth;
		
		GraphicsContext gc = getGraphicsContext2D();

		Image wall = new Image(getClass().getResourceAsStream("/resources/images/wall.jpg"));
		Image player = new Image(getClass().getResourceAsStream("/resources/images/player.jpg"));
		Image target = new Image(getClass().getResourceAsStream("/resources/images/target.jpg"));
		Image box = new Image(getClass().getResourceAsStream("/resources/images/box.jpg"));
		Image floor = new Image(getClass().getResourceAsStream("/resources/images/floor.jpg"));

		gc.clearRect(0, 0, displayerWidth, displayerHeight);
		
		for(int i = 0; i < level.getGrid().size(); i++) {
			for(int j = 0; j < level.getGrid().get(i).size(); j++) {
				char c = level.getGrid().get(i).get(j).getObjRep();
				if(c == ' '){
					gc.drawImage(floor,cellWidth*j, cellHeight*i, cellWidth, cellHeight);
				}
				if(c == '#'){
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
			throw new Exception("good job..");
		}
		Platform.runLater(() -> requestFocus());
	}

	public void showLogo() {

		Image logo = new Image(getClass().getResourceAsStream("/resources/images/logo.jpg"));
		GraphicsContext gc = getGraphicsContext2D();
		gc.drawImage(logo,30,30,440,440);

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

}
