package controller.commands;

import model.Model;
import model.receivers.move.Direction;

import java.io.IOException;

public class MoveCommand implements Command {

	private Model model = null;
	private Direction direction = null;
	
	public MoveCommand(Model model ) { this.model = model; }
	public void setParams(String[] params) {
				
		switch(params[1]) {
		
		case "up":	  this.direction = Direction.UP; 
					  break;
			 
		case "down":  this.direction = Direction.DOWN;;
					  break;
			
		case "right": this.direction = Direction.RIGHT;;
					  break;
			
		case "left":  this.direction = Direction.LEFT;;
				      break;
			
		default:	  System.out.println("invalid direction");
					  break;
		}
		
	}
	
	
	@Override
	public void execute() throws IOException {
		model.move(direction);
	}

}


