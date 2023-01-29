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
	private static final Color SUN_COLOR_DAY = Color.YELLOW;
	private static final Color SUN_COLOR_NIGHT = Color.WHITE;

	private int x;
	private int y;
	private double centerX, centerY;
	private int diameter;
	private boolean dayTime;

	/**
	 * Constructor is responsible for the position x, position y and radius of the
	 * sun
	 * 
	 * @param x      is the position of the sun on the X-axis
	 * @param y      is the position of the sun on the Y-axis
	 * @param radius is radius of the sun
	 */
	public Sun(int x, int y, int diameter) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.dayTime = true;

		centerX = (x + diameter / 2);
		centerY = (y + diameter / 2);
	}

	/**
	 * The method switchTime is responsible for the click on the sun. There is a
	 * formula for counting the area of the sun in the whole JFrame.
	 * 
	 * @param x is the position of the sun on the X-axis in JFrame
	 * @param y is the position of the sun on the Y-axis in JFrame
	 * @return returns the dayTime (night or day)
	 */
	public boolean switchTime(int x, int y) {
		// Satz des Pythagoras
		if (Math.pow((x - centerX), 2) + Math.pow((y - centerY), 2) <= Math.pow(diameter / 2, 2)) {

			dayTime = !dayTime;
		}

		return dayTime;
	}

	/**
	 * The method draw is responsible for the drawing of the sun and moon (depends
	 * of the dayTime)
	 * 
	 * @param g        is the variable which is responsible for drawing of the sun
	 *                 and the moon
	 * @param skyColor is the color of the sky
	 */
	public void draw(Graphics g, Color skyColor) {
		if (dayTime) {
			// Sun
			g.setColor(SUN_COLOR_DAY); // setting of the color
			g.fillOval(x, y, diameter, diameter); // setting of the form of the sun

		} else {
			// Moon
			g.setColor(SUN_COLOR_NIGHT); // setting of the color
			g.fillOval(x, y, diameter, diameter); // setting of the form of the moon

			g.setColor(skyColor); // setting of the color of half moon
			g.fillOval(x - 20, y, diameter, diameter); // setting of the form of the half moon
		}
	}

	public boolean getDayTime() {
		return dayTime;
	}

}
