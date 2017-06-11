package io.github.asyncviridian.bioevosim;

import java.util.ArrayList;
//TODO: Add stats export functionality, a la what people need from the simulation
//TODO: Figure out what else I have to do?

public class Program {
	public static final int INPUT_DIM_X = 400;
	public static final int INPUT_DIM_Y = 300;
	public static final int DEFAULT_SIZEX = 5;
	public static final int DEFAULT_SIZEY = 5;
	public static final int DEFAULT_COUNT = 8;
	public static final int DEFAULT_STATLOW = 2;
	public static final int DEFAULT_STATHI = 8;
	public static final int DEFAULT_ITERATIONS = 100;
	public static final int DEFAULT_SQRCONTENT_DIM = 4;

	static int dimCreatSquare;

	public static void main(String[] args) throws InterruptedException {
		ScreenInput.initInput(INPUT_DIM_X, INPUT_DIM_Y);
		// If the user types in something broken here...
		// I'll just throw exceptions ¯\_(ツ)_/¯
		int sizeX = ScreenInput.queryInt("Starting board X?", DEFAULT_SIZEX);
		// int sizeX = DEFAULT_SIZEX;
		int sizeY = ScreenInput.queryInt("Starting board Y?", DEFAULT_SIZEY);
		// int sizeY = DEFAULT_SIZEY;
		int countEach = ScreenInput.queryInt("Starting board predator/prey count EACH?", DEFAULT_COUNT);
		// int countEach = DEFAULT_COUNT;
		int lowerBound = ScreenInput.queryInt("Starting stat lower bound?", DEFAULT_STATLOW);
		// int lowerBound = DEFAULT_STATLOW;
		int upperBound = ScreenInput.queryInt("Starting stat upper bound?", DEFAULT_STATHI);
		// int upperBound = DEFAULT_STATHI;
		int iterations = ScreenInput.queryInt("How many iterations to run?", DEFAULT_ITERATIONS);
		// int iterations = DEFAULT_ITERATIONS;
		dimCreatSquare = DEFAULT_SQRCONTENT_DIM;
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
			while (!StdDraw.mousePressed() && !ScreenInput.enterKeyPressed()) {
				// Wait until mouse click or correct keypress to move on
				ScreenInput.clearKeys();
				Thread.sleep(100);
			}
			StdDraw.setMousePressed(false);
		}
	}

	public static void initBoard(Board board) {
		int dim = Math.min(1000, Math.max(board.sizeX, board.sizeY) * 150);
		StdDraw.enableDoubleBuffering();
		StdDraw.setPenColor();
		StdDraw.setCanvasSize(dim, dim);
		StdDraw.setScale(-.05, 1.05);
		StdDraw.setPenRadius(0.005);
	}

	public static void updateBoard(Board board) {
		StdDraw.clear();
		StdDraw.setPenColor();
		drawCardsGrid(board.sizeX, board.sizeY);
		StdDraw.textRight(1, -0.025, "Click or Enter Key To Advance");
		StdDraw.textLeft(0, -0.025, "Iteration " + (board.generation + 1));
		for (int posX = 0; posX < board.sizeX; posX++) {
			for (int posY = 0; posY < board.sizeY; posY++) {
				drawCellContents(board, posX, posY);
			}
		}
		StdDraw.show();
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
		double cellWidth = cellDimension(board.sizeX);
		double cellHeight = cellDimension(board.sizeY);
		double posTop = cellHeight * (coordY + 1);
		double posBot = cellHeight * coordY;
		double posLeft = cellWidth * coordX;
		double posRight = cellWidth * (coordX + 1);
		double intervalUD = (posTop - posBot) / (dimCreatSquare + 1);
		double intervalLR = (posRight - posLeft) / (dimCreatSquare + 1);
		int creatureNum = 0;
		for (int i = 0; i < ((double) cellcontents.size()) / dimCreatSquare; i++) {
			for (int j = 0; j < dimCreatSquare && creatureNum < cellcontents.size(); j++) {
				drawCreatureStat(cellcontents.get(creatureNum), posLeft + intervalLR * (j + 1),
						posBot + intervalUD * (i + 1), Math.min(cellWidth, cellHeight) / (2 * (dimCreatSquare + 2)));
				creatureNum++;
			}
		}
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