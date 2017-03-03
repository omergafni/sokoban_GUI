package testEx;

public class Level {

    char[][] data;
    public Runnable onChange;
    public Level(char[][] data) {this.data=data;}

    public void changeData(int row,int col,char c) {
        data[row][col]=c;
        onChange.run();
    }


    public static void main(String[] args) {
        char[][] data=new char[10][10];
        Level l=new Level(data);
        l.onChange=()->System.out.println("a change!");
        l.changeData(0, 0, 'a'); // output = "a change!"
    }

}
