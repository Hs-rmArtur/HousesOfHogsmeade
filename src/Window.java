import java.awt.Color;
import java.awt.Graphics;

/**
 * Window are a part of House and can switch light on and off according the
 * dayTime. Position and size can be set individually.
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

	public Window(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		color = WINDOW_COLOR_LIGHT_OFF_AT_DAY;
		lightState = false;
	}

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

	public void makeWindowToDoor(int missingHeightTillDoor) {
		height += missingHeightTillDoor;

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean getTurnedOn() {
		return lightState;
	}

	public void setLightState(boolean state) {
		lightState = state;
	}

	public void setItsDay(boolean state) {
		itsDay = state;
	}

}
