import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ManegeInfo extends JFrame {

	public ManegeInfo(GUI gui, Manege manege, Bdd bdd) {
		// L'interface GUI principale devient invisible
		gui.setVisible(false);

		// Taille de la fenetre
		setSize(600, 360);

		// Getter du contenu de la fenetre
		Container container = getContentPane();
		container.setLayout(new FlowLayout());

		// Definition du titre de la fenetre : nom du manege
		setTitle(manege.getNom());

		// JLabel contenant les inormations d'un manege
		JLabel jLabel = new JLabel(manege.toString());
		setLayout(new BorderLayout());
		// Ce JLabel sera positione en haut de la fenetre (NORTH)
		add(jLabel, BorderLayout.NORTH);

		JPanel peanneau = new JPanel();
		JLabel jLabel2 = new JLabel("Present dans les parcs suivants : ");
		peanneau.add(jLabel2);
		container.add(peanneau);

		// Liste de String ayant le nom des parcs ou se trouve le manege voulu
		String[] data = bdd.emplacementsManege(manege).toArray(new String[0]);
		JList<String> liste = new JList<String>(data);

		// Permet defilement de la liste si la fenetre est trop petite pour elle
		JScrollPane defil = new JScrollPane(liste);
		peanneau.add(defil);

		// WindowListener pour les actions de la fenetre
		addWindowListener(new Fenetre(gui));

		pack();

		// Afichage de la fenetre
		setVisible(true);

	}
}
