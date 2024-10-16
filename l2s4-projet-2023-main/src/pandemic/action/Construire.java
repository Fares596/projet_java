package pandemic.action;
import pandemic.role.*;
import java.util.List;
import java.util.ArrayList;
import pandemic.carte.*;
import pandemic.*;
import listchooser.*;

/**
 * Classe représentant l'action de construire une station de recherches héritant du type Action
 */
public class Construire extends Action {
	
	protected Joueur joueur;
	protected Game partie ;
	protected boolean estExpert;
	protected CarteJoueur carte;
	protected Ville stationADeplacer;
	
	/**
	 * Constructeur de la classe
	 * @param joueur Le joueur qui construit la station
	 * @param partie La partie en cours
	 */
	public Construire(Joueur joueur, Game partie) {
		super(joueur);
		this.partie = partie;
		if(this.joueur instanceof Expert ) {
			this.estExpert = true;
		}
		else {
			this.estExpert=false;
		}
		this.stationADeplacer=null;
	}
	
	/**
	 * Détermine si l'action est possible ou non
	 */
	public boolean estPossible() {
		boolean res = estExpert;
		
		
		for(CarteJoueur carte : super.joueur.getMain()) {
			if (carte.getVille()== super.joueur.getVille()) {
				res = true;
				this.carte = carte;
			}
		}
		return (res) && (super.joueur.getActionsRestantes()>0)&&(!(super.joueur.getVille().aUneStation()));
	}
	
	/**
	 * Fait un choix de station à déplacer (pour le cas où on ne peut plus construire de stations et qu'il faut en déplacer une)
	 * @param liste La liste des villes possédant une station
	 */
	public void choisirStationADeplacer(List<Ville> liste){
		ListChooser<Ville> chooser;
		if (this.joueur.estBot()){
			chooser=new RandomListChooser<Ville>();
		}
		else{
			chooser=new InteractiveListChooser<Ville>();
		}
		this.stationADeplacer=chooser.choose("Choisir une station à déplacer sur la ville courante:",liste);
	}

	/**
	 * Exécute l'action de construire une station de recherches
	 */
	public void executer(){
		super.joueur.getVille().ajouteStation();
		super.joueur.defausseCarte(carte);
		
		if( this.partie.getNbStationsRestantes() > 0) {
			this.partie.baisseNbStationsRestantes();
		}
		
		else {
			List<Ville> villesAvecStation=new ArrayList<Ville>();
			for (Ville ville:this.partie.getBoard().getVilles()) {
				if (ville.aUneStation()) {
					villesAvecStation.add(ville);
				}
			}
			this.choisirStationADeplacer(villesAvecStation);
			this.stationADeplacer.enleveStation();
			super.joueur.getVille().ajouteStation();
		}
		super.joueur.getVille().ajouteStation();
		super.joueur.setActionsRestantes(super.joueur.getActionsRestantes()-1);
		for (CarteJoueur carte:super.joueur.getMain()){
			if (carte.getVille()==super.joueur.getVille()){
				this.joueur.defausseCarte(carte);
				return;
			}
		}
	}
	
	/**
	 * Renvoie une chaîne de caractères représentant l'action de construire une station de recherches
	 * @return La chaîne "Construire"
	 */
	public String toString() {
		return "Construire";
	}
	

	
	
}