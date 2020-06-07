package model.server_side;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler<Problem, Solution> {

	// in -> Messages from the client.
	// out -> messages from the server.
	public void handleClient(InputStream in, OutputStream out, String exitString) throws Exception;
}
