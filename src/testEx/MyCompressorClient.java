package testEx;

import java.io.*;
import java.net.Socket;

public class MyCompressorClient implements CompressorClient {

    @Override
    public String uncompress(String compress){
        char compressedArray[] = compress.toCharArray();
        char c;
        int charCounter;
        int arraySize = compressedArray.length;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < arraySize; i+=2){
            c = compressedArray[i];
            if(c == '\n' || i+1 == arraySize) {
                sb.append(c);
                i--;
            }
            else {
                charCounter = Character.getNumericValue(compressedArray[i+1]);
                for(int j = 0; j < charCounter; j++){
                    sb.append(c);
                }
            }
        }
        // save uncompressed data to a file:
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("c:/users/omer/desktop/output.txt"));
            String[] str = sb.toString().split("\n");
            for (String s : str) {
                out.write(s);
                out.newLine();
            }
            out.close();

        } catch(IOException e) {e.printStackTrace();}

        return sb.toString();
    }

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
