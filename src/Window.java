import java.awt.Color;
import java.awt.Graphics;

public class Window {
	final static private Color WINDOW_COLOR_LIGHT_OFF = Color.WHITE;
	final static private Color WINDOW_COLOR_LIGHT_ON = Color.YELLOW;

	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;

	private boolean lightState;

	public Window(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		color = WINDOW_COLOR_LIGHT_OFF;
		lightState = false;
	}

	public void draw(Graphics g) {
		
		if (lightState) {
			color = WINDOW_COLOR_LIGHT_ON;
		} else {
			color = WINDOW_COLOR_LIGHT_OFF;
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

}
