package pandemic.role;
import pandemic.Ville;
import pandemic.Game;

/**
 * Classe représentant le rôle de globetrotter héritant du type joueur
 */
public class GlobeTrotter extends Joueur {
	/**
	 * Constructeur de la classe
	 * @param name Le nom du joueur
	 * @param ville Ville dans laquelle se trouve le joueur
	 * @param estBoot true si le joueur est un ordinateur, false s'il s'agit d'un humain
	 * @param partie La partie en cours
	 */
	public GlobeTrotter(String name, Ville ville, boolean estBoot, Game partie) {
		super(name,ville, estBoot,partie);
	}
	
	/**
	 * Renvoie une chaîne de caractères représentant le rôle de globetrotter
	 * @return le mot "GlobeTrotter"
	 */
	public String toString() {
		return "GlobeTrotter";
	}
}
