import java.awt.Color;
import java.awt.Graphics;
/**
 * Tree are illustrating a tree by drawing a root and a crown.
 * 
 * @author Fouad Ahsayni, Mykhailo Fakliier, Artur Konkel
 * @version 1.0
 */
public class Tree {
	final static Color TREE_GREEN = new Color(5, 102, 41);
	final static Color TREE_BROWN = new Color(91, 58, 41);

	int x;
	int y;
	int width;
	int height;

	/**
	 * 
	 * @param x setting the x-coordinate of the tree
	 * @param y setting the y-coordinate of the tree
	 * @param width setting the width of the tree
	 * @param height setting the height of the tree
	 */
	public Tree(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Drawing the tree
	 * @param g setting Graphics
	 */
	public void draw(Graphics g) {
		g.setColor(TREE_BROWN);
		g.fillRect(x + width / 4, y, width / 2, height);
		g.setColor(TREE_GREEN);
		g.fillOval(x - 1, y - (height / 2), width, height);

	}

}
