package pandemic;
import java.util.*;

public class Board {
	public static ArrayList<Ville> villes;
	protected ArrayList<Ville> villesDejaVisiter;
	
	/**
	 * Constructeur de la classe Board
	 */
	public Board() {
		Board.villes = new ArrayList<Ville>();
		this.villesDejaVisiter = new ArrayList<Ville>();
	}
	
	/**Renvoie les villes du plateau de jeu
	 * 
	 * @return la liste des villes du plateau de jeu
	 */
	public ArrayList<Ville> getVilles(){
		return Board.villes;
	}
	
	/**Renvoie le nombre de foyer de contamination actuellemnt sur le plateau de jeu
	 * 
	 * @return l'entier correspondant au nombre de foyers sur le plateau
	 */
	public int getNbFoyers() {
		int nbFoyers = 0;
		for(int i=0; i<Board.villes.size();i++) {
			if(Board.villes.get(i).estFoyer()) {
				nbFoyers = nbFoyers + 1;
			}
		}
		return nbFoyers;
	}
	
	/**Ajoute une ville au plateau de jeu
	 * 
	 * @param ville la ville a ajouter au plateau
	 */
	public void addVille(Ville ville) {
		Board.villes.add(ville);
	}
	
	/** Infect une ville avec une maladie
	 * 
	 * @param ville La ville infecté
	 * @param maladie La maladie a propager
	 * @throws FinDePartieException S'il n'y a plus de cube de la maladie
	 */
	public void infection(Ville ville,Maladie maladie) throws FinDePartieException {		
		if (maladie.getCubes() <= 0) {
			throw new FinDePartieException(maladie.toString() + " n'a pas de cube");
		}
		if (!this.dejaVisiter(ville)) {
			
			this.villesDejaVisiter.add(ville);
			
	    	System.out.println("  Infection de " + ville.getNom() + " par la maladie " + maladie.getName());
			
	    	if (ville.estFoyer(maladie)) {
				System.out.println(" Contamination des villes voisine de " + ville.getNom());
				this.propagation(ville,maladie);
			}else {
				ville.ajouteMaladie(maladie);
				maladie.retireCubeStock();
			}
		}
		this.villesDejaVisiter = new ArrayList<Ville>();
	}
	
	
	/** Propage le maladie aux voisin
	 * 
	 * @param ville La ville de départ
	 * @param maladie LA maladie a propage
	 */
	public void propagation(Ville ville, Maladie maladie) {
		for (Ville voisin : ville.getVoisins()) {
			if (!this.dejaVisiter(voisin)) {
				System.out.println("  Infection de " + voisin.getNom() + " par la maladie " + maladie.getName());
				this.villesDejaVisiter.add(voisin);
				if (voisin.estFoyer()) {
					System.out.println(" Contamination des villes voisine de " + voisin.getNom());
					this.propagation(voisin, maladie);
				} else {					
					voisin.ajouteMaladie(maladie);
					maladie.retireCubeStock();
				}
			}
		}
		
	}
	
	/** Renvoie vrai si la ville as déjà été visitée
	 * @param ville La ville en question
	 * @return vrai si la ville as déjà été visitée, faux sinon
	 */
	public boolean dejaVisiter(Ville ville) {
		for (Ville villeDejaVisiter : this.villesDejaVisiter) {
			if (ville == villeDejaVisiter) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Représente les villes et leurs maladies
	 * @return Une chaîne de caractères décrivant les villes et leurs maladies
	 */
	public String toString() {
		String res = "";
		String tmp = "";
		for (Ville ville : villes) {
			boolean asMaladie = false;
			
			tmp += ville.getNom() + ":\n";
			for (Maladie maladie : ville.getMaladies()) {
				asMaladie = asMaladie || (ville.getNivMaladie(maladie)>0);
				tmp += "\t -> " + maladie.getName() + " = " + ville.getNivMaladie(maladie) + "\n";
			}
			if (asMaladie) {
				res += tmp;
			}
			tmp = "";
		}
		
		return res;
	}
	
}
