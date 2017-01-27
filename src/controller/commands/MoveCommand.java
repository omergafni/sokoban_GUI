package controller.commands;

import model.Model;
import model.receivers.move.Direction;

import java.io.IOException;

public class MoveCommand implements Command {

	private Model model = null;
	private Direction direction = null;
	
	public MoveCommand(Model model ) { this.model = model; }
	public void setParams(String[] params) throws IOException {

		if(params.length == 1) {
			throw new IOException("please choose a direction!");
		}

		switch(params[1]) {
		
		case "up":	  this.direction = Direction.UP; 
					  break;
			 
		case "down":  this.direction = Direction.DOWN;
					  break;
			
		case "right": this.direction = Direction.RIGHT;
					  break;
			
		case "left":  this.direction = Direction.LEFT;
				      break;
			
		default:	  direction = null;
			   	      System.out.println("invalid direction");
					  break;
		}
		
	}

	@Override
	public void execute() throws Exception {
		if(direction == null) throw new IOException("invalid direction\n");
		model.move(direction);
	}

}


