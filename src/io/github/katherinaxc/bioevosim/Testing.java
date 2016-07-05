package io.github.katherinaxc.bioevosim;

public class Testing {
	// Entirely for the purposes of testing the screen display and rendering
	// without input setup

	public static void main(String[] args) {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setScale(-.05, 1.05);
		StdDraw.setPenRadius(0.005);
		drawCardsGrid(5, 5);
		StdDraw.textRight(1, -0.025, "Click or Space Key To Advance");
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
