package io.github.katherinaxc.bioevosim;

import java.util.ArrayList;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		// Scanner console = new Scanner(System.in);
		// System.out.println("Starting board X?");
		// int sizeX = console.nextInt();
		int sizeX = 5;
		// System.out.println("Starting board Y?");
		// int sizeY = console.nextInt();
		int sizeY = 5;
		// System.out.println("Starting board predator/prey count EACH?");
		// int countEach = console.nextInt();
		int countEach = 8;
		// System.out.println("Starting stat lower bound?");
		// int lowerBound = console.nextInt();
		int lowerBound = 2;
		// System.out.println("Starting stat upper bound?");
		// int upperBound = console.nextInt();
		int upperBound = 8;
		// System.out.println("How many iterations to run?");
		// int iterations = console.nextInt();
		int iterations = 1;
		// console.close();
		Board board = new Board(sizeX, sizeY, countEach, lowerBound, upperBound);
		initBoard(board);
		for (int i = 0; i < iterations; i++) {
			board.randomize();
			updateBoard(board);
			board.sortByStat();
			board.feast();
			board.checkSurvivors();
			board.reproduce();
		}
	}

	public static void initBoard(Board board) {
		int dim = Math.min(800, Math.max(board.sizeX, board.sizeY) * 100);
		StdDraw.setPenColor();
		StdDraw.setCanvasSize(dim, dim);
		StdDraw.setScale(-.05, 1.05);
		StdDraw.setPenRadius(0.005);
		drawCardsGrid(board.sizeX, board.sizeY);
		StdDraw.circle(0.5, 0.5, 0.015);
		StdDraw.textRight(1, -0.025, "Click or Space Key To Advance");
	}

	public static void updateBoard(Board board) {
	}

	public static void drawCreatureStat(Creature creature, double posX, double posY, double size) {
		if (creature instanceof Predator) {
			StdDraw.setPenColor(StdDraw.RED);
		} else {
			StdDraw.setPenColor(StdDraw.GREEN);
		}
		StdDraw.circle(posX, posY, size);
		StdDraw.text(posX, posY, "" + creature.stat);
	}

	public static void drawCellContents(Board board, int coordX, int coordY) {
		ArrayList<Creature> cellcontents = board.getCellContents(coordX, coordY);
		double cellWidth = cellDimension(coordX);
		double cellHeight = cellDimension(coordY);
		double posTop = cellHeight * (coordY + 1);
		double posBot = cellHeight * coordY;
		double posLeft = cellWidth * coordX;
		double posRight = cellWidth * (coordX + 1);
	}

	public static void drawCardsGrid(int cellsX, int cellsY) {
		double cellWidth = cellDimension(cellsX);
		double cellHeight = cellDimension(cellsY);
		for (int i = 0; i <= cellsX; i++) {
			StdDraw.line(i * cellWidth, 0, i * cellWidth, 1);
		}
		for (int i = 0; i <= cellsY; i++) {
			StdDraw.line(0, i * cellHeight, 1, i * cellHeight);
		}
	}

	public static double cellDimension(int dimension) {
		return 1. / dimension;
	}
}