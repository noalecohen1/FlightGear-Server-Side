package model.test.milestone_1_2_3;

import model.server_side.BestFirstSearch;
import model.server_side.FileCacheManager;
import model.server_side.MatrixProblem;
import model.server_side.MySerialServer;
import model.server_side.Position;
import model.server_side.SearchableClientHandler;
import model.server_side.SearcherSolver;
import model.server_side.Server;

public class TestSetter {
	

	static Server s; 
	
	public static void runServer(int port) {
		
		s = new MySerialServer(port);
		
		try {
			FileCacheManager<MatrixProblem, String> cacheManager = new FileCacheManager<MatrixProblem, String>();
			SearcherSolver<MatrixProblem, String, Position> solver = new SearcherSolver<MatrixProblem, String, Position>(new BestFirstSearch<Position>());
			SearchableClientHandler<String, Position> clientHandler = new SearchableClientHandler<>(solver, cacheManager);
			
			System.out.println("The server is connected....");
			
			s.start(clientHandler, "end");
			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static void stopServer() {
		s.stop();
	}
}