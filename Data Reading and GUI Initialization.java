/*
 * AUTEURS : DANIEL KESSLER & YASSINE BELADEL
 * FICHIER : Classe MainClass TP2
 * COURS : IFT 1176
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import javax.swing.JFileChooser;
import javax.swing.JComboBox;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass {
	private static Bdd donnees;

	public static void main(String[] args) {

		// Création des JComboBox pour les options de sélection des manèges et des parcs
		JComboBox<String> manegeComboBox = new JComboBox<>();
		JComboBox<String> parcComboBox = new JComboBox<>();

		// Lecture des données à partir d'un fichier pour initialiser l'objet Bdd
		Bdd donnees = lireFichier(manegeComboBox, parcComboBox);

		// Exécution de l'interface GUI dans l'Event Dispatch Thread (EDT)
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				// Création des JComboBox pour les options de sélection des manèges et des parcs
				JComboBox<String> manegeComboBox = new JComboBox<>();
				JComboBox<String> parcComboBox = new JComboBox<>();

				// Lecture des données à partir d'un fichier pour initialiser l'objet Bdd
				Bdd bdd = lireFichier(manegeComboBox, parcComboBox);

				// Création de l'interface GUI avec les JComboBox et l'objet Bdd
				GUI gui = new GUI(bdd, manegeComboBox, parcComboBox);

				// ActionListener pour la JComboBox de sélection des manèges
				manegeComboBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						// Récupération du manège sélectionné
						String choix = (String) manegeComboBox.getSelectedItem();

						// Création de l'interface ManegeInfo avec le manège sélectionné et l'objet Bdd
						ManegeInfo manegeInfo = new ManegeInfo(gui, bdd.getManege(new Manege(choix)), bdd);
					}
				});

				// ActionListener pour la JComboBox de sélection des parcs
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

	// Fonction pour lire les données à partir d'un fichier et remplir les JComboBox
	public static Bdd lireFichier(JComboBox<String> manegeComboBox, JComboBox<String> parcComboBox) {
		FileReader fr = null;
		boolean existeFile = true;
		boolean finFichier = false;
		String nomFile;
		String nom, parc;
		double hauteur, vitesse;
		String[] valeurs;
		JFileChooser choixFichier;

		HashSet<String> maneges = new HashSet<String>();
		HashSet<String> parcs = new HashSet<String>();

		// Création d'un nouvel objet Bdd
		Bdd donnees = new Bdd();

		// Choix du fichier à partir d'une boîte de dialogue
		choixFichier = new JFileChooser();
		if (choixFichier.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			nomFile = choixFichier.getSelectedFile().getName();
			File f = choixFichier.getCurrentDirectory();
			nomFile = f.getAbsolutePath() + File.separator + nomFile;
			try {
				fr = new FileReader(nomFile);
			} catch (java.io.FileNotFoundException e) {
				System.out.println("Probleme d'ouvrir le fichier " + nomFile);
				existeFile = false;
			}

			if (existeFile) {
				try {
					BufferedReader entree = new BufferedReader(fr);

					while (!finFichier) {
						String ligne = entree.readLine();
						if (ligne != null) {
							valeurs = ligne.split(";");
							nom = valeurs[0];
							hauteur = Double.parseDouble(valeurs[1].trim());
							vitesse = Double.parseDouble(valeurs[2].trim());
							parc = valeurs[3].trim();
							maneges.add(nom);
							parcs.add(parc);

							// Ajout du manège et du parc à l'objet Bdd
							donnees.addManege(new Manege(nom, hauteur, vitesse), parc);
						} else
							finFichier = true;
					}
					entree.close();
				} catch (IOException e) {
					System.out.println("Problème lors de la lecture du fichier");
				}
			}

			// Remplissage de la JComboBox des manèges
			for (String tempoManege : maneges) {
				manegeComboBox.addItem(tempoManege);
			}

			// Remplissage de la JComboBox des parcs
			for (String tempoParc : parcs) {
				parcComboBox.addItem(tempoParc);
			}
		}

		return donnees;
	}

	public static Bdd getDonnees() {
		return donnees;
	}
}
