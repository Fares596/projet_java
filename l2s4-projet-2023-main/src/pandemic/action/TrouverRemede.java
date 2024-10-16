package pandemic.action;
import pandemic.role.*;
import listchooser.InteractiveListChooser;
import listchooser.RandomListChooser;
import pandemic.carte.CarteJoueur;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import pandemic.Maladie;

/**
 * Classe représentant l'action de trouver un remède héritant du type Action
 */
public class TrouverRemede extends Action {
	protected boolean estScientifique;
	protected Maladie maladieAGuerir;
	
	/**
	 * Le constructeur de la classe
	 * @param joueur Le joueur qui exécute l'action
	 */
	public TrouverRemede(Joueur joueur) {
		super(joueur);
		if (super.joueur instanceof Scientifique) {
			this.estScientifique=true;
		}
		else {
			this.estScientifique=false;
		}
		maladieAGuerir=null;
	}
	
	/**
	 * Crée la liste des maladies pour lesquelles le joueur peut trouver un remède
	 * @return La liste des maladies pour lesquelles le joueur peut trouver une remède
	 */
	public List<Maladie> getMaladiesGuerissables(){
		Map<Maladie,Integer> maladiesDansMain = new HashMap<Maladie,Integer>();
		List<Maladie> maladiesGuerissables = new ArrayList<Maladie>();
		// On compte pour chaque maladie de la main le nombre de cartes de cette maladie
		for (CarteJoueur carte:this.joueur.getMain()) {
			if (maladiesDansMain.keySet().contains(carte.getMaladie())) {
				maladiesDansMain.put(carte.getMaladie(), maladiesDansMain.get(carte.getMaladie())+1);
			}
			else {
				maladiesDansMain.put(carte.getMaladie(), 1);
			}
		}
		// On regarde pour chaque maladie de la main s'il est possible de trouver un remède selon que le joueur est un scientifique ou non
		if (this.estScientifique) {
			for (Maladie maladie:maladiesDansMain.keySet()) {
				if ((maladiesDansMain.get(maladie)>=pandemic.role.Joueur.CARTES_NECESSAIRES_POUR_REMEDE)&&(!(maladie.estGuerie()))) {
					maladiesGuerissables.add(maladie);
				}
			}
		}
		else {
			for (Maladie maladie:maladiesDansMain.keySet()) {
				if ((maladiesDansMain.get(maladie)>=pandemic.role.Joueur.CARTES_NECESSAIRES_POUR_REMEDE)&&(!(maladie.estGuerie()))) {
					maladiesGuerissables.add(maladie);
				}
			}
		}
		return maladiesGuerissables;
	}
	
	/**
	 * Détermine si l'action est possible ou non.
	 * @return true si la ville du joueur a une station de recherche et il y a au moins une maladie dans sa main qu'il peut guérir, false sinon.
	 */
	@Override
	public boolean estPossible() {
		List<Maladie> maladiesGuerissables = getMaladiesGuerissables();
		return (!(maladiesGuerissables.isEmpty())&&(this.joueur.getVille().aUneStation())&&(super.joueur.getActionsRestantes()>0));
	}
	
	/**
	 * Permet de choisir la maladie dont on veut trouver le remède interactivement pour les joueurs, au hasard pour les bots
	 * @param maladies
	 * @return la maladie choisie
	 */
	public Maladie choisirMaladie(List<Maladie> maladies) {
		Maladie res;
		if(!(super.joueur.estBot())) {
			InteractiveListChooser<Maladie> lc = new InteractiveListChooser<Maladie>();
			res = lc.choose("de quelle maladie voulez vous découvrir le remède ?", maladies);}
		else {
			RandomListChooser<Maladie> rc = new RandomListChooser<Maladie>();
			res = rc.choose("de quelle maladie voulez vous découvrir le remède ?", maladies);}		
		
		return res;
	}
	
	@Override
	/**
	 * Guérit la maladie si le joueur peut le faire, et baisse son nombre d'actions restantes.
	 */
	public void executer() {
		// TODO Auto-generated method stub
		List<Maladie> maladiesGuerissables=this.getMaladiesGuerissables();
		if (this.estPossible()) {
			this.choisirMaladie(maladiesGuerissables);
			this.maladieAGuerir.guerir();
		}
		this.joueur.setActionsRestantes(this.joueur.getActionsRestantes()-1);
	}

	public String toString() {
		return "Trouver un Remede";
	}
	
	
	
}
