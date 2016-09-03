package io.github.davariax.bioevosim;

import java.util.ArrayList;

public class Predator extends Creature {

	public Predator(int stat, Board board, int coordX, int coordY) {
		super(stat, board, coordX, coordY);
	}

	public void eat(ArrayList<Prey> cellmates) {
		if (cellmates.size() > 0 && preyAreEdible(cellmates)) {
			int lowestvisible = 0;
			int loweststat = cellmates.get(lowestvisible).stat;
			for (int i = 0; i < cellmates.size(); i++) {
				int testingstat = cellmates.get(i).stat;
				if (testingstat < loweststat) {
					lowestvisible = i;
					loweststat = testingstat;
				}
			}
			Prey target = cellmates.get(lowestvisible);
			target.reposition(-1, -1); // aka killing them
			this.is_hungry = false;
		}
	}

	public boolean canSurvive(int generation) {
		if (this.is_hungry && (generation - this.birthgeneration) > 1) {
			return false;
		} else {
			return true;
		}
	}

	public ArrayList<Predator> reproduce() {
		ArrayList<Predator> offspring = new ArrayList<Predator>();
		if (!this.is_hungry) {
			offspring.add(new Predator(this.stat + 1, this.board, this.coordX, this.coordY));
			if (this.stat > 1) {
				offspring.add(new Predator(this.stat - 1, this.board, this.coordX, this.coordY));
			}
		} else {
			offspring.add(this);
		}
		return offspring;
	}

	private boolean preyAreEdible(ArrayList<Prey> prey) {
		boolean edible = false;
		if (prey.size() > 0) {
			for (int i = 0; i < prey.size(); i++) {
				if (prey.get(i).stat < this.stat) {
					edible = true;
				}
			}
		}
		return edible;
	}

}
