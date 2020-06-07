package model.server_side;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MySerialServer implements Server {
	
	// Data members:
	private int port;
	private volatile boolean isOff;
	
	// CTOR.
	public MySerialServer(int otherPort) {
		super();
		this.port = otherPort;
		this.isOff = false;
	}
	
	// Opens the server and waits for clients.
	@Override
	public void open(ClientHandler clientHandler, String exitString) throws Exception {	
		ServerSocket server = new ServerSocket(this.port);
		server.setSoTimeout(1000);	// Every 1 second T.O happening if there is no connected client.
	
		while (this.isOff == false) {
			try {
				Socket aClient = server.accept();	// Blocking call.
				try {
					// Starts the protocol by clientHandler.
					clientHandler.handleClient(aClient.getInputStream() ,aClient.getOutputStream(), exitString);	//////
					
					aClient.close();
				} catch (IOException error) {error.printStackTrace();}
			} catch (SocketTimeoutException error) {error.printStackTrace();}
		}
		
		server.close();
	}
	
	// Starts the server.
	@Override
	public void start(ClientHandler clientHandler, String exitString) {
		new Thread(()->
		{
			try {
				open(clientHandler, exitString); ///////////
			} catch (Exception error) { error.printStackTrace(); }
		}).start();
	}
	
	// Stops the server.
	@Override
	public void stop() {
		this.isOff = true;
	}
}