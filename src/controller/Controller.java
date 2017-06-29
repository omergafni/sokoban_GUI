package controller;

/**
 * Describes a general controller that contains a blocking queue of commands for execution
 */
public interface Controller {

    /**
     * Runs the blocking queue
     */
    void start();

    /**
     * Stops the blocking queue
     */
    void stop();

}
