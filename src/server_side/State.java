package model.server_side;

public class State<T> {
	
	// Data members:
	private T state; 
	private double cost; // How much steps it cost to reach this state.
	private State<T> cameFrom; // The state we came from to this state.
	
	// Default CTOR.
	public State() {
		
		this.state = null; // X ? X // 
		this.cost = 0;
		this.cameFrom = null;
	}
	
	// CTOR.
	public State(T otherState) {
		
		this.state = otherState;
		this.cost = 0;
		this.cameFrom = null;
	}
	
	// C'CTOR.
	public State(State<T> other) {
		
		this.state = other.state;
		this.cost = other.cost;
		this.cameFrom = other.cameFrom;
	}
	
	// CTOR.
	public State(T otherState, double otherCost)
	{
		this.state = otherState;
		this.cost = otherCost;
		this.cameFrom = null;
	}
	
	@Override
	public int hashCode() {
		return (this.state + "").hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.state.equals(((State<T>) obj).state);
	}
	
	public boolean equals(State<T> otherState)
	{
		return this.state.equals(otherState.state);
	}
	
	// Getters & Setters:
	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}	
}
