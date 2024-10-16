package pandemic.role;
import pandemic.Ville;
import pandemic.Game;

/**
 * Classe représentant le rôle d'expert héritant du type joueur
 */
public class Expert extends Joueur {
	
	/**
	 * Constructeur de la classe
	 * @param name Le nom du joueur
	 * @param ville La ville où se trouve le joueur
	 * @param estBoot true si le joueur est un ordinateur, false s'il s'agit d'un humain
	 * @param partie La partie en cours
	 */
	public Expert(String name ,Ville ville, boolean estBoot, Game partie) {
		super(name,ville, estBoot, partie);
	}
	
	public String toString() {
		return "Expert";
	}
}
