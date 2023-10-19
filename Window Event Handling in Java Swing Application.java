import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* La classe Fentre herite de la classe WindowAdapter, qui est une
 * classe qui recoit les evenements de la fenetre. 
 */
public class Fenetre extends WindowAdapter {
	// La fenetre agit avec le GUI
	private GUI gui;

	// Constructeur avec GUI comme objet
	public Fenetre(GUI gui) {
		this.gui = gui;
	}

	/*
	 * Surcharge de la methode windowClosing, la methode agit lorsque la fenetre se
	 * ferme. Le GUI redevient visible quand la fenetre est fermee.
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		gui.setVisible(true);
	}
}
