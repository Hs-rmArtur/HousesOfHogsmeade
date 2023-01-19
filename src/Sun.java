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

	/**
	 * Constructor is responsible for the position x, position y and radius of the sun
	 * @param x
	 * @param y
	 * @param radius
	 */
	public Sun(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.dayTime = true;
	}

	/**
	 * The method switchTime is responsible for the click on the sun. There is a formula for counting the area of
	 * the sun in the whole JFrame.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean switchTime(int x, int y) {

		if ((x >= this.x && x <= this.x + radius * 2) && (y >= this.y && y <= this.y + radius * 2)) {

			dayTime = !dayTime;
		}

		return dayTime;			// the method returns the dayTime (night or day)
	}

	/**
	 * The method draw is responsible for the drawing of the sun and moon (depends of the dayTime)
	 * @param g
	 * @param skyColor
	 */
	public void draw(Graphics g, Color skyColor) {
		if (dayTime) {
			// Sun
			g.setColor(SUN_COLOR_DAY);				// setting of the color
			g.fillOval(x, y, radius, radius);		// setting of the form of the sun

		} else {
			// Moon
			g.setColor(SUN_COLOR_NIGHT);			// setting of the color
			g.fillOval(x, y, radius, radius);		// setting of the form of the moon

			g.setColor(skyColor);					// setting of the color of half moon
			g.fillOval(x - 20, y, radius, radius);	// setting of the form of the half moon
		}
	}

}
