package pandemic.carte;
import pandemic.*;

/**
 * Classe représentant les cartes joueurs du paquet de cartes joueurs héritant du type Carte et implémentant l'interface ICarteJoueur
 */
public class CarteJoueur extends Carte implements ICarteJoueur {
	protected Ville ville;
	protected Maladie maladie;
	protected Game jeu;
	
	/** Constructeur de CarteJoueur
	 * @param ville La ville liée
	 * @param maladie La maladie liée
	 */
	public CarteJoueur(Ville ville, Maladie maladie, Game jeu) {
		super(ville,maladie,jeu);
	}
	
	/**
	 * Effet de la carte: rien, les cartes joueurs sont utilisées dans les actions 
	 */
	public void effet() {
	}
}
