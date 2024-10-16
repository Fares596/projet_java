package pandemic.action;
import pandemic.role.*;
import pandemic.*;

/**
 * Classe abstraite représentant les actions possibles pour les joueurs
 */
public abstract class Action {
	protected Joueur joueur;
	
	/**
	 * Le constructeur de la classe
	 * @param joueur Le joueur qui exécute l'action
	 */
	public Action(Joueur joueur) {
		this.joueur=joueur;
	}
	
	/**
	 * Détermine si l'action est possible
	 * @return true si l'action est possible, false sinon
	 */
	public boolean estPossible() {
		return this.joueur.getActionsRestantes()>0;
	}
	
	/**
	 * Méthode abstraite représentant l'exécution de l'action
	 * @throws MaladieNonTrouveeException Exception levée potentiellement quand le programme cherche une maladie et ne la trouve pas
	 */
	public abstract void executer() throws MaladieNonTrouveeException;
}
