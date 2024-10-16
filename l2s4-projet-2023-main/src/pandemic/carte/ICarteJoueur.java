package pandemic.carte;

import pandemic.FinDePartieException;

/**
 *	Interface représentant le paquet de cartes joueurs
 */
public interface ICarteJoueur {
	/**
	 * Effet de la carte joueur
	 * @throws FinDePartieException Exception lancée si une carte provoque la fin de partie
	 */
	public void effet() throws FinDePartieException;
}
