/*
 * AUTEURS : DANIEL KESSLER & YASSINE BELADEL
 * FICHIER : Classe Bdd TP2
 * COURS : IFT 1176
 *
 * Cette classe correspond a une Base de Donnees (BDD) pour les maneges et les parcs.
 * La classe contient deux maps.
 * La premiere map (parcs) associe le nom d.un parc a la liste de maneges qui lui sont associes.
 * La deuxieme map associe un manege a un TreeSet de parcs qui contient ce manege.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Bdd {
	// Map qui enregistre les parcs avec une liste de ses manèges
	private Map<String, List<Manege>> parcs;

	// Map qui fait un lien entre un manege et un TreeSet de parcs ou se trouve ce
	// meme manege.
	private Map<Manege, TreeSet<String>> manegeToParcs;

	// Constructeur qui initialise les deux Maps
	public Bdd() {
		parcs = new LinkedHashMap<>();
		manegeToParcs = new HashMap<>();
	}

	// Fonction pour ajouter un manege à un parc dans les deux Maps
	// Ajout du manege dans le parc - 1ere Map
	public void addManege(Manege manege, String parc) {
		parcs.putIfAbsent(parc, new ArrayList<>());
		parcs.get(parc).add(manege);

		// Ajouter le nom du parc au TreeSet associe avec le manege - 2eme map
		manegeToParcs.putIfAbsent(manege, new TreeSet<>());
		manegeToParcs.get(manege).add(parc);
	}

	// Fonction qui retourne la liste des manèges dans un parc donné
	public List<Manege> listeDuParc(String parc) {
		List<Manege> manegeList = parcs.get(parc);
		if (manegeList == null) {
			return Collections.emptyList();
		} else {
			return manegeList;
		}
	}

	// Fonction pour avoir la liste des maneges d'un parc voulu
	public Set<String> emplacementsManege(Manege manege) {
		Set<String> emplacements = new HashSet<>();
		for (Map.Entry<String, List<Manege>> entry : parcs.entrySet()) {
			for (Manege manege2 : entry.getValue()) {
				if (manege2.equals(manege)) {
					emplacements.add(entry.getKey());
				}
			}
		}
		return emplacements;
	}

	// Methode "getter" pour obtenir un manege voulu
	public Manege getManege(Manege manege) {
		for (List<Manege> manegeList : parcs.values()) {
			for (Manege manege2 : manegeList) {
				if (manege2.equals(manege)) {
					return manege2;
				}
			}
		}

		// Iteration des TreeSets de parcs pour chaque manege
		for (TreeSet<String> manegeList : manegeToParcs.values()) {
			for (String manegex : manegeList) {
				if (manegex.equals(manege.getNom())) {

					Manege mTemp = null;

					for (Manege manege3 : manegeToParcs.keySet()) {
						if (manege3.equals(manege)) {
							mTemp = manege;
						}
					}

					return mTemp;

				}
			}
		}

		return null;
	}

	/*
	 * Fonction pour avoir une Map de tout manege ainsi que sa frequence (combien de
	 * fois le manege apparait)
	 */
	public Map<Manege, Integer> frequence() {
		Map<Manege, Integer> freqMap = new HashMap<>();
		for (List<Manege> manegeList : parcs.values()) {
			for (Manege manege : manegeList) {
				freqMap.put(manege, freqMap.getOrDefault(manege, 0) + 1);
			}
		}

		return freqMap;
	}

	/*
	 * Surcharge de methode toString pour afficher l'information des maneges et des
	 * parcs
	 */
	@Override
	public String toString() {
		String manegeInfo = "";

		for (Map.Entry<String, List<Manege>> entry : parcs.entrySet()) {
			manegeInfo += "Parc : " + entry.getKey() + "\n";
			for (Manege manege : entry.getValue()) {
				manegeInfo += "\tManege : " + manege.getNom() + ", Hauteur : " + manege.getHauteur() + ", Vitesse : "
						+ manege.getVitesse() + "\n";
			}
		}

		return manegeInfo;
	}

}
