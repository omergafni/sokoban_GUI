package testEx;

import java.io.*;
import java.net.Socket;

public class MyCompressorClient implements CompressorClient {

    @Override
    public String compress(String uncompressed) {

        char stringArray[] = uncompressed.toCharArray();
        int counter = 1;
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < stringArray.length; i++) {
            char c = stringArray[i];
            if(i == stringArray.length-1){
                addToCompressString(c,counter,s);
            }
            else if(c == stringArray[i+1]) {
                counter++;
            }
            else {
                addToCompressString(c,counter,s);
                counter = 1;
            }

        }
        return s.toString();
    }

    public void addToCompressString(char c, int counter, StringBuilder sb){
        if(c == '\n'){
            sb.append(c);
            return;
        }
        sb.append(c);
        sb.append(counter);
    }

    public void compressTest(String s) {
        String c = compress(s);
        System.out.println(c);
    }

    @Override
    public void send(String fileName, String ip, int port) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        String uncompressed = "";
        String compressed;
        while ((line = reader.readLine()) != null) {
            uncompressed = uncompressed.concat(line+'\n');
        }
        compressed = compress(uncompressed);

        Socket theServer = new Socket(ip,port);
        //System.out.println("connected to server!");
        PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(theServer.getInputStream()));

        outToServer.print(compressed);
        outToServer.println("end");
        outToServer.flush();

        line = inFromServer.readLine();
        System.out.println(line);

        outToServer.close();
        inFromServer.close();
        theServer.close();


    }
}
