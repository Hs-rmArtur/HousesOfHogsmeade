import java.awt.Color;
import java.awt.Graphics;

public class Star {
	int x;
	int y;
	int radius;
	
	Color color;
	
	public Star(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		
		color = Color.YELLOW;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, radius, radius);
	}
	
}
