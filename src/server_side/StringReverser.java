package model.server_side;

public class StringReverser<Problem, Solution> implements Solver<Problem, Solution> {
	
	// The string (Problem) reverses into new reversed string (solution). 
	@Override
	public Solution solve(Problem problem) {
		StringBuilder solutionReverseString = new StringBuilder();
		solutionReverseString.append(problem);
		solutionReverseString.reverse();
		
		return (Solution)solutionReverseString.toString();
	}
}
