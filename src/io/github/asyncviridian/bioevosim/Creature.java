package io.github.asyncviridian.bioevosim;

public abstract class Creature implements Comparable<Creature> {
	int stat;
	int birthgeneration;
	boolean is_hungry;
	Board board;
	int coordX;
	int coordY;

	public Creature(int stat, Board board, int coordX, int coordY) {
		this.stat = stat;
		this.birthgeneration = board.generation;
		this.board = board;
		this.coordX = coordX;
		this.coordY = coordY;
		this.is_hungry = true;
	}

	public void reposition(int coordX, int coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public int compareTo(Creature other) {
		// Sort from highest to lowest!!!
		return Integer.compare(other.stat, this.stat);
	}
}
