package model.server_side;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {
	
	// Data members:
	protected PriorityQueue<State<T>> openList;
	private int evaluatedNode;

	
	// Default CTOR.
	public CommonSearcher() {
		
		super();
		this.openList = new PriorityQueue<>(1, (state1, state2) -> 
						{return Double.compare(state1.getCost(), state2.getCost()); }); // Predicate.
		this.evaluatedNode = 0;
	}
	
	@Override
	public abstract List<State<T>> search(Searchable<T> searchable);
	
	@Override
	public int getNumberOfNodesEvaluated() {
		
		return this.evaluatedNode;
	}
	
	// Pop the first state and increase the evaluatedNode by 1.
	protected State<T> popOpenList() {
		
		State<T> tmp = this.openList.poll();
		
		if(tmp != null){
			this.evaluatedNode++;
		}
		
		return tmp;
	}
	
	// Relax the cost.
	public boolean addToOpenList(State<T> state) {
		
		State<T> tmpState = new State<T>(state);
		
		tmpState.setCost(tmpState.getCost() + (tmpState.getCameFrom() != null ? tmpState.getCameFrom().getCost() : 0));
		
		return this.openList.add(tmpState);
	}
	
	// Clears the open list from states.
	public void clearOpenList() {
		
		this.openList.clear();
	}
	
	// Returns true if the open list is empty.
	public boolean isOpenListEmpty() {
		
		return this.openList.isEmpty();
	}
	
	// Returns true if the state is contains in the open list.
	public boolean isContainedInOpenList(State<T> state) {
		
		return this.openList.contains(state);
	}
	
	// Removes from open list specific state.
	public boolean removeFromOpenList(State<T> state) {
		
		return this.openList.remove(state);
	}
	
	
	public void backTraceHelper(LinkedList<State<T>> pathOfStates, State<T> goalState) {
		
		if(goalState == null) {
			return;
		}
		
		pathOfStates.add(goalState);
		backTraceHelper(pathOfStates, goalState.getCameFrom());
		
	}
	
	public LinkedList<State<T>> backTrace(State<T> goalState) {
		
		LinkedList<State<T>> pathOfStates = new LinkedList<>();
		
		backTraceHelper(pathOfStates, goalState);
		clearOpenList();
		
		return pathOfStates;
	}
	
	// Checks if the received state is already in the list.
	public State<T> findInOpenList(State<T> state) {
		
		Iterator<State<T>> it = this.openList.iterator();
		State<T> tmpState = null;
		
		while(it.hasNext() == true) {
			tmpState = it.next();
			
			if(tmpState.equals(state) == true) {
				return tmpState;
			}
		}

		return null;
	}
}
