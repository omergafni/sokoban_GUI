package testEx;

public abstract class Command {

    public final int priority;
    public final long timeToExecute;

    public Command(int priority,long timeToExecute) {
        this.priority=priority;
        this.timeToExecute=timeToExecute;
    }
    public abstract void execute();


}

