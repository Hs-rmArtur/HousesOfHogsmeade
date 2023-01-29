import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * Hogsmeade are building a street with different houses, trees, sun/moon and
 * stars. Sun is able to switch between day and night.
 * 
 * @author Fouad Ahsayni, Mykhailo Fakliier, Artur Konkel
 * @version 1.0
 */
public class Hogsmeade extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private static final int MAX_AMOUNT_STARS = 150;
	private static final int MAX_STAR_SIZE = 5;
	private static final int AMOUNT_OF_HOUSES = 5;
	private static final int AMOUNT_OF_TREES = 3;
	private static final int HOUSE_WIDTHS[] = { 150, 150, 50, 200, 100 };
	private static final int HOUSE_HEIGHTS[] = { 120, 150, 100, 170, 120 };
	private static final Color HOUSE_COLORS[] = { Color.PINK, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.RED };
	private static final Color ROOF_COLORS[] = { new Color(130, 0, 0), new Color(130, 54, 0), new Color(172, 113, 35),
			new Color(119, 75, 16), new Color(122, 92, 52) };
	private static final Color SKY_COLOR_NIGHT = Color.BLACK;
	private static final Color SKY_COLOR_DAY = new Color(50, 100, 200);

	private int hogsMeadSizeX;
	private int hogsMeadSizeY;
	private int streetHeight;
	private int houseLineY;
	private int treeLineY;
	private Color skyColor;

	private Sun sun;
	private Tree trees[] = new Tree[AMOUNT_OF_TREES];;
	private House houses[] = new House[AMOUNT_OF_HOUSES];
	private Star stars[] = new Star[MAX_AMOUNT_STARS];

	private int houseXs[] = new int[AMOUNT_OF_HOUSES];
	private int houseYs[] = new int[AMOUNT_OF_HOUSES];

	/**
	 * Constructor of Hogsmead, initializing of trees, stars, sun and houses
	 * 
	 * @param hogsMeadSizeX the width of the whole screen / hogsmead
	 * @param hogsMeadSizeY the height of the whole screen / hogsmead
	 */
	public Hogsmeade(int hogsMeadSizeX, int hogsMeadSizeY) {

		this.hogsMeadSizeX = hogsMeadSizeX;
		this.hogsMeadSizeY = hogsMeadSizeY;

		streetHeight = 100;
		houseLineY = hogsMeadSizeY - streetHeight + 10;
		treeLineY = houseLineY + 10;
		skyColor = SKY_COLOR_DAY;

		this.addMouseListener(this);

		// Initialisierung

		buildTrees();

		buildStars();

		buildSun();

		buildHouseStreet();
	}

	/**
	 * Draw all objects on the frame
	 */
	public void paint(Graphics g) {
		super.paint(g);
		drawSky(g);

		drawSun(g);

		if (!sun.getDayTime()) {
			drawStars(g);
		}

		drawStreet(g);

		drawHouses(g);

		drawTrees(g);

		// making darkness
		if (!sun.getDayTime()) {
			addDarkness(g);
		}

	}

	/**
	 * Adding darkness, according the number of houses, that has there lights off
	 * 
	 * @param g setting Graphics
	 */
	private void addDarkness(Graphics g) {
		g.setColor(new Color(28, 27, 27, adjustDarkness()));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Draw the sun
	 * 
	 * @param g setting Graphics
	 */
	private void drawSun(Graphics g) {
		sun.draw(g, skyColor);
	}

	/**
	 * Draw the Sky by night or day
	 * 
	 * @param g setting Graphics
	 */
	private void drawSky(Graphics g) {
		if (sun.getDayTime()) {
			skyColor = SKY_COLOR_DAY;
		} else {
			skyColor = SKY_COLOR_NIGHT;
		}

		g.setColor(skyColor);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Draw the stars
	 * 
	 * @param g setting Graphics
	 */
	private void drawStars(Graphics g) {
		for (int i = 0; i < stars.length; i++) {
			if (stars[i] != null) {
				stars[i].draw(g);
			}
		}
	}

	/**
	 * Draw the street
	 * 
	 * @param g setting Graphics
	 */
	private void drawStreet(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, hogsMeadSizeY - streetHeight, hogsMeadSizeX, streetHeight);

	}

	/**
	 * Draw the trees
	 * 
	 * @param g setting Graphics
	 */
	private void drawTrees(Graphics g) {
		for (int i = 0; i < trees.length; i++) {
			if (trees[i] != null) {
				trees[i].draw(g);
			}
		}
	}

	/**
	 * Draw the houses with the information, if it's night or day
	 * 
	 * @param g setting Graphics
	 */
	private void drawHouses(Graphics g) {
		letHousesKnowDayState();

		for (int i = 0; i < houses.length; i++) {
			if (houses[i] != null) {
				houses[i].draw(g);
			}
		}
	}

	/**
	 * The transparency of the darkness is getting lower, when more houses has there
	 * lights on --> it's getting brighter
	 * 
	 * @return transparency of darkness
	 */
	private int adjustDarkness() {
		int housesWithLightsOn = 0;
		int darkness = 150;

		for (int i = 0; i < houses.length; i++) {
			if (houses[i].getLightOn()) {
				housesWithLightsOn++;
			}
		}

		return darkness - housesWithLightsOn * 24;

	}

	/**
	 * Letting the houses know, if it's currently night or day
	 */
	private void letHousesKnowDayState() {

		for (int i = 0; i < houses.length; i++) {
			houses[i].setItsDay(sun.getDayTime());
		}
	}

	/**
	 * Putting the stars into an array, by getting there position and size randomly
	 */
	private void buildStars() {
		int x;
		int y;
		int radius;

		for (int i = 0; i < stars.length; i++) {
			x = getRandomPos(0, hogsMeadSizeX);
			y = getRandomPos(0, hogsMeadSizeY - streetHeight);
			radius = getRandomPos(0, MAX_STAR_SIZE);

			stars[i] = new Star(x, y, radius);
		}

	}

	/**
	 * The method getRandomPos is responsible for the giving of the random position in the sky.
	 * @param min is the start range
	 * @param max is the end range
	 * @return returns the random position in the sky
	 */
	public static int getRandomPos(int min, int max) {
		return (int) Math.round(Math.random() * (max - min) + min);
	}

	/**
	 * The method buildTrees is responsible for the building of the trees. We have such parameter as width, height,
	 * position on the X-axis and on the Y-axis
	 */
	private void buildTrees() {
		int width = 50;
		int height = 70;
		int x = 100;
		int y = treeLineY - height;

		for (int i = 0; i < trees.length; i++) {
			trees[i] = new Tree(x, y, width, height);
			x = (int) (x * 2.2);
		}
	}

	/**
	 * The method buildSun is responsible for the building of the sun. Therefore it is necessary
	 * to write the parameters as position in X-axis, Y-axis and radius of the sun.
	 */
	private void buildSun() {
		sun = new Sun(550, 50, 70);
	}

	/**
	 * The method determineYOfHouses is responsible for the position of Houses on the X-axis
	 */
	private void determineXOfHouses() {
		int houseX = 0;
		// FirstHouse starts at 0
		houseXs[0] = houseX;

		for (int i = 1; i < houseXs.length; i++) {
			houseX += HOUSE_WIDTHS[i - 1];
			houseXs[i] = houseX;
		}

	}

	/**
	 * The method determineYOfHouses is responsible for the position of Houses on the Y-axis
	 */
	private void determineYOfHouses() {
		for (int i = 0; i < houseYs.length; i++) {
			houseYs[i] = houseLineY - HOUSE_HEIGHTS[i];
		}
	}

	/**
	 * The method buildHouseStreet is responsible for drawing of the whole street with houses and trees.
	 */
	private void buildHouseStreet() {
		determineXOfHouses();
		determineYOfHouses();

		for (int i = 0; i < houses.length; i++) {
			houses[i] = new House(houseXs[i], houseYs[i], HOUSE_WIDTHS[i], HOUSE_HEIGHTS[i], HOUSE_COLORS[i],
					ROOF_COLORS[i]);
		}

	}

	/**
	 * Aufloesung der x, y-Position, an der Mausbutton betaetigt wurde.
	 * 
	 * Umsetzung der Methode
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 * 
	 * @param e Maus-Ereignis, das ausgeloest wurde
	 */
	public void mouseClicked(MouseEvent e) {
		int x, y;

		x = e.getX(); // x-Koordinate, an der Mausereignis stattgefunden hat
		y = e.getY(); // y-Koordinate, an der Mausereignis stattgefunden hat

		// hier sollte dann der Maus-Event entsprechend verarbeitet werden
		sun.switchTime(x, y);

		for (int i = 0; i < houses.length; i++) {
			if (houses[i] != null) {
				houses[i].switchLight(x, y);
			}

		}

		// nach jeder Veraenderung soll der Graphik-Kontext neu gezeichnet werden
		repaint();
	}

	/**
	 * Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {
		// diese Methode bleibt einfach leer
	}

	/**
	 * Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e) {
		// diese Methode bleibt einfach leer
	}

	/**
	 * Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
		// diese Methode bleibt einfach leer
	}

	/**
	 * Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
		// diese Methode bleibt einfach leer
	}
}
