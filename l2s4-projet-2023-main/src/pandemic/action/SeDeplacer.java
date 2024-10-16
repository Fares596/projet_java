package pandemic.action;
import pandemic.role.*;
import listchooser.*;
import pandemic.Ville;
import pandemic.Board;

/**
 * Classe représentant l'action de se déplacer héritant du type Action
 */
public class SeDeplacer extends Action {
	protected boolean estGlobeTrotter;
	protected Ville villeDest;
	
	/**
	 * Constructeur de la classe
	 * @param joueur Le joueur qui exécute l'action
	 */
	public SeDeplacer(Joueur joueur) {
		super(joueur);
		if (super.joueur instanceof GlobeTrotter) {
			this.estGlobeTrotter=true;
		}
		else {
			this.estGlobeTrotter=false;
		}
		this.villeDest=null;
	}

	/**
	 * Accesseur de la ville de destination du joueur
	 * @return
	 */
	public Ville getVilleDest() {
		return this.villeDest;
	}
	
	/**
	 * Détermine si le joueur est un globetrotter
	 * @return true si le joueur est un globetrotter, false sinon
	 */
	public boolean getEstGlobeTrotter() {
		return this.estGlobeTrotter;
	}
	
	/**
	 * Choisit une ville de destination
	 */
	private void choisirVille() {
		ListChooser<Ville> chooser;
		if (this.joueur.estBot()) {
			chooser=new RandomListChooser<Ville>();
		}
		else {
			chooser=new InteractiveListChooser<Ville>();
		}
		if (this.joueur instanceof GlobeTrotter) {
			this.villeDest=chooser.choose("Choisir une ville", Board.villes);
		}
		else {
			this.villeDest= chooser.choose("Choisir une ville:",this.joueur.getVille().getVoisins());
		}
	}
	
	/**
	 * Détermine si l'action est possible
	 * @return true si l'action est possible, false sinon
	 */
	@Override
	public boolean estPossible(){
		return this.joueur.getActionsRestantes()>0;
	}

	/**
	 * Exécute l'action de se déplacer
	 */
	@Override
	public void executer() {
		this.choisirVille();
		if (this.estPossible()) {
			this.joueur.setVille(this.villeDest);
			System.out.println(this.joueur.getName()+" se déplace dans "+this.joueur.getVille());
		}
		this.joueur.setActionsRestantes(this.joueur.getActionsRestantes()-1);		
	}
	
	/**
	 * Renvoie une chaîne de caractères représentant l'action de se déplacer
	 * @return La chaîne "Se Deplacer"
	 */
	public String toString() {
		return "Se Deplacer";
	}
	
}
