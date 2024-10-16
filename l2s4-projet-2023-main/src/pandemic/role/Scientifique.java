package pandemic.role;

import pandemic.Ville;
import pandemic.Game;

/**
 * Classe représentant le rôle de scientifique héritant du type Joueur
 */
public class Scientifique extends Joueur {
	public static final int CARTES_NECESSAIRES_POUR_REMEDE=4;
	/**
	 * Constructeur de la classe
	 * @param name Le nom du joueur
	 * @param ville La ville où se trouve le joueur
	 * @param estBoot vaut true si le joueur est l'ordinateur, false s'il s'agit d'un humain
	 * @param partie La partie en cours
	 */	
	public Scientifique (String name,  Ville ville, boolean estBoot, Game partie) {
		super(name, ville, estBoot, partie);
	}
	
	/**
	 * Renvoie une chaîne de caractère représentant le rôle de scientifique
	 * @return Le mot "Scientifique"
	 */
	public String toString() {
		return "Scientifique";
	}
}
	