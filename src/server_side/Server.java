package model.server_side;

public interface Server {

	// Opens the server and waits for clients.
	public void open(ClientHandler clientHandler, String exitString) throws Exception;
	
	// Starts the server.
	public void start(ClientHandler clientHandler, String exitString);
	
	// Stops the server.
	public void stop();
}
