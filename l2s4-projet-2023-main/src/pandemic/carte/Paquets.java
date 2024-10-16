package pandemic.carte;
import java.util.Stack;

import pandemic.FinDePartieException;

import java.util.Collections;

/**
 * Type paramétré représentant les paquets de cartes infections et joueurs
 */
public class Paquets<T>{
	private Stack<T> pile;
	private Stack<T> defausse;
	
	/**
	 * Le constructeur de la classe
	 */
	public Paquets() {
		this.pile=new Stack<T>();
		this.defausse=new Stack<T>();
	}
	
	/**
	 * Accesseur de la pile de carte représentant le paquet
	 * @return La pile de cartes représentant le paquet
	 */
	public Stack<T> getPile(){
		return this.pile;
	}
	
	/**
	 * Accesseur de la défausse liée au paquet
	 * @return La pile de défausse liée au paquet
	 */
	public Stack<T> getDefausse(){
		return this.defausse;
	}
	
	/**
	 * Retire une carte du paquet
	 * @return La carte retirée du paquet
	 * @throws FinDePartieException Exception lancée si le paquet de cartes est vide
	 */
	public T retirePile() throws FinDePartieException {
		if (this.pileEstVide()) {
			throw new FinDePartieException("Pile est vide");
		}
		return this.pile.pop();
	}
	
	/**
	 * Rajoute une carte dans le paquet
	 * @param carte La carte à rajouter sur le sommet du paquet
	 */
	public void ajoutePile(T carte) {
		this.pile.push(carte);
	}
	
	/**
	 * Retire une carte de la défausse
	 * @return La carte retirée de la défausse
	 */
	public T retireDefausse() {
		return this.defausse.pop();
	}
	
	/**
	 * Ajoute une carte à la pile de défausse
	 * @param carte La carte à ajouter à la défausse
	 */
	public void ajouteDefausse(T carte) {
		this.defausse.push(carte);
	}
	
	/**
	 * Permet de savoir si le paquet de cartes est vide
	 * @return true si la pile est vide, false sinon
	 */
	public boolean pileEstVide() {
		return this.pile.isEmpty();
	}
	
	/**
	 * Permet de savoir si la pile de défausse est vide
	 * @return true si la défausse est vide, false sinon
	 */
	public boolean defausseEstVide() {
		return this.defausse.isEmpty();
	}
	
	/**
	 * Regarde la carte sur le sommet de la pile
	 * @return La carte sur le sommet de la pile, sans la retirer
	 */
	public T sommetPile() {
		return this.pile.peek();
	}
	
	/**
	 * Regarde la carte au sommet de la défausse
	 * @return La carte sur le sommet de la défausse, sans la retirer
	 */
	public T sommetDefausse() {
		return this.defausse.peek();
	}
	
	/**
	 * Rajoute la défausse sur le dessus du paquet après avoir mélangé la défausse
	 */
	public void fusionDefaussePile() {
		this.melangerDefausse();;
		while (!this.defausseEstVide()) {
			this.ajoutePile(this.retireDefausse());
		}
	}
	
	/**
	 * Mélange le paquet de cartes
	 */
	public void melangerPile() {
		Collections.shuffle(this.pile);
	}
	
	/**
	 * Mélange la défausse
	 */
	public void melangerDefausse() {
		Collections.shuffle(this.defausse);
	}
}
