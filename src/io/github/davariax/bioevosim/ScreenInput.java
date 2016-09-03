package io.github.davariax.bioevosim;

public class ScreenInput {

	static int sizeX;
	static int sizeY;

	public static void initInput(int sizeX, int sizeY) {
		ScreenInput.sizeX = sizeX;
		ScreenInput.sizeY = sizeY;
		StdDraw.setPenColor();
		StdDraw.setCanvasSize(sizeX, sizeY);
		StdDraw.setPenRadius(0.005);
	}

	public static double[] reset() {
		StdDraw.clear();
		// Input box
		double inputCenterX = ((double) 1) / 2;
		double inputCenterXDim = ((double) 1) * 2 / 5;
		double inputCenterY = ((double) 1) / 2;
		double inputCenterYDim = ((double) 1) / 8;
		StdDraw.rectangle(inputCenterX, inputCenterY, inputCenterXDim, inputCenterYDim);
		// Submit button
		double submitCenterX = ((double) 1) / 2;
		double submitCenterXDim = ((double) 1) / 6;
		double submitCenterY = ((double) 1) / 4;
		double submitCenterYDim = ((double) 1) / 10;
		StdDraw.rectangle(submitCenterX, submitCenterY, submitCenterXDim, submitCenterYDim);
		StdDraw.text(submitCenterX, submitCenterY, "Submit");
		// returns coords of submit button corners
		return new double[] { submitCenterX - submitCenterXDim, submitCenterX + submitCenterXDim,
				submitCenterY - submitCenterYDim, submitCenterY + submitCenterYDim };
	}

	public static int queryInt(String prompt) throws InterruptedException {
		double[] submitcoords = ScreenInput.reset();
		// TODO
		StdDraw.show();
		while (!submitButtonPressed(submitcoords) && !enterKeyPressed()) {
			// Wait until mouse click in button area to move on
			Thread.sleep(100);
		}
		StdDraw.setMousePressed(false);
		return 5;
	}

	public static boolean submitButtonPressed(double[] submitcoords) {
		double coordX = StdDraw.mouseX();
		double coordY = StdDraw.mouseY();
		return StdDraw.mousePressed() && coordX >= submitcoords[0] && coordX <= submitcoords[1]
				&& coordY >= submitcoords[2] && coordY <= submitcoords[3];
	}

	public static boolean enterKeyPressed() {
		if (StdDraw.hasNextKeyTyped()) {
			char key = StdDraw.nextKeyTyped();
			return (key == '\n' || key == '\r');
		}
		return false;
	}

	public static boolean spaceKeyPressed() {
		return (StdDraw.hasNextKeyTyped() && StdDraw.nextKeyTyped() == ' ');
	}

}
