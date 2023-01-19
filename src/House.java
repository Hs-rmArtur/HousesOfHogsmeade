import java.awt.Color;
import java.awt.Graphics;

/**
 * House is building with rectangles a illustration of a real house. The color,
 * size and position can be set individually. Is possible to switch lights on
 * and off.
 * 
 * @author Fouad Ahsayni, Mykhailo Fakliier, Artur Konkel
 * @version 1.0
 */
public class House {
	private static final int WINDOWS_DINSTANCE_FROM_GROUND = 10;

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean lightOn;
	private boolean itsDay;
	private Window windows[][];

	private int spaceBetweenWindowsHorizontal;
	private int spaceBetweenWindowsVertical;

	private int windowsWidth;
	private int windowsHeight;

	private int chimneyX;
	private int chimneyY;
	private int chimneyHeight;
	private int chimneyWidth;
	private int chimneyRoofHeight;

	private Color wallColor;
	private Color roofColor;
	private Color chimneyColor;

	private int roofHeight;

	/**
	 * Constructor of House
	 * 
	 * @param x         setting x-position of House
	 * @param y         setting y-position of House
	 * @param width     setting width of House
	 * @param height    setting height of House
	 * @param wallColor setting wall color of House
	 * @param roofColor setting roof color of House
	 */
	public House(int x, int y, int width, int height, Color wallColor, Color roofColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.wallColor = wallColor;
		this.roofColor = roofColor;

		spaceBetweenWindowsHorizontal = 10;
		spaceBetweenWindowsVertical = 10;
		windowsWidth = 15;
		windowsHeight = 30;

		lightOn = false;
		itsDay = true;

		roofHeight = height / 6;

		chimneyX = x + width / 5;
		chimneyY = y - height / 10;
		chimneyHeight = height / 10;
		chimneyWidth = width / 10;
		chimneyRoofHeight = height / 10 / 4;

		chimneyColor = Color.GRAY;

		buildWindowsAndDoors();
	}

	/**
	 * Switching the lights of the house
	 * 
	 * @param x x-position of mouseClick
	 * @param y y-position of mouseClick
	 * @return boolean if light is on or not
	 */
	public boolean switchLight(int x, int y) {

		if ((x >= this.x && x <= this.x + width) && (y >= this.y && y <= this.y + height)) {

			lightOn = !lightOn;
		}

		return lightOn;
	}

	/**
	 * Draw the whole house
	 * 
	 * @param g setting Graphics
	 */
	public void draw(Graphics g) {

		// Wall of the house
		g.setColor(wallColor);
		g.fillRect(x, y, width, height);

		// Roof of the house
		g.setColor(roofColor);
		g.fillRect(x, y, width, roofHeight);

		// Chimney of the house
		g.setColor(wallColor);
		g.fillRect(chimneyX, chimneyY, chimneyWidth, chimneyHeight);

		// Roof of the chimney
		g.setColor(chimneyColor);
		g.fillRect(chimneyX, chimneyY, chimneyWidth, chimneyRoofHeight);

		// window
		drawWindowRow(g);

	}

	/**
	 * Draws a window row into the house
	 * 
	 * @param g setting Graphics
	 */
	private void drawWindowRow(Graphics g) {
		changeWindowsByDayStatus();
		adjustWindowLights();

		for (int i = 0; i < windows.length; i++) {
			for (int j = 0; j < windows[i].length; j++) {
				if (windows[i][j] != null) {
					windows[i][j].draw(g);
				}
			}
		}
	}

	/**
	 * Windows are changing light-off-color, when its day or night
	 */
	private void changeWindowsByDayStatus() {
		for (int i = 0; i < windows.length; i++) {
			for (int j = 0; j < windows[i].length; j++) {
				if (windows[i][j] != null) {
					windows[i][j].setItsDay(itsDay);
				}
			}
		}
	}

	/**
	 * Switch the light status of windows
	 */
	private void adjustWindowLights() {
		for (int i = 0; i < windows.length; i++) {
			for (int j = 0; j < windows[i].length; j++) {
				if (windows[i][j] != null) {
					windows[i][j].setLightState(lightOn);
				}
			}
		}
	}

	/**
	 * Changing one of the windows at the lower window-level to a door. When the
	 * amount of windows is even, then its the left one. When its uneven its the
	 * second one from the right.
	 * 
	 * @param windowsInRow amount of windows in one row of the house
	 */
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

	/**
	 * Building the windows into the house evenly and convert one of them in a door.
	 */
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

	/**
	 * Checks, how much windows + the space between the windows are fitting into the
	 * house-width.
	 * 
	 * @return number of fitting windows
	 */
	private int determineNumberOfWindowsInRow() {
		int numberOfWindows = width / (windowsWidth + spaceBetweenWindowsHorizontal);
		spaceBetweenWindowsHorizontal = (width - (numberOfWindows - 1) * windowsWidth) / numberOfWindows;

		return numberOfWindows - 1;
	}

	/**
	 *  * Checks, how much windows + the space between the windows are fitting into the
	 * house-height.
	 * @return number of fitting windows
	 */
	private int determineNumberOfWindowRows() {
		int numberOfWindowRows = (height - roofHeight) / (windowsHeight + spaceBetweenWindowsVertical);
		spaceBetweenWindowsVertical = ((height - roofHeight - WINDOWS_DINSTANCE_FROM_GROUND)
				- (numberOfWindowRows * windowsHeight)) / numberOfWindowRows;
		return numberOfWindowRows;
	}

	/**
	 * Getter of the variable lightOn
	 * @return boolean if light is on or not
	 */
	public boolean getLightOn() {
		return lightOn;
	}

	
	/**
	 * Setter of the variable itsDay
	 * @param state a boolean if it's day or not
	 */
	public void setItsDay(boolean state) {
		this.itsDay = state;
	}

}
