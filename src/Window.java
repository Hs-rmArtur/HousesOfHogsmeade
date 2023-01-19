import java.awt.Color;
import java.awt.Graphics;

/**
 * Window is a part of House and can switch the light on and off 
 * based on the dayTime. The position and the size can be set individually.
 * 
 * @author Fouad Ahsayni, Mykhailo Fakliier, Artur Konkel
 * @version 1.0
 */
public class Window {
	final static private Color WINDOW_COLOR_LIGHT_OFF_AT_DAY = Color.WHITE;
	final static private Color WINDOW_COLOR_LIGHT_OFF_AT_NIGHT = Color.DARK_GRAY;
	final static private Color WINDOW_COLOR_LIGHT_ON = Color.YELLOW;

	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;

	private boolean lightState;
	private boolean itsDay;

	/**
	 * Constructor for Window
	 * 
	 * @param x setting the x position of the window
	 * @param y setting the y position of the window
	 * @param width setting the width of the window
	 * @param height setting the height of the window
	 */
	public Window(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		color = WINDOW_COLOR_LIGHT_OFF_AT_DAY;
		lightState = false;
	}

	/**
	 * Draws window and color of the window based on 
	 * x, y, the width, the height and the daytime
	 * 
	 * @param g setting graphics
	 */
	public void draw(Graphics g) {

		if (lightState) {
			color = WINDOW_COLOR_LIGHT_ON;
		} else {
			if (itsDay) {
				color = WINDOW_COLOR_LIGHT_OFF_AT_DAY;
			} else {
				color = WINDOW_COLOR_LIGHT_OFF_AT_NIGHT;

			}
		}

		g.setColor(color);
		g.fillRect(x, y, width, height);

	}

	/**
	 * Turns window into door
	 * 
	 * @param missingHeightTillDoor the distance between 
	 * the bottom of the window and the bottom of the house 
	 */
	public void makeWindowToDoor(int missingHeightTillDoor) {
		height += missingHeightTillDoor;

	}

	/**
	 * Gets the width of the window
	 * 
	 * @return width of the window
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the window
	 * 
	 * @return height of the window
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Turns on the light
	 * 
	 * @return light state
	 */
	public boolean getTurnedOn() {
		return lightState;
	}

	/**
	 * Sets the light state
	 * 
	 * @param state light on or light off
	 */
	public void setLightState(boolean state) {
		lightState = state;
	}

	/**
	 * Sets the daytime
	 * 
	 * @param state night or day
	 */
	public void setItsDay(boolean state) {
		itsDay = state;
	}

}
