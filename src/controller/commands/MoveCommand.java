package controller.commands;

import model.Model;
import model.receivers.move.Direction;

import java.io.IOException;

/**
 * Move command moves the character in a direction
 */
public class MoveCommand implements Command {

	private Model model = null;
	private Direction direction = null;

	/**
	 * Constructor
	 * @param model is a Model
	 */
	public MoveCommand(Model model ) { this.model = model; }

	/**
	 * Sets the command's parameters
	 * @param params Contains the direction
	 * @throws IOException
	 */
	public void setParams(String[] params) throws IOException {

		if(params.length == 1) {
			throw new IOException("please choose a direction!");
		}

		switch(params[1]) {
		
		case "up":	  this.direction = Direction.up;
					  break;
			 
		case "down":  this.direction = Direction.down;
					  break;
			
		case "right": this.direction = Direction.right;
					  break;
			
		case "left":  this.direction = Direction.left;
				      break;
			
		default:	  direction = null;
			   	      System.out.println("invalid direction");
					  break;
		}
		
	}

	/**
	 * Calls the model move method
	 * @throws Exception
	 */
	@Override
	public void execute() throws Exception {
		if(direction == null) throw new IOException("invalid direction\n");
		model.move(direction);
	}

}


