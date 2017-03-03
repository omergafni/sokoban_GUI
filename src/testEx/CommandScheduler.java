package testEx;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;

public class CommandScheduler {

    PriorityQueue<Command> commandQueue;
    Timer timer;
    boolean stop = false;

    public CommandScheduler () {

        timer = new Timer();
        commandQueue = new PriorityQueue<>(128,new Comparator<Command>(){

            @Override
            public int compare(Command c1, Command c2) {
                if(c1.timeToExecute == c2.timeToExecute) {
                    return c1.priority-c2.priority;
                }
                else {
                    return (int)(c1.timeToExecute-c2.timeToExecute);
                }
            }
        });
    }
    public void insertCommand(Command c){
        commandQueue.add(c);
    }


    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!stop) {
                    while (!commandQueue.isEmpty()) {
                        Command c = commandQueue.poll();
                        timer.schedule(new TimerTask() {@Override public void run() {c.execute();}} , c.timeToExecute);
                    }
                    try {
                    Thread.sleep(3600*1000);
                    } catch(Exception e) {e.printStackTrace();}
                }
            }

        }).start();
    }

    public void stop(){
        stop = false;
        timer.cancel();
    }


}
