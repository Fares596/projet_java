package pandemic.action;
import pandemic.*;
import pandemic.role.*;
import java.util.List;

import listchooser.*;

/**
 * Classe représentant l'action de traiter une maladie héritant du type Action
 */
public class TraiterMaladie extends Action {
	
	protected boolean estMedecin;
	protected Maladie maladieATraiter;
	/**
	 * Constructeur de la classe
	 * @param joueur Le joueur qui exécute l'action
	 */
	public TraiterMaladie(Joueur joueur) {
		super(joueur);
		if (super.joueur instanceof Medecin) {
			this.estMedecin=true;
		}
		else {
			this.estMedecin=false;
		}
		this.maladieATraiter=null;
	}
	
	/**
	 * Fait un choix de maladie à traiter
	 * @param maladies La liste des maladies qu'il est possible de traiter
	 */
	public void choisirMaladie(List<Maladie> maladies) {
		ListChooser<Maladie> chooser;
		if (this.joueur.estBot()){
			chooser=new RandomListChooser<Maladie>();
		}
		else{
			chooser=new InteractiveListChooser<Maladie>();
		}
		this.maladieATraiter=chooser.choose("Choisir une maladie à traiter:",maladies);
	}
	
	/**
	 * Détermine si l'action est possible ou non
	 * @return true si l'action est possible, false sinon
	 */
	@Override
	public boolean estPossible() {
		List<Maladie> maladies = super.joueur.getVille().getMaladies();
		return (!(maladies.isEmpty()))&&(super.joueur.getActionsRestantes()>0);
	}
	
	
	/**
	 * Exécute l'action de traiter une maladie
	 */
	@Override
	public void executer() throws MaladieNonTrouveeException{
		if (this.estPossible()) {
			this.choisirMaladie(super.joueur.getVille().getMaladies());
			if ((this.estMedecin)||(this.maladieATraiter.estGuerie())) {
				while (super.joueur.getVille().getNivMaladie(maladieATraiter)>0) {
					super.joueur.getVille().enleveMaladie(maladieATraiter);
				}
			}
			else {
				super.joueur.getVille().enleveMaladie(maladieATraiter);
			}
		}
		this.joueur.setActionsRestantes(this.joueur.getActionsRestantes()-1);
	}

	/**
	 * Renvoie une chaîne de caractères représentant l'action de traiter une maladie
	 * @return La chaîne "Traiter une maladie"
	 */
	public String toString() {
		return "Traiter une Maladie";
	}
	
	
}
