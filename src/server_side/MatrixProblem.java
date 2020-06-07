package model.server_side;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MatrixProblem implements Searchable<Position> {

	// Data members:
	private int[][] costsMatrix;
	private int width;
	private int height;
	State<Position> start;
	HashSet<State<Position>> goals;
	
	// Default CTOR.
	public MatrixProblem() {
		
		this.costsMatrix = null;
		this.width = 0;
		this.height = 0;
		this.start = null;
		this.goals = null;
	}
	
	// CTOR.
	public MatrixProblem(int[][] otherCostsMatrix, int otherWidth, int otherHight, 
						 Position otherStart, List<Position> goals) {
		
		this.costsMatrix = otherCostsMatrix;
		this.width = otherWidth;
		this.height = otherHight;
		this.start = new State(otherStart, otherCostsMatrix[otherStart.getRow()][otherStart.getCol()]);
		this.goals = new HashSet<>();
		
		for(Position p : goals) {
			this.goals.add(new State(p, otherCostsMatrix[p.getRow()][p.getCol()]));
		}
	}

	@Override
	public State<Position> getInitialState() {

		return this.start;
	}

	@Override
	public boolean isGoal(State<Position> currentState) {
		
		return this.goals.contains(currentState);
	}

	// Returns all the possible state from the current state position.
	@Override
	public List<State<Position>> getAllPossibleStates(State<Position> state) {

		Position statePosition = state.getState();
		ArrayList<State<Position>> successors = new ArrayList<>();
		
		// Left step.
		if(statePosition.getCol() != 0) {
			successors.add(new State (new Position(statePosition.getRow(), statePosition.getCol() - 1),
						   this.costsMatrix[statePosition.getRow()][statePosition.getCol() - 1]));
		}
		
		// Right step.
		if(statePosition.getCol() != this.width - 1) {
			successors.add(new State (new Position(statePosition.getRow(), statePosition.getCol() + 1),
					   this.costsMatrix[statePosition.getRow()][statePosition.getCol() + 1]));
		}
		
		// Up step.
		if(statePosition.getRow() != 0) {
			successors.add(new State (new Position(statePosition.getRow() - 1, statePosition.getCol()),
						   this.costsMatrix[statePosition.getRow() - 1][statePosition.getCol()]));
		}
		
		// Down step.
				if(statePosition.getRow() != this.height - 1) {
					successors.add(new State (new Position(statePosition.getRow() + 1, statePosition.getCol()),
								   this.costsMatrix[statePosition.getRow() + 1][statePosition.getCol()]));
				}
				
		return successors;
	}
	
	// Returns array of string with the directions of solution.
	public String[] getDirection(List<State<Position>> pathStates) {
		
		LinkedList<String> path = new LinkedList<>();
		
		for(State<Position> p : pathStates) {
			
			if(p.getCameFrom() == null) {
				break;
			}
			
			if(p.getState().getCol() > p.getCameFrom().getState().getCol()) {
				path.add("Right");
			}
			
			if(p.getState().getCol() < p.getCameFrom().getState().getCol()) {
				path.add("Left");
			}
			
			if(p.getState().getRow() > p.getCameFrom().getState().getRow()) {
				path.add("Down");
			}
			
			if(p.getState().getCol() < p.getCameFrom().getState().getCol()) {
				path.add("Up");
			}
			
		}
		
		Collections.reverse(path);
		return (String[]) path.toArray(new String[0]);
	}

	// Getters & Setters:
	public int[][] getCostsMatrix() {
		return costsMatrix;
	}

	public void setCostsMatrix(int[][] costsMatrix) {
		this.costsMatrix = costsMatrix;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public State<Position> getStart() {
		return start;
	}

	public void setStart(State<Position> start) {
		this.start = start;
	}

	public HashSet<State<Position>> getGoals() {
		return goals;
	}

	public void setGoals(HashSet<State<Position>> goals) {
		this.goals = goals;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(costsMatrix);
		result = prime * result + ((goals == null) ? 0 : goals.hashCode());
		result = prime * result + height;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatrixProblem other = (MatrixProblem) obj;
		if (!Arrays.deepEquals(costsMatrix, other.costsMatrix))
			return false;
		if (goals == null) {
			if (other.goals != null)
				return false;
		} else if (!goals.equals(other.goals))
			return false;
		if (height != other.height)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
}
