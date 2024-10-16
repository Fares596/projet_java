package pandemic.carte;
import pandemic.*;

/**
 *	Classe représentant les cartes infections héritant du type Carte
 */
public class CarteInfection extends Carte{
	
	protected Ville ville;
	protected Maladie maladie;
	protected Game jeu;
	
	/** Constructeur de CarteInfection
	 * @param ville La ville liée
	 * @param maladie La maladie liée
	 */
	public CarteInfection(Ville ville, Maladie maladie,Game jeu) {
		super(ville,maladie,jeu);
	}
	
}
