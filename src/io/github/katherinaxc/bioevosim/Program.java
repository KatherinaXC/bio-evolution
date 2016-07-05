package io.github.katherinaxc.bioevosim;

import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("Starting board X");
		int sizeX = console.nextInt();
		System.out.println("Starting board Y");
		int sizeY = console.nextInt();
		System.out.println("Starting board predator/prey count EACH");
		int countEach = console.nextInt();
		System.out.println("Starting stat lower bound");
		int lowerBound = console.nextInt();
		System.out.println("Starting stat upper bound");
		int upperBound = console.nextInt();
		System.out.println("How many iterations to run?");
		int iterations = console.nextInt();
		Board board = new Board(sizeX, sizeY, countEach, lowerBound, upperBound);
		for (int i = 0; i < iterations; i++) {
			board.randomize();
			board.sortByStat();
			board.feast();
			board.checkSurvivors();
			board.reproduce();
			//
		}
		/*
		 * StdDraw.setPenRadius(0.05); StdDraw.setPenColor(StdDraw.BLUE);
		 * StdDraw.point(0.5, 0.5); StdDraw.setPenColor(StdDraw.MAGENTA);
		 * StdDraw.line(0.2, 0.2, 0.8, 0.2);
		 */
	}
}