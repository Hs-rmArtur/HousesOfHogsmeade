import javax.swing.JFrame;

/**
 * Starter der Hogsmaede-Applikation.
 * 
 * Applikation wird in einem Standard-Fenster der Groesse 700 x 500 angezeigt.
 *
 * @author berdux
 *
 */
public class HogsmeadeApp {
	/**
	 * Starten der Applikation und Anzeige des Fensters
	 * 
	 * @param args wird nicht interpretiert
	 */
	public static void main(String[] args) {
		
		int frameSizeX = 700;
		int frameSizeY = 500;
		// Initialisierung des Frames
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(frameSizeX, frameSizeY);
        
        /* Instanziierung der eigentlichen Anzeige, 
         * die in in der Klasse Hogsmaede definiert ist.
         * Hogsmaede ist als JPanel eine Darstellungsflaeche,
         * in der die Landschaft gezeichnet wird.
         */
		Hogsmeade myDisplay = new Hogsmeade(frameSizeX, frameSizeY);
        jFrame.add(myDisplay);
        jFrame.setVisible(true);
	}
}
