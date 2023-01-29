import java.awt.Color;
import java.awt.Graphics;

/**
 * Star is illustrating stars. Position and size can be set individually.
 * 
 * @author Fouad Ahsayni, Mykhailo Fakliier, Artur Konkel
 * @version 1.0
 */
public class Star {
	private int x;
	private int y;
	private int radius;
	private Color color;

	/**
	 * Constructor of Star
	 * @param x setting x-position of star
	 * @param y setting y-position of star 
	 * @param radius setting radius of star
	 */
	public Star(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;

		color = Color.WHITE;
	}

	/**
	 * draw stars
	 * @param g setting Graphics
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, radius, radius);
	}

}
