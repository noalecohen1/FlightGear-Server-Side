package model.server_side;

import java.util.List;

public interface Searcher<T> {
	
	// Returns a list of the solution of the problem.
	public List<State<T>> search(Searchable<T> searchable);
	
	// Returns how many nodes were evaluated till now.
	public int getNumberOfNodesEvaluated();
}
