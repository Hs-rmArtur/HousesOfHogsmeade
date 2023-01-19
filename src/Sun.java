import java.awt.Color;
import java.awt.Graphics;

/**
 * Sun is illustrating a sun at day and a moon at night. Position and size can
 * be set individually.
 * 
 * @author Fouad Ahsayni, Mykhailo Fakliier, Artur Konkel
 * @version 1.0
 */

public class Sun {
	final static private Color SUN_COLOR_DAY = Color.YELLOW;
	final static private Color SUN_COLOR_NIGHT = Color.WHITE;

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

	public void draw(Graphics g, Color skyColor) {
		if (dayTime) {
			// Sun
			g.setColor(SUN_COLOR_DAY);
			g.fillOval(x, y, radius, radius);

		} else {
			// Moon
			g.setColor(SUN_COLOR_NIGHT);
			g.fillOval(x, y, radius, radius);

			g.setColor(skyColor);
			g.fillOval(x - 20, y, radius, radius);
		}
	}

}
