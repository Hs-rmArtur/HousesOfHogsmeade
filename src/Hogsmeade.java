import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/** 
 * Basis-Panel stellt Grundfunktionen fuer den Aufbau interaktiver Anwendungen zur
 * Verfuegung.
 *  
 * Alle Mausereignisse koennen in einzelnen Methoden verarbeitet werden. 
 *  
 * @author Joerg Berdux
 * @version 1.0
 */
public class Hogsmeade extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private int hogsMeadSizeX;
	private int hogsMeadSizeY;
	
	Tree trees[] = new Tree[3];
	
	
	

	/**
	 * Initialisierung des Panels und setzen des MouseListerns
	 * fuer die Verwendung von Maus-Ereignissen
	 */
	public Hogsmeade(int hogsMeadSizeX, int hogsMeadSizeY){
		
		this.hogsMeadSizeX = hogsMeadSizeX;
		this.hogsMeadSizeY = hogsMeadSizeY;
		
		
		/* registriert Panel als MouseListener, so dass die jeweilige spezialisierte 
		 * Methode aufgerufen wird, wenn ein Mausereignis innerhalb des Panels ausgeloest 
		 * wird
		 */
		this.addMouseListener(this);
		
		// Initialisiere Haeuser, Baeume, Sonne ...
		
		buildTrees();
		
		
	}
	
	/** 
	 * Zeichnen der Strasse.
	 * 
	 * Umsetzung der Methode
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 * 
	 * @param g Graphik-Kontext, auf dem die Landschaft gezeichnet wird
	 */
	public void paint(Graphics g){
		super.paint(g);
		
		// Beispiel fuer das Zeichnen des Himmels
		g.setColor(new Color(50, 100, 200));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		//Zeichnen der Straße
		
		g.setColor(Color.GRAY);
		g.fillRect(0, hogsMeadSizeY, hogsMeadSizeX , 100);
		
		//hier wird alles gezeichnet ...
		
		for (int i = 0; i < trees.length; i++) {
			if(trees[i] != null) {
				trees[i].draw(g);
			}
	
		}
	

	}
	
	private void buildTrees() {
		trees[0] = new Tree(100, 320, 50, 100);
		trees[1] = new Tree(200, 320, 30, 100);
		trees[2] = new Tree(300, 320, 100, 100);
	}
	
	
	
	/** 
	 * Aufloesung der x, y-Position, an der Mausbutton betaetigt wurde.
	 * 
	 * Umsetzung der Methode
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 * 
	 * @param e Maus-Ereignis, das ausgeloest wurde 
	 */
	public void mouseClicked(MouseEvent e){
		int x, y;
		
		x = e.getX(); // x-Koordinate, an der Mausereignis stattgefunden hat
		y = e.getY(); // y-Koordinate, an der Mausereignis stattgefunden hat
		
		// hier sollte dann der Maus-Event entsprechend verarbeitet werden
		
		
		// nach jeder Veraenderung soll der Graphik-Kontext neu gezeichnet werden
		repaint();
	}
	
	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e){
		// diese Methode bleibt einfach leer
	}
	
	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e){
		// diese Methode bleibt einfach leer
	}
	
	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e){
		// diese Methode bleibt einfach leer
	}

	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e){
		// diese Methode bleibt einfach leer
	}
}
