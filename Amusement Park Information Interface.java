import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ParcInfo extends JFrame implements ActionListener {

	// Declaration d'attributs
	private Bdd bdd;
	private String nomParc;
	private JLabel nomManegeLabel;
	private JLabel vitesseManegeLabel;
	private JLabel hauteurManegeLabel;
	private JLabel indiceCourant;
	private JLabel enteteManegeLabel;
	private JLabel enteteVitesseLabel;
	private JLabel enteteHauteurLabel;
	private JButton previousButton;
	private JButton nextButton;
	private JPanel centerPanel;
	private int indiceManege = 0;

	public ParcInfo(GUI gui, Bdd bdd, String nomParc) {

		// Initialisation de la fenetre avec ses attributs
		gui.setVisible(false);
		this.bdd = bdd;
		this.nomParc = nomParc;

		List<Manege> parc = bdd.listeDuParc(nomParc);

		setTitle(nomParc);
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Initialisation des composants
		enteteManegeLabel = new JLabel("Nom du manège");
		enteteManegeLabel.setPreferredSize(new Dimension(100, 50));
		enteteVitesseLabel = new JLabel("Vitesse du manège en mph");
		enteteVitesseLabel.setPreferredSize(new Dimension(200, 50));
		enteteHauteurLabel = new JLabel("Hauteur du manège en pieds");
		enteteHauteurLabel.setPreferredSize(new Dimension(200, 50));

		nomManegeLabel = new JLabel();
		nomManegeLabel.setOpaque(false);
		nomManegeLabel.setBackground(new Color(0, 0, 0, 0));
		nomManegeLabel.setPreferredSize(new Dimension(100, 50));

		vitesseManegeLabel = new JLabel();
		vitesseManegeLabel.setOpaque(false);
		vitesseManegeLabel.setBackground(new Color(0, 0, 0, 0));
		vitesseManegeLabel.setPreferredSize(new Dimension(200, 50));

		hauteurManegeLabel = new JLabel();
		hauteurManegeLabel.setOpaque(false);
		hauteurManegeLabel.setBackground(new Color(0, 0, 0, 0));
		hauteurManegeLabel.setPreferredSize(new Dimension(200, 50));

		// Creation de panel de l'en-tete
		JPanel headerPanel = new JPanel();
		JLabel jLabel = new JLabel("Les manèges du parc " + nomParc);
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		headerPanel.add(jLabel);
		add(headerPanel, BorderLayout.NORTH);

		// Panel central
		centerPanel = new JPanel(new GridLayout(2, 3));
		centerPanel.setBackground(Color.GRAY);
		centerPanel.add(enteteManegeLabel);
		centerPanel.add(enteteVitesseLabel);
		centerPanel.add(enteteHauteurLabel);
		centerPanel.add(nomManegeLabel);
		centerPanel.add(vitesseManegeLabel);
		centerPanel.add(hauteurManegeLabel);

		add(centerPanel, BorderLayout.CENTER);

		previousButton = new JButton("Précédent");
		nextButton = new JButton("Suivant");

		// Ajout des action listeners
		previousButton.addActionListener(this);
		nextButton.addActionListener(this);

		/*
		 * Initialisation du compteur des maneges (pour chaque parc), renvoie egalement
		 * le nombre total de maneges (ex: 1 de 4, 2 de 4, 3 de 4 et 4 de 4)
		 */
		indiceCourant = new JLabel(indiceManege + 1 + " de " + parc.size());

		// Panel du bas
		JPanel bottomPanel = new JPanel();

		previousButton.setEnabled(false);
		bottomPanel.add(previousButton);

		bottomPanel.add(indiceCourant);

		if (parc.size() == 1) {
			nextButton.setEnabled(false);
		}

		bottomPanel.add(nextButton);
		add(bottomPanel, BorderLayout.SOUTH);

		manInfo();

		addWindowListener(new Fenetre(gui));

		pack();
		setVisible(true);

	}

	// Apres avoir clique appuye sur un bouton, le manege suivant est affiche
	@Override
	public void actionPerformed(ActionEvent e) {
		List<Manege> parc = bdd.listeDuParc(nomParc);

		// Mise a jour du compteur apres appuyer sur bouton "precedent"
		if (e.getSource() == previousButton) {
			indiceManege = (indiceManege - 1 + parc.size()) % parc.size();

			// Meme principe pour boutoon "suivant"
		} else if (e.getSource() == nextButton) {
			indiceManege = (indiceManege + 1) % parc.size();
		}

		// Bouton precedent est deactive si l'indice du manegee actuel est 0 (le premier
		// manege)
		if (indiceManege > 0) {
			previousButton.setEnabled(true);
		} else {
			previousButton.setEnabled(false);
		}

		/*
		 * Bouton suicant desactuve si l'indice du manege est egal au nombre de maneges
		 * dans la liste (le dernier manege - parc.size() - 1))
		 */
		if (indiceManege == parc.size() - 1) {
			nextButton.setEnabled(false);
		} else {
			nextButton.setEnabled(true);
		}

		manInfo();
	}

	// On met les informations du manege actuel
	private void manInfo() {
		List<Manege> parc = bdd.listeDuParc(nomParc);
		Manege manege = parc.get(indiceManege);
		nomManegeLabel.setText(manege.getNom());
		vitesseManegeLabel.setText(String.valueOf(manege.getVitesse()));
		hauteurManegeLabel.setText(String.valueOf(manege.getHauteur()));
		indiceCourant.setText(indiceManege + 1 + " de " + parc.size());

		centerPanel.setBackground(Color.GRAY);

		centerPanel.revalidate();
		centerPanel.repaint();
	}
}
