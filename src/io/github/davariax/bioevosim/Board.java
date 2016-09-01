package io.github.davariax.bioevosim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Board {
	int sizeX;
	int sizeY;
	int generation;
	ArrayList<Predator> predators;
	ArrayList<Prey> prey;
	Random rand;

	public Board(int sizeX, int sizeY, int startingCountEach, int startingLowerBounds, int startingUpperBounds) {
		rand = new Random();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.generation = 0;
		predators = new ArrayList<Predator>();
		populatePredators(startingCountEach, startingLowerBounds, startingUpperBounds);
		prey = new ArrayList<Prey>();
		populatePrey(startingCountEach, startingLowerBounds, startingUpperBounds);
	}

	public void feast() {
		// Not the Tkaanie kind :3
		// Works from largest stat to smallest stat predators
		for (int i = 0; i < predators.size(); i++) {
			Predator current = predators.get(i);
			current.eat(getPreyInCell(current.coordX, current.coordY));
		}
	}

	public void checkSurvivors() {
		for (int i = predators.size() - 1; i >= 0; i--) {
			Predator current = predators.get(i);
			if (!current.canSurvive(generation)) {
				predators.remove(i);
			}
		}
		for (int i = prey.size() - 1; i >= 0; i--) {
			Prey current = prey.get(i);
			if (current.coordX == -1) {
				prey.remove(i);
			}
		}
	}

	public void reproduce() {
		for (int i = predators.size() - 1; i >= 0; i--) {
			predators.addAll(predators.get(i).reproduce());
			predators.remove(i);
		}
		for (int i = prey.size() - 1; i >= 0; i--) {
			prey.addAll(prey.get(i).reproduce());
			prey.remove(i);
		}
	}

	public void randomize() {
		for (int i = 0; i < predators.size(); i++) {
			Predator current = predators.get(i);
			current.reposition(rand.nextInt(sizeX), rand.nextInt(sizeY));
		}
		for (int i = 0; i < prey.size(); i++) {
			Prey current = prey.get(i);
			current.reposition(rand.nextInt(sizeX), rand.nextInt(sizeY));
		}
	}

	public void sortByStat() {
		Collections.sort(predators);
	}

	private void populatePredators(int number, int lowerBounds, int upperBounds) {
		for (int i = 0; i < number; i++) {
			Predator newpred = new Predator(rand.nextInt(upperBounds - lowerBounds) + lowerBounds, this, 0, 0);
			predators.add(newpred);
		}
	}

	private void populatePrey(int number, int lowerBounds, int upperBounds) {
		for (int i = 0; i < number; i++) {
			Prey newprey = new Prey(rand.nextInt(upperBounds - lowerBounds) + lowerBounds, this, 0, 0);
			prey.add(newprey);
		}
	}

	private ArrayList<Prey> getPreyInCell(int coordX, int coordY) {
		ArrayList<Prey> list = new ArrayList<Prey>();
		for (int i = 0; i < prey.size(); i++) {
			Prey testing = prey.get(i);
			if (testing.coordX == coordX && testing.coordY == coordY) {
				list.add(testing);
			}
		}
		return list;
	}

	private ArrayList<Predator> getPredatorsInCell(int coordX, int coordY) {
		ArrayList<Predator> list = new ArrayList<Predator>();
		for (int i = 0; i < predators.size(); i++) {
			Predator testing = predators.get(i);
			if (testing.coordX == coordX && testing.coordY == coordY) {
				list.add(testing);
			}
		}
		return list;
	}

	public ArrayList<Creature> getCellContents(int coordX, int coordY) {
		ArrayList<Creature> results = new ArrayList<Creature>();
		results.addAll(getPredatorsInCell(coordX, coordY));
		results.addAll(getPreyInCell(coordX, coordY));
		return results;
	}
}
