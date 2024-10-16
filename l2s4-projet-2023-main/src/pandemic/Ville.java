package pandemic;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Ville {

	protected String nom;
	protected Map<Maladie, Integer> maladie;
	protected ArrayList<Ville> villeVois;
	protected boolean possedeStation;
	public static final int NIVFOYER = 3;

	/**
	 * Constructeur de la classe Ville
	 * @param nom Le nom de la ville
	 */
	public Ville(String nom) {
		this.nom = nom;
		this.maladie = new HashMap<Maladie, Integer>();
		this.villeVois = new ArrayList<Ville>();
		this.possedeStation = false;
	}
	
	
	/** Renvoie le nom de l'instance
	 * 
	 * @return le nom de l'instance
	 */
	public String getNom() {
		return this.nom;
	}
	
	/** Renvoie une liste des Villhies voisines de l'instance
	 * 
	 * @return la liste des Villes voisines de l'instance
	 */
	public List<Ville> getVoisins() {
		return this.villeVois;
	}
	
	
	/** Détermine si une ville est voisine de l'instance
	 * 
	 * @param ville la ville qu'on recheche
	 * @return <code>true</code> si la ville est voisine de l'instance
	 * 		   <code>false</code> sinon 
	 */
	public boolean estVoisin(Ville ville) {
		boolean trouver = false;
		int i = 0;
		while (i < this.villeVois.size() && !trouver){
			if (this.villeVois.get(i) == ville) {
				trouver = true;
			}
			i++;
		}
		return trouver;
	}
	
	/** Renvoie les Maladies est leurs niveaux sous forme de Map
	 * 
	 * @return une Map des Maladies et leurs niveaux
	 */
	public List<Maladie> getMaladies() {
		ArrayList<Maladie> res = new ArrayList<Maladie>();
		for (Maladie maladie : this.maladie.keySet()) {
			if (this.getNivMaladie(maladie)>0) {
				res.add(maladie);
			}
		}
		return res;
	}
	
	/** Détermine le niveau d'une Maladie
	 * 
	 * @param maladie la Maladie dont on veut le niveau 
	 * @return le niveau de la Maladie
	 */
	public int getNivMaladie(Maladie maladie){
		if (this.maladie.containsKey(maladie)) {
			return this.maladie.get(maladie);
		}
		this.maladie.put(maladie,0);
		return 0;
	}
	
	/**
	 * Détermine si la ville possède une station de recherches ou non.
	 * @return true si la ville pohissède une station, false sinon.
	 */
	public boolean aUneStation() {
		return this.possedeStation;
	}
	
	/** Ajoute a l'instance un ville comme voisin
	 * @param voisin La ville à ajouter
	 */
	public void ajouteVoisin(Ville voisin) {
		this.villeVois.add(voisin);
	}
	
	/**
	 * Ajoute une station de recherches à la ville.
	 */
	public void ajouteStation() {
		this.possedeStation=true;
	}
	
	/**
	 * Retire la station de recherches de la ville.
	 */
	public void enleveStation() {
		this.possedeStation=false;
	}
	
	/**
	 * Ajoute une maladie à la liste des maladies si elle n'y est pas, augmente son niveau de 1 sinon.
	 * @param maladie La maladie à ajouter.
	 */
	public void ajouteMaladie(Maladie maladie) {
		if (this.maladie.containsKey(maladie) && !(this.estFoyer(maladie))) {
			this.maladie.replace(maladie, this.maladie.get(maladie)+1);
		}
		else if (!this.maladie.containsKey(maladie)) {
			this.maladie.put(maladie, 1);
		}
	}
	
	/**
	 * Retire 1 au niveau de maladie (on suppose que la maladie fait partie de la liste et que niveau est positif strictement).
	 * @param maladie La maladie concernée.
	 * @throws MaladieNonTrouveeException Renvoie une exception si la maladie passée en paramètre ne fait pas partie de la liste de maladies ou est au niveau 0.
	 */
	public void enleveMaladie(Maladie maladie) throws MaladieNonTrouveeException{
		if (!this.getMaladies().contains(maladie)) {
			throw new MaladieNonTrouveeException("La maladie ne fait pas partie des maladies de la ville.");
		}
		else if (this.getNivMaladie(maladie)==0) {
			throw new MaladieNonTrouveeException("Le niveau de la maladie est déjà à 0");
		}
		else {
			this.maladie.replace(maladie, this.maladie.get(maladie)-1);
		}
	}
	
	/**
	 * Détermine si la ville est un foyer d'infection ou non.
	 * @return si la ville est un foyer
	 */
	public boolean estFoyer() {
		for (int value:this.maladie.values()) {
			if (value>=NIVFOYER) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Détermine si la ville est un foyer  d'infection ou non par rapport à une maladie.
	 * 
	 * @param maladie La maladie a 
	 * @return si la ville est un foyer
	 */
	public boolean estFoyer(Maladie maladie) {
		return this.getNivMaladie(maladie) >= NIVFOYER;
	}
	
	/**
	 * Renvoie une chaîne de caractères représentant la ville
	 * @return Le nom de la ville
	 */
	public String toString() {
		return this.nom;
	}

	/** Renvoie une chaîne de caractères contenant toutes les maladies et leur niveau de la ville
	 * 
	 * @return une chaîne de caractères contenant toutes les maladies et leur niveau de la ville
	 */
	public String toutLesMaladies() {
		String res = this.getNom() + ", les maladies sont : ";
		for (Maladie m : this.maladie.keySet()) {
			res += m.getName() + ":" + this.getNivMaladie(m) + ", ";
		}
		return res;
	}
	
}
