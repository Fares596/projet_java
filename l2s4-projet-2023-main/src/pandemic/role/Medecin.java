package pandemic.role;
import pandemic.Ville;
import pandemic.Game;

/**
 * Classe représentant le rôle de médecin héritant du type joueur
 */
public class Medecin extends Joueur {
	
	/**
	 * Le constructeur de la classe
	 * @param name Le nom du joueur
	 * @param ville La ville où se trouve le joueur
	 * @param estBoot true si le joueur est un ordinateur, false s'il s'agit d'un humain
	 * @param partie La partie en cours
	 */
	public Medecin (String name,  Ville ville, boolean estBoot, Game partie) {
		super(name, ville, estBoot, partie);
	}
	
	/**
	 * Renvoie une chaîne de caractères représentant le rôle de médecin
	 * @return Le mot "Médecin"
	 */
	public String toString() {
		return "Medecin";
	}
}