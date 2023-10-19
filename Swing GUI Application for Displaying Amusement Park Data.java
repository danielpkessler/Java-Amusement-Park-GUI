import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class GUI extends JFrame {
	private JLabel equipeLabel;
	private JComboBox<String> manegeComboBox;
	private JComboBox<String> parcComboBox;
	private Bdd bdd;

	public GUI(Bdd bdd, JComboBox<String> manegeComboBox, JComboBox<String> parcComboBox) {
		this.bdd = bdd;
		this.manegeComboBox = manegeComboBox;
		this.parcComboBox = parcComboBox;

		// Propriétés de la fenêtre principale
		setTitle("TP2, Choisir quoi afficher");
		setSize(600, 360);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Création de la barre de menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");

		equipeLabel = new JLabel("TP2 de Yassine Beladel et Daniel Kessler");

		// Création de l'option "Afficher la table de fréquence d'un manège" dans le
		// menu
		JMenuItem afficherTableItem = new JMenuItem("Afficher la table de fréquence d'un manège");
		afficherTableItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Construction du message de la table de fréquence
				String message = "Nb\t\tNom du manège\n";
				for (Map.Entry<Manege, Integer> freq : bdd.frequence().entrySet()) {
					message += freq.getValue() + "  :  " + freq.getKey().getNom() + "\n";
				}

				// Affichage de la boîte de dialogue avec la table de fréquence
				JOptionPane.showMessageDialog(null, message);
			}
		});

		// Ajout de l'option "Afficher la table de fréquence d'un manège" dans le menu
		menu.add(afficherTableItem);

		// Création de l'option "Quitter" dans le menu
		JMenuItem quitterItem = new JMenuItem("Quitter");
		quitterItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Fermeture de l'application lorsqu'on sélectionne "Quitter"
				System.exit(0);
			}
		});

		// Ajout de l'option "Quitter" dans le menu
		menu.add(quitterItem);

		// Ajout du menu à la barre de menu
		menuBar.add(menu);
		setJMenuBar(menuBar);

		// Configuration du layout de la fenêtre principale
		setLayout(new BorderLayout());
		add(equipeLabel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel(new FlowLayout());

		JLabel manegeLabel = new JLabel("Manèges :");
		centerPanel.add(manegeLabel);
		centerPanel.add(manegeComboBox);

		JLabel parcLabel = new JLabel("Parcs :");
		centerPanel.add(parcLabel);
		centerPanel.add(parcComboBox);
		add(centerPanel, BorderLayout.CENTER);

		// Création du panneau inférieur contenant le bouton "Quitter"
		JPanel southPanel = new JPanel(new FlowLayout());
		JButton quitterButton = new JButton("Quitter");
		quitterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Fermeture de l'application lorsqu'on clique sur le bouton "Quitter"
				System.exit(0);
			}
		});
		southPanel.add(quitterButton);
		add(southPanel, BorderLayout.SOUTH);

		// Rendre la fenêtre principale visibleç
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				// Création des JComboBox pour les options de sélection des manèges et des parcs
				JComboBox<String> manegeComboBox = new JComboBox<>();
				JComboBox<String> parcComboBox = new JComboBox<>();

				// Lecture des données à partir d'un fichier pour initialiser l'objet Bdd
				Bdd bdd = MainClass.lireFichier(manegeComboBox, parcComboBox);

				// Création de l'interface GUI avec les JComboBox et l'objet Bdd
				GUI gui = new GUI(bdd, manegeComboBox, parcComboBox);

				// Ajout des ActionListener pour les options de sélection des manèges et des
				// parcs
				manegeComboBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						// Récupération du manège sélectionné
						String choix = (String) manegeComboBox.getSelectedItem();

						// Création de l'interface ManegeInfo avec le manège sélectionné et l'objet Bdd
						ManegeInfo manegeInfo = new ManegeInfo(gui, bdd.getManege(new Manege(choix)), bdd);
					}
				});

				parcComboBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						// Récupération du parc sélectionné
						String choix = (String) parcComboBox.getSelectedItem();

						// Création de l'interface ParcInfo avec le parc sélectionné et l'objet Bdd
						ParcInfo parcInfo = new ParcInfo(gui, bdd, choix);
					}
				});
			}
		});
	}
}
