import java.awt.Color;
import java.awt.Graphics;

public class House {
	int x;
	int y;
	int width;
	int height;
	boolean lightOn;
	Color wallColor;
	
	int windowsFirstFloor;
	int windowsGroundFloor;
	
	int windowWidth;
	int windowHeight;
	
	public House (int x, int y, int width, int height, Color wallColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.wallColor = wallColor;
		
		this.windowsFirstFloor = 3;
		this.windowsGroundFloor = 3;
		
		this.windowWidth = width / 4;
		this.windowHeight = height / 3;
		
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
		
		// Windows on the first floor
		g.setColor(Color.white);
	
		for (int i = 0; i < windowsFirstFloor; i++) {
			
			g.fillRect(x + width / 15, y + height / 5, windowWidth, windowHeight);
			
			x = x + windowWidth + width / 15;

		}	
		
		// Resetting x
		for (int i = 0; i < windowsFirstFloor; i++) {
						
			x = x - windowWidth - width / 15;

		}
		
		// Windows on the ground floor
		for (int i = 0; i < windowsGroundFloor; i++) {
			
			g.fillRect(x + width / 15, y + 120, windowWidth, windowHeight);
			
			x = x + windowWidth + width / 15;

		}	
		
	}
	
}
