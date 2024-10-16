package pandemic.carte;

import pandemic.Maladie;
import pandemic.Game;
import pandemic.Ville;

public class Carte {
	protected Ville ville;
	protected Maladie maladie;
	protected Game jeu;
	
	/** Constructeur de Carte
	 * @param ville La ville liée
	 * @param maladie La maladie liée
	 */
	public Carte(Ville ville, Maladie maladie, Game jeu) {
		this.ville=ville;
		this.maladie=maladie;
		this.jeu=jeu;
	}
	
	/** Renvoie la ville affecter à l'instance
	 * @return la ville affecter à l'instance
	 */
	public Ville getVille() {
		return this.ville;
	}
	
	/** Renvoie la maladie affecter à l'instance
	 * @return la maladie affecter à l'instance
	 */
	public Maladie getMaladie() {
		return this.maladie;
	}
	
	/**
	 * Renvoie le jeu dont fait partie la carte
	 * @return Le jeu dont fait partie la carte
	 */
	public Game getJeu() {
		return this.jeu;
	}

	/**
	 * Renvoie une chaîne de caractères représentant une carte
	 * @return Une chaîne de la forme "(Ville / Maladie)", où Ville représente la ville associée à la carte, et Maladie la maladie associée à la carte
	 */
	public String toString(){
		return "("+this.ville+" / "+this.maladie.getName()+")";
	}
}
