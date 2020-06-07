package model.server_side;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class FileCacheManager<Problem,Solution> implements CacheManager<Problem, Solution> {

	HashMap<Problem,Solution> problemSolutionHashMap=new HashMap<>();
	// CTOR.
	public FileCacheManager() {
		this.problemSolutionHashMap = new HashMap<Problem, Solution>();
	}

	@Override
	public boolean isSolutionExsits(Problem problem) {
		File file = new File("./FileCacheManager/");
		file.mkdirs();
		try
		{
            boolean isExist = problemSolutionHashMap.containsKey(problem);
            if(isExist)
                return true;
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("./FileCacheManager/"+problem+".txt"));
			input.close();
			return true;
		} catch (IOException e) { return false; }
	}
	
	@Override
	public Solution getSolution(Problem problem) {
		File file = new File("./FileCacheManager/");
		file.mkdirs();
        Solution solution = problemSolutionHashMap.getOrDefault(problem, null);
        if(solution ==null){
            ObjectInputStream input = null;
			try {
				input = new ObjectInputStream(new FileInputStream("./FileCacheManager/"+problem+".txt"));
				solution = (Solution) input.readObject();
	            problemSolutionHashMap.put(problem,solution); //if the server go down and up again
	            input.close();
			} catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }   
        }
        
        return solution;
	}

	@Override
	public void saveSolution(Problem problem, Solution solution) {
		File file = new File("./FileCacheManager/");
		file.mkdirs();
        problemSolutionHashMap.put(problem,solution);
        ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream("./FileCacheManager/"+problem+".txt"));
			out.writeObject(solution);
			out.flush();
			out.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

	
//	// Data members:
//	private HashMap<Problem, Solution> solutionMap;
//	
//	// CTOR.
//	public FileCacheManager() {
//		super();
//		this.solutionMap = new HashMap<>();
//	}
//	
//	// Getters & Setters:
//	public HashMap<Problem, Solution> getSolutionMap() {
//		return solutionMap;
//	}
//
//	public void setSolutionMap(HashMap<Problem, Solution> otherSolutionMap) {
//		this.solutionMap = otherSolutionMap;
//	}
//	
//	// Returns true if the solution for the received problem exists, otherwise returns false.
//	public boolean isSolutionExsits(Problem problem) {
//		return (this.solutionMap.get(problem) != null);
//	}
//	
//	// Returns the solution for the received problem.
//	public Solution getSolution(Problem problem) {
//		return this.solutionMap.get(problem);
//	}
//	
//	// Saves the solution of the received problem in the cache manager.
//	public void saveSolution(Problem problem, Solution solution) {
//		this.solutionMap.put(problem, solution);
//	}
}
