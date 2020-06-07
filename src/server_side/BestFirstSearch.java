package model.server_side;

import java.util.LinkedHashSet;
import java.util.List;

public class BestFirstSearch<T> extends CommonSearcher<T>  {

	@Override
	public List<State<T>> search(Searchable<T> searchable) {
		
	State<T> initialState = new State<T>(searchable.getInitialState());
	initialState.setCost(0);
	addToOpenList(initialState);
	LinkedHashSet<State<T>> closedSet = new LinkedHashSet<>();
	
	while(isOpenListEmpty() == false) {
		
		State<T> currentState = this.popOpenList();
		closedSet.add(currentState);
		
		if(searchable.isGoal(currentState)) {
			return backTrace(currentState);
		}
		
		List<State<T>> successors = searchable.getAllPossibleStates(currentState);
		
		for(State<T> neighbor : successors) {
			if((closedSet.contains(neighbor) == false) && (isContainedInOpenList(neighbor) == false)) {
				neighbor.setCameFrom(currentState);
				addToOpenList(neighbor);
			}
			else if((closedSet.contains(neighbor) == false) && 
					(currentState.getCost() + neighbor.getCost() < findInOpenList(neighbor).getCost())) {
				removeFromOpenList(neighbor);
				addToOpenList(neighbor);
			}
			
		}
	}
	
	return null;
}
}
