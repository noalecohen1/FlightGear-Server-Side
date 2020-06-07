package model.server_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class MyTestClientHandler<Problem,Solution> implements ClientHandler<Problem, Solution> {
	
	// Data members:
	private Solver<Problem, Solution> solver;
	private CacheManager<Problem, Solution> cacheManager;
	
	// CTOR.
	public MyTestClientHandler(Solver<Problem, Solution> otherSolver, CacheManager<Problem, Solution> otherCacheManager) {
		super();
		this.solver = otherSolver;
		this.cacheManager = otherCacheManager;
	}
	
	// in -> Messages from the client.
	// out -> messages from the server.
	@Override
	public void handleClient(InputStream in, OutputStream out, String exitString) throws Exception {		
		
		// Reads from user-client to the server.
		BufferedReader userInput = new BufferedReader(new InputStreamReader(in));
		
		// Writes to the user-client from the server.
		PrintWriter serverOutput = new PrintWriter(out); 
		
		String line;
		
		while((line = userInput.readLine()).equals(exitString) == false) {	// // // // //			
			if (this.cacheManager.isSolutionExsits((Problem)line) == true) {
				serverOutput.println(this.cacheManager.getSolution((Problem)line));
			}
			else {
				// Solves the problem.
				Solution solution = (Solution) this.solver.solve((Problem)line);
				
				// Saves the solution in the cache manager.
				this.cacheManager.saveSolution((Problem)line, solution);
				
				//Prints the solution to the user-client.
				serverOutput.println(solution);
			}
					
			serverOutput.flush();			
		}
		
		in.close();
		out.close();
		userInput.close();
		serverOutput.close();
	}
}
