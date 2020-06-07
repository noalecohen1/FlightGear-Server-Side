package model.server_side;

public class SearcherSolver<Problem, Solution, stateType> implements Solver<Problem, Solution> {

	// Data members:
	private Searcher<stateType> searcher;
	
	// CTOR.
	public SearcherSolver(Searcher<stateType> otherSearcher) {
		
		this.searcher = otherSearcher;
	}
	
	@Override
	public Solution solve(Problem problem) {
		
		Searchable<stateType> matrixSolver = (Searchable)problem;
		
		return (Solution)searcher.search((Searchable<stateType>)problem);
	}

}
