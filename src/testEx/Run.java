package testEx;

import model.receivers.move.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Run {

    public static void test1() throws Exception{
        String t1="x@@@x      A    x";
        MyCompressorClient mc=new MyCompressorClient();
        if(!mc.compress(t1).equals("x1@3x1 6A1 4x1"))
            System.out.println("compress failure");

        //---------------------------------------------
        String[] output={
                "x17",
                "x1@3x1 6A1 4x1",
                "x1@3x1 4B3 4x1",
                "x1 3x1 5>1 5x1",
                "x1 3<1 2B1x9",
                "x3 4B1 3B1 4x1",
                "x1@2 4B1 3B1 4x1",
                "x17"
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    ServerSocket server=new ServerSocket(5400);
                    Socket client=server.accept();
                    BufferedReader infromClient=new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String line;
                    int i=0;
                    boolean fail=false;
                    while(!(line=infromClient.readLine()).equals("end")){
                        if(i<output.length && !line.equals(output[i])){
                            fail=true;
                        }
                        i++;
                    }
                    client.getOutputStream().write("ok".getBytes());
                    infromClient.close();
                    client.getOutputStream().flush();
                    client.getOutputStream().close();
                    client.close();
                    server.close();
                    if(i!=output.length)
                        System.out.println("wrong compression lines");
                    if(fail)
                        System.out.println("compress failure in client output");
                }catch(Exception e){}
            }
        }).start();

        //new Thread(() -> mc.send("soko.txt", "127.0.0.1", 5400)).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mc.send("c:/users/omer/desktop/soko.txt", "127.0.0.1", 5400);
                } catch (IOException e) {e.printStackTrace();}
                }
        }).start();

        //---------------------------------------------

    }

    public static void test2() throws Exception{
        char[][] t1={
                {'x','x','x','x','x'},
                {'x',' ',' ',' ','x'},
                {'x',' ','B',' ','x'},
                {'x','R','A','x','x'},
                {'x',' ','B',' ','x'},
                {'x',' ','B',' ','x'},
                {'x','x','x','x','x'}
        };

        MyPolicyImplementor p=new MyPolicyImplementor();
        if(p.isLegal(t1, Direction.RIGHT))
            System.out.println("failed1 right");
        if(!p.isLegal(t1, Direction.UP))
            System.out.println("failed1 up");
        if(p.isLegal(t1, Direction.LEFT))
            System.out.println("failed1 left");
        if(p.isLegal(t1, Direction.DOWN))
            System.out.println("failed1 down");

        char[][] t2={
                {'x','x','x','x','x'},
                {'x',' ','x',' ','x'},
                {'x',' ','B',' ','x'},
                {' ','L','A','B','>'},
                {'x',' ','<',' ','x'},
                {'x','x','x','x','x'}
        };

        if(!p.isLegal(t2, Direction.RIGHT))
            System.out.println("failed2 right");
        if(p.isLegal(t2, Direction.UP))
            System.out.println("failed2 up");
        if(!p.isLegal(t2, Direction.LEFT))
            System.out.println("failed2 left");
        if(!p.isLegal(t2, Direction.DOWN))
            System.out.println("failed2 down");

    }

    public static void test3() throws Exception{
        char[][] data=new char[1][1];
        Level l=new Level(data);
        final boolean[] b=new boolean[1];
        l.onChange=()->{b[0]=true;};
        l.changeData(0, 0, 'a');
        if(!b[0])
            System.out.println("failed 3");
    }

    public static void test4() throws Exception{
        int[] t=new int[3];
        Command c1=new Command(10,1000) {
            @Override
            public void execute() {
                t[0]=1;
            }
        };
        Command c2=new Command(5,1000) {
            @Override
            public void execute() {
                t[1]=2;
            }
        };
        Command c3=new Command(15,2000) {
            @Override
            public void execute() {
                t[2]=3;
            }
        };

        CommandScheduler cs=new CommandScheduler();
        cs.insertCommand(c3);
        cs.insertCommand(c2);
        cs.insertCommand(c1);
        cs.start();
        Thread.sleep(3000);
        cs.stop();
        if(!(t[0]==1 && t[1]==2 && t[2]==3))
            System.out.println("failed 4");
    }

    public static void main(String[] args) throws Exception{

        final boolean[] gf=new boolean[1];
        gf[0]=true;
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(gf[0])
                    System.out.println("general execution time failure");
            }
        }).start();

        test1();
        test2();
        test3();
        test4();
        gf[0]=false;
        System.out.println("end");
    }


}
