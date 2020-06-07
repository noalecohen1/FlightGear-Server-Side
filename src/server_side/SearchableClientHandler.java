package model.server_side;

import java.io.BufferedReader;import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SearchableClientHandler<Solution, StateType> implements ClientHandler<MatrixProblem, Solution> {
	
	// Data members:
	private Solver solver;
	private CacheManager cacheManager;
	
	// CTOR.
	public SearchableClientHandler(Solver<MatrixProblem, Solution> otherSolver, CacheManager<MatrixProblem, Solution> otherCacheManager) {
		
		this.solver = otherSolver;
		this.cacheManager = otherCacheManager;
	}

	@Override
	public void handleClient(InputStream in, OutputStream out, String exitString) throws Exception {
		
		// Server reads from the user.
		BufferedReader userInput = new BufferedReader(new InputStreamReader(in));
		
		// Server writes to the user.
		PrintWriter serverOutput = new PrintWriter(new OutputStreamWriter(out));
		
		String line = null;
		
		ArrayList<int[]> listAsMatrix = new ArrayList<>();
		
		while((line = userInput.readLine()).equals(exitString) == false) {
			listAsMatrix.add(Arrays.asList(line.split(",")).stream().mapToInt(Integer::parseInt).toArray());
			
		}
		
		// Gets the start position.
		line = userInput.readLine();
		String[] splitter;
		splitter = line.split(",");
		
		Position start = new Position(Integer.parseInt(splitter[0]), Integer.parseInt(splitter[1]));
		
		// Gets the goal position.
		line = userInput.readLine();
		splitter = line.split(",");
		
		Position goal = new Position(Integer.parseInt(splitter[0]), Integer.parseInt(splitter[1]));
		
		// Converts Array-list into Matrix.
		
		int[][] matrix = new int[listAsMatrix.size()][];
		
		Iterator<int[]> it = listAsMatrix.iterator();
		
		int i = 0;
		int[] tmpArray = null;
		int tmpArraySize = 0;
		
		while(it.hasNext() == true) {
			tmpArray = it.next();
			tmpArraySize = tmpArray.length;
			
			matrix[i] = new int[tmpArraySize];
			
			System.arraycopy(tmpArray, 0, matrix[i], 0, tmpArraySize);
			
			i++;
		}
		
		ArrayList<Position> goalList = new ArrayList<>();
		goalList.add(goal);
		
		MatrixProblem matrixProblem = new MatrixProblem(matrix, tmpArraySize, listAsMatrix.size(), start, goalList);
		
		if(cacheManager.isSolutionExsits(matrixProblem) == true) {
			serverOutput.println(cacheManager.getSolution(matrixProblem));
		}
		else {
			List<State<Position>> paths = (List<State<Position>>)this.solver.solve(matrixProblem);
			
			String[] directionList = matrixProblem.getDirection(paths);
			
			Solution solution = (Solution) String.join(",", directionList);
			
			cacheManager.saveSolution(matrixProblem, solution);
			
			serverOutput.println(solution);
		}
		
		serverOutput.flush();
		serverOutput.close();
		userInput.close();
	}
	

}
