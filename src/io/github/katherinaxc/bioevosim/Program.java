package io.github.katherinaxc.bioevosim;

import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("Starting board X?");
		int sizeX = console.nextInt();
		System.out.println("Starting board Y?");
		int sizeY = console.nextInt();
		System.out.println("Starting board predator/prey count EACH?");
		int countEach = console.nextInt();
		System.out.println("Starting stat lower bound?");
		int lowerBound = console.nextInt();
		System.out.println("Starting stat upper bound?");
		int upperBound = console.nextInt();
		System.out.println("How many iterations to run?");
		int iterations = console.nextInt();
		console.close();
		Board board = new Board(sizeX, sizeY, countEach, lowerBound, upperBound);
		initializePen();
		drawBoard(board);
		for (int i = 0; i < iterations; i++) {
			board.randomize();
			updateBoard(board);
			board.sortByStat();
			board.feast();
			board.checkSurvivors();
			board.reproduce();
		}
	}

	public static void initializePen() {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setScale(-.05, 1.05);
		StdDraw.setPenRadius(0.005);
	}

	public static void drawBoard(Board board) {
		drawCardsGrid(board.sizeX, board.sizeY);
		StdDraw.textRight(1, -0.025, "Click or Space Key To Advance");
		// TODO
	}

	public static void updateBoard(Board board) {
		// TODO
	}

	public static void drawCardsGrid(int cellsX, int cellsY) {
		double cellWidth = 1. / cellsX;
		double cellHeight = 1. / cellsY;
		for (int i = 0; i <= cellsX; i++) {
			StdDraw.line(i * cellWidth, 0, i * cellWidth, 1);
		}
		for (int i = 0; i <= cellsY; i++) {
			StdDraw.line(0, i * cellHeight, 1, i * cellHeight);
		}
	}
}