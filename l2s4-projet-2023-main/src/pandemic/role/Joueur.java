package pandemic.role;

import pandemic.*;
import pandemic.action.*;
import pandemic.carte.*;
import java.util.ArrayList;
import java.util.List;

import listchooser.*;

/**
 * Classe représentant un joueur
 */
public class Joueur {
	
	public static final int MAX_ACTIONS=4;
	public static final int CARTES_NECESSAIRES_POUR_REMEDE=5;
	protected String name;
	protected List< CarteJoueur > main;
	protected Ville ville;
	protected boolean sEstDeplace;
	protected int nbActionsRestantes;
	protected ListChooser<Action> listChooser;
	protected boolean estBot;
	protected Game jeu;
	
	/** Constructeur de Joueur 
	 * 
	 * &
	 * @param name Le nom du joueur
	 * @param ville La ville de départ
	 */
	public Joueur(String name , Ville ville, boolean estBoot, Game partie) {
		this.name = name;
		this.ville = ville;
		this.main = new ArrayList<CarteJoueur>();
		this.sEstDeplace=false;
		this.nbActionsRestantes=MAX_ACTIONS;
		this.estBot=estBoot;
		if (estBoot) {
			listChooser = new RandomListChooser<Action>();
		} else {
			listChooser = new InteractiveListChooser<Action>();
		}
		this.jeu=partie;
	}
	
	public Action choose(String msg, List<Action> list) {
		return listChooser.choose(msg, list);
	}
	
	/** Renvoie les cartes dans sa main
	 * @return les cartes dans sa main
	 */
	public List<CarteJoueur> getMain() {
		return this.main;
	}
	
	/** Ajoute une carte dans sa main
	 * @param c la carte ajoutée
	 */
	public void piocheCarte(ICarteJoueur c) throws FinDePartieException{
		if (c instanceof CarteJoueur) {
			this.main.add((CarteJoueur)c);
		}
		else if (c instanceof CarteEpidemie) {
			c.effet();
			this.defausseCarte(c);
		}
	}
	
	/** Retire une carte de sa main
	 * @param c La carte retirée
	 */
	public void defausseCarte(ICarteJoueur c) {
		this.main.remove(c);
		this.jeu.getPaquetsJoueur().ajouteDefausse(c);
	}
	
	/** Renvoie la ville où se trouve l'instance
	 * @return la ville où se trouve l'instance
	 */
	public Ville getVille() {
		return this.ville;
	}
	
	/** Renvoie le nom de l'instance
	 * @return Le nom
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Modifie la ville dans laquelle se trouve le joueur
	 * @param ville La ville de destination du joueur
	 */
	public void setVille(Ville ville) {
		this.ville=ville;
	}
	
	/**
	 * Modifie le nombre d'actions restantes du joueur
	 * @param niveau Le nouveau nombre d'actions restantes du joueur
	 */
	public void setActionsRestantes(int niveau) {
		if ((0<=niveau)&&(niveau<=MAX_ACTIONS)) {
			this.nbActionsRestantes=niveau;
		}
	}
	
	/**
	 * Accesseur du nombre d'actions restantes du joueur
	 * @return Le nombre d'actions restantes
	 */
	public int getActionsRestantes() {
		return this.nbActionsRestantes;
	}
	
	/**
	 * Décrémente le nombre d'actions restantes de 1 
	 * @throws PlusDActionsPossiblesException Exception lancée si la méthode est appelée alors qu'il n'y a plus d'actions possibles
	 */
	public void retirerUneActionPossible() throws PlusDActionsPossiblesException{
		if (this.nbActionsRestantes<=0) {
			throw new PlusDActionsPossiblesException("Plus d'actions possibles");
		}
		this.nbActionsRestantes--;
	}
	
	/**
	 * Vérifie si le joueur est un ordinateur ou un humain
	 * @return true si le joueur est un ordinateur, false s'il s'agit d'un humain
	 */
	public boolean estBot() {
		return this.estBot;
	}
}
 