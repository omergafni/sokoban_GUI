package model.policy;

import model.receivers.move.Move;

public interface Policy {

	void execute(Move moveCommand) throws Exception;
}
