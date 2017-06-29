package model.policy;

import model.receivers.move.Move;

/**
 * Represents a general game policy
 */
public interface Policy {

	/**
	 * Executes, if possible, a move command
	 * @param moveCommand A move command
	 * @throws Exception
	 */
	void execute(Move moveCommand) throws Exception;
}
