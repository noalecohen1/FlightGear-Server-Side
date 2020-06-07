package model.server_side;

import java.util.List;

public interface Searchable <T> {
	
	// Returns the initial state of the problem.
	public State<T> getInitialState(); 
	
	// Returns true if the current state is the final state of the problem.
	public boolean isGoal(State<T> currentState);
	
	// Returns all the possible states of the current state.
	public List<State<T>> getAllPossibleStates(State<T> state);
}
