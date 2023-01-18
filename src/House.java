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

	private int spaceBetweenWindowsHorizontal = 20;
	private int spaceBetweenWindowsVertical = 20;
	
	private int windowsWidth = 40;
	private int windowsHeight = 60;

	private Color wallColor;
	private Color roofColor = Color.GRAY;

	private int roofHeight;

	public House(int x, int y, int width, int height, Color wallColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.wallColor = wallColor;

		roofHeight = height / 6;

		buildWindows();
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
		g.fillRect(x + 20, y - height / 10, width / 10, height / 10);

		// Roof of the chimney
		g.setColor(roofColor);
		g.fillRect(x + 20, y - height / 10, width / 10, height / 10 / 4);

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

	private void buildWindows() {
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
	}

	private void drawWindowRows(Graphics g) {

	}

	private int determineNumberOfWindowRows() {
		int numberOfWindowRows = (height - roofHeight) / (windowsHeight + spaceBetweenWindowsVertical);
		return numberOfWindowRows;
	}

	private int determineNumberOfWindowsInRow() {
		int numberOfWindows = width / (windowsWidth + spaceBetweenWindowsHorizontal);
		spaceBetweenWindowsHorizontal = (width - (numberOfWindows - 1) * windowsWidth) / numberOfWindows;

		return numberOfWindows - 1;
	}
}
