import java.awt.Color;
import java.awt.Graphics;

/**
 * Star is illustrating stars. Position and size can be set individually.
 * 
 * @author Fouad Ahsayni, Mykhailo Fakliier, Artur Konkel
 * @version 1.0
 */

public class Star {
	int x;
	int y;
	int radius;

	Color color;

	public Star(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;

		color = Color.WHITE;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, radius, radius);
	}

}
