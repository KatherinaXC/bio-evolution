package io.github.davariax.bioevosim;
import java.util.ArrayList;

public class Prey extends Creature {

	public Prey(int stat, Board board, int coordX, int coordY) {
		super(stat, board, coordX, coordY);
	}

	public ArrayList<Prey> reproduce() {
		ArrayList<Prey> offspring = new ArrayList<Prey>();
		offspring.add(new Prey(this.stat + 1, this.board, this.coordX, this.coordY));
		if (this.stat > 1) {
			offspring.add(new Prey(this.stat - 1, this.board, this.coordX, this.coordY));
		} else {
			offspring.add(new Prey(this.stat, this.board, this.coordX, this.coordY));
		}
		return offspring;
	}

}
