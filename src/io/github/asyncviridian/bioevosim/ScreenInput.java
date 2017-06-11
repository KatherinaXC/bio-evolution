package io.github.asyncviridian.bioevosim;

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

	public static double[] reset(String prompt) {
		StdDraw.clear();
		// Prompt question
		StdDraw.text(((double) 1) / 2, ((double) 1) * 3 / 4, prompt);
		// Instructions
		StdDraw.textRight(1, ((double) 1) / 10, "Press - to clear");
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
		// returns coords of submit button corners and input center
		return new double[] { submitCenterX - submitCenterXDim, submitCenterX + submitCenterXDim,
				submitCenterY - submitCenterYDim, submitCenterY + submitCenterYDim, inputCenterX, inputCenterY };
	}

	public static void updateInput(double[] inputbox, int num) {
		StdDraw.text(inputbox[0], inputbox[1], "" + num);
	}

	public static int queryInt(String prompt, int defaultResult) throws InterruptedException {
		double[] coords = ScreenInput.reset(prompt);
		double[] submitcoords = { coords[0], coords[1], coords[2], coords[3] };
		double[] inputcoords = { coords[4], coords[5] };
		StdDraw.show();
		int result = defaultResult;
		ScreenInput.updateInput(inputcoords, result);
		while (!buttonPressed(submitcoords) && !enterKeyPressed()) {
			if (numberPressed()) {
				char number = StdDraw.nextKeyTyped();
				int intnumber = Character.getNumericValue(number);
				result = (result * 10) + intnumber;
				ScreenInput.reset(prompt);
				ScreenInput.updateInput(inputcoords, result);
			} else if (dashPressed()) {
				result = 0;
				ScreenInput.reset(prompt);
				ScreenInput.updateInput(inputcoords, result);
			}
			// Wait until mouse click or enter key to move on
			ScreenInput.clearKeys();
			Thread.sleep(100);
			StdDraw.show();
		}
		// Reset mouse status
		StdDraw.setMousePressed(false);
		return result;
	}

	public static int queryInt(String prompt) throws InterruptedException {
		return queryInt(prompt, 0);
	}

	public static boolean buttonPressed(double[] coords) {
		double coordX = StdDraw.mouseX();
		double coordY = StdDraw.mouseY();
		return StdDraw.mousePressed() && coordX >= coords[0] && coordX <= coords[1] && coordY >= coords[2]
				&& coordY <= coords[3];
	}

	public static boolean numberPressed() {
		// Does not remove key from keylist regardless of situation
		if (StdDraw.hasNextKeyTyped()) {
			char key = StdDraw.nextKeyTyped();
			StdDraw.addKeyTyped(key);
			if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7'
					|| key == '8' || key == '9' || key == '0') {
				return true;
			}
		}
		return false;
	}

	public static boolean dashPressed() {
		if (StdDraw.hasNextKeyTyped()) {
			char key = StdDraw.nextKeyTyped();
			if (key == '-') {
				return true;
			}
			StdDraw.addKeyTyped(key);
		}
		return false;
	}

	public static boolean enterKeyPressed() {
		if (StdDraw.hasNextKeyTyped()) {
			char key = StdDraw.nextKeyTyped();
			if (key == '\n' || key == '\r') {
				return true;
			}
			// Replace the key that was tested
			StdDraw.addKeyTyped(key);
		}
		return false;
	}

	public static boolean spaceKeyPressed() {
		if (StdDraw.hasNextKeyTyped()) {
			char key = StdDraw.nextKeyTyped();
			if (key == ' ') {
				return true;
			}
			// Replace the key that was tested
			StdDraw.addKeyTyped(key);
		}
		return false;
	}

	public static void clearKeys() {
		// Clears all of StdDraw's saved keys
		while (StdDraw.hasNextKeyTyped()) {
			StdDraw.nextKeyTyped();
		}
	}

}
