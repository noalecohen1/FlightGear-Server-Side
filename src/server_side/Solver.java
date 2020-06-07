package model.server_side;

public interface Solver <Problem, Solution> {
	
	// Returns the solution to the received problem.
	public Solution solve(Problem problem);
}
