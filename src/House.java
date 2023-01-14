import java.awt.Color;
import java.awt.Graphics;

public class House {
	int x;
	int y;
	int width;
	int height;
	boolean lightOn;
	Color wallColor;
	
	int windowWidth = 10;
	
	public House (int x, int y, int width, int height, Color wallColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.wallColor = wallColor;
		
		lightOn = false;
		
		
	}
	
	public boolean switchLight (int x, int y) {
		
		return true;
	}
	
	public void draw (Graphics g) {
		
		
		// Wall of the house
		g.setColor(wallColor);
		g.fillRect(x, y, width, height);
		
		// Roof of the house
		g.setColor(Color.gray);
		g.fillRect(x, y, width, height / 6);
		
		// Chimney of the house
		g.setColor(wallColor);
		g.fillRect(x + 20, y - height / 10, width / 10, height / 10);
		
		// Part of the chimney
		g.setColor(Color.gray);
		g.fillRect(x + 20, y - height / 10, width / 10, height / 10 / 4);
		
		
		
	}
	
	private int determineNumberOfWindows() {
		
		
		
		return width / windowWidth;
	}
}
