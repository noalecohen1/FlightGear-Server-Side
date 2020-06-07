package model.server_side;

public interface CacheManager<Problem, Solution> {
	
	// Returns true if the solution for the received problem exists, otherwise returns false.
	public boolean isSolutionExsits(Problem problem);
	
	// Returns the solution for the received problem.
	public Solution getSolution(Problem problem);
	
	// Saves the solution of the received problem in the cache manager.
	public void saveSolution(Problem problem, Solution solution);
}
