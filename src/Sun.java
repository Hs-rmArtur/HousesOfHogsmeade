import java.awt.Color;
import java.awt.Graphics;

public class Sun {

	int x;
	int y;
	int radius;
	boolean dayTime;
	
	public Sun(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.dayTime = true;
	}
	
	public boolean switchTime(int x, int y) {
		
		if ((x >= this.x && x <= this.x + radius * 2) && (y >= this.y && y <= this.y + radius * 2)) {
			
			dayTime = !dayTime;
			
		}
		
		return dayTime;
		
	}
	
	public void draw (Graphics g) {
		
		g.setColor(Color.YELLOW);
		
		if (dayTime) {
			g.fillOval(x, y, radius, radius);
		} else {
			g.drawOval(x, y, radius, radius);
		}
	}
}
