package pandemic.action;

import pandemic.role.Joueur;
import pandemic.MaladieNonTrouveeException;

/**
 * Classe représentant l'action de passer son tour héritant du type Action
 */
public class PasserTour extends Action {

	/**
	 * Constructeur de la classe
	 * @param joueur Le joueur qui passe son tour
	 */
	public PasserTour(Joueur joueur) {
		super(joueur);
	}
	
	/**
	 * Détermine si l'action est possible
	 * @return true car il est toujours possible de passer son tour
	 */
	@Override
	public boolean estPossible() {
		return true;
	}
	
	/**
	 * Exécute l'action de passer son tour
	 */
	@Override
	public void executer() throws MaladieNonTrouveeException {
		// TODO Auto-generated method stub
		super.joueur.setActionsRestantes(0);
	}

	/**
	 * Renvoie une chaîne de caractères représentant l'action de passer son tour
	 * @return la chaîne "Passer son Tour"
	 */
	public String toString() {
		return "Passe son Tour";
	}
	
	
}
