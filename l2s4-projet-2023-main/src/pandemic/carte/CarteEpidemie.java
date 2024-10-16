package pandemic.carte;
import pandemic.*;
/**
 * Classe représentant les cartes épidémies du paquet de cartes joueurs implémentant l'interface ICarteJoueur
 */
public class CarteEpidemie implements ICarteJoueur{
	
	private Game jeu;
	/**
	 * Constructeur de la classe
	 */
	public CarteEpidemie(Game jeu) {
		this.jeu=jeu;
	}
	
	/**
	 * Effet de la carte infection
	 */
	public void effet() throws FinDePartieException{
		this.jeu.augmenteTauxInfection();
		CarteInfection carte=this.jeu.getPaquetsInfection().retirePile();
		carte.getJeu().getBoard().infection(carte.getVille(),carte.getMaladie());
		this.jeu.getPaquetsInfection().fusionDefaussePile();
	}
	
}