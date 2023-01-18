import java.awt.Color;
import java.awt.Graphics;

public class House {
//	private static final int SPACE_BETWEEN_WINDOWS_HORIZONTAL = 20;
//	private static int SPACE_BETWEEN_WINDOWS_VERTICAL = 20;
	private static final int WINDOWS_DINSTANCE_FROM_GROUND = 10;

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean lightOn = false;
	private Window windows[][];

	private int spaceBetweenWindowsHorizontal = 10;
	private int spaceBetweenWindowsVertical = 10;

	private int windowsWidth = 15;
	private int windowsHeight = 30;

	private Color wallColor;
	private Color roofColor;
	private Color chimneyColor = Color.GRAY;

	private int roofHeight;

	public House(int x, int y, int width, int height, Color wallColor, Color roofColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.wallColor = wallColor;
		this.roofColor = roofColor;
		
		roofHeight = height / 6;

		buildWindowsAndDoors();
	}

	public boolean switchLight(int x, int y) {
		if ((x >= this.x && x <= this.x + width) && (y >= this.y && y <= this.y + height)) {

			lightOn = !lightOn;

			switchAllWindowLights();
		}

		return lightOn;
	}

	public void draw(Graphics g) {

		// Wall of the house
		g.setColor(wallColor);
		g.fillRect(x, y, width, height);

		// Roof of the house
		g.setColor(roofColor);
		g.fillRect(x, y, width, roofHeight);

		// Chimney of the house
		g.setColor(wallColor);
		g.fillRect(x + width / 5, y - height / 10, width / 10, height / 10);

		// Roof of the chimney
		g.setColor(chimneyColor);
		g.fillRect(x + width / 5, y - height / 10, width / 10, height / 10 / 4);

		// window
		drawWindowRow(g);

	}

	private void drawWindowRow(Graphics g) {
		for (int i = 0; i < windows.length; i++) {
			for (int j = 0; j < windows[i].length; j++) {
				if (windows[i][j] != null) {
					windows[i][j].draw(g);
				}
			}
		}
	}

	private void switchAllWindowLights() {
		for (int i = 0; i < windows.length; i++) {
			for (int j = 0; j < windows[i].length; j++) {
				if (windows[i][j] != null) {
					windows[i][j].setLightState(lightOn);
				}
			}
		}
	}

	private void makeWindowToDoor(int windowsInRow) {
		int doorAtIndex = windows[0].length - 1;

		if (windowsInRow % 2 == 0) {
			windows[0][0].makeWindowToDoor(WINDOWS_DINSTANCE_FROM_GROUND);
		} else {

			if (windowsInRow < 3) {
				doorAtIndex = 0;
			} else {
				doorAtIndex = windows[0].length - 2;
			}

			windows[0][doorAtIndex].makeWindowToDoor(WINDOWS_DINSTANCE_FROM_GROUND);

		}

	}

	private void buildWindowsAndDoors() {
		int windowRows = determineNumberOfWindowRows();
		int windowsInRow = determineNumberOfWindowsInRow();
		int windowX = x + spaceBetweenWindowsHorizontal;
		int windowY = y + height - WINDOWS_DINSTANCE_FROM_GROUND - windowsHeight;
		windows = new Window[windowRows][windowsInRow];

		for (int i = 0; i < windows.length; i++) {
			for (int j = 0; j < windows[i].length; j++) {
				windows[i][j] = new Window(windowX, windowY, windowsWidth, windowsHeight);
				windowX += spaceBetweenWindowsHorizontal + windowsWidth;

			}
			windowX = x + spaceBetweenWindowsHorizontal;
			windowY = windowY - spaceBetweenWindowsVertical - windowsHeight;
		}

		makeWindowToDoor(windowsInRow);

	}


	private int determineNumberOfWindowsInRow() {
		int numberOfWindows = width / (windowsWidth + spaceBetweenWindowsHorizontal);
		spaceBetweenWindowsHorizontal = (width - (numberOfWindows - 1) * windowsWidth) / numberOfWindows;

		return numberOfWindows - 1;
	}

	private int determineNumberOfWindowRows() {
		int numberOfWindowRows = (height - roofHeight) / (windowsHeight + spaceBetweenWindowsVertical);
		spaceBetweenWindowsVertical = ((height - roofHeight - WINDOWS_DINSTANCE_FROM_GROUND)
				- (numberOfWindowRows * windowsHeight)) / numberOfWindowRows;
		return numberOfWindowRows;
	}
	
	public boolean getLightOn() {
		return lightOn;
	}




}
