package controller.server;

import java.io.OutputStream;

public interface Server {

	void start();
	void stop();
	OutputStream getOutputStream();

}
