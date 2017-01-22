package model.policy;

import model.receivers.move.Move;

public interface Policy {

	public void execute(Move moveCommand);
}
