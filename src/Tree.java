import java.awt.Color;
import java.awt.Graphics;

public class Tree {
	
	int x;
	int y;
	int width;
	int height;
	
	public Tree(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw (Graphics g) {
		g.setColor(new Color(91, 58, 41));
		g.fillRect(x + width / 4 + 1 , y + height - 6, width / 2, height);
		g.setColor(new Color(5, 102, 41));
		g.fillOval(x, y, width, height);
	
		
	}

}
