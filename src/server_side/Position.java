package model.server_side;

public class Position {
	
	// Data members:
	private int row;
	private int col;
	
	// CTOR.
	public Position(int otherRow, int otherCol) {
		
		this.col = otherCol;
		this.row = otherRow;
	}

	// Getters & Setters:
	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	@Override
	public String toString() {
		
		return "Row: " + row + ", Column: " + col;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
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
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	 
	public boolean equals (Position other) {
		return (this.getRow() == other.getRow()) && (this.getCol() == other.getCol());
	}
}
