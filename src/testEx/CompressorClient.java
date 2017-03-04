package testEx;

import java.io.IOException;

public interface CompressorClient {

    String compress(String uncompressed);
    String uncompress(String compress);
    void send(String fileName,String ip,int port) throws IOException;


}
