package pandemic;

import pandemic.carte.*;
import pandemic.role.*;
import pandemic.action.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import listchooser.*;

import java.lang.Thread;

/**
 * Classe représentant une partie de Pandemic
 */
public class Game {
	private static final int NB_VILLES=48;
	public static final int NB_CARTES=NB_VILLES;
	public static final int NB_CARTES_EPIDEMIES=4;
	public static final int NB_STATIONS=6;
	public static final int TAUX_GLOBAL_INFECTION_DEBUT=2;
	public static final int NB_CARTES_A_TIRER_JOUEUR=2;
	protected int stationsRestantes;
	protected int tauxGlobalInfection;
	protected Board board;
	protected Paquets<ICarteJoueur> paquetsJoueur;
	protected Paquets<CarteInfection> paquetsInfection;
	protected List<Joueur> joueurs;
	protected Map<Integer, Maladie> maladies;
	protected Map<Integer, ArrayList<Ville>> secteur;
	protected Random random = new Random();
	protected int nbCarteJoueurTirer;
	
	/**
	 * Constructeur de la classe
	 * @param board Le plateau de jeu
	 * @param maladies Les maladies présentes dans le jeu
	 * @param secteur Les différents secteurs où positionner les villes
	 */
	public Game(Board board, Map<Integer, Maladie> maladies, Map<Integer, ArrayList<Ville>> secteur) {
		this.joueurs = new ArrayList<Joueur>();
		this.paquetsInfection = new Paquets<CarteInfection>();
		this.paquetsJoueur = new Paquets<ICarteJoueur>();
		this.board = board;
		this.secteur = secteur;
		this.maladies = maladies;
		this.tauxGlobalInfection = TAUX_GLOBAL_INFECTION_DEBUT;
		this.stationsRestantes = NB_STATIONS;
		this.nbCarteJoueurTirer = NB_CARTES_A_TIRER_JOUEUR;
	}
	
	/**
	 * Méthode exécutant le déroulement de la partie
	 * @throws InterruptedException Le code est interrompu de temps en temps pour de la lisibilité, cette exception peut-être renvoyée
	 * @throws MaladieNonTrouveeException Si une maladie rechercher n'est pas trouver
	 */
	public void jouer() throws InterruptedException, MaladieNonTrouveeException {
		ArrayList<Action> actions;
		this.creationCarte();
		int nbTour = 1;
		boolean finDePartie = false;
		while (!finDePartie) {
			try {
				System.out.println("***************\nTour " + nbTour);
				System.out.println(this.board + "\n");
				
				
				for (Joueur j: this.joueurs) { // Tour des joueurs
					System.out.println("\nTour de " +j.getName());
					System.out.println(j.getName() + " est dans " + j.getVille().toutLesMaladies());
					
					for (int i =0; i <nbCarteJoueurTirer; i++) { // Pioche dans le paquet des cartes joueurs
						ICarteJoueur c = this.paquetsJoueur.retirePile();
						
						if (c instanceof CarteEpidemie) {
							System.out.println(" " + j.getName() + " pioche une carte Epidemie");
							
							CarteInfection carteInfection = this.paquetsInfection.retirePile();
							this.board.infection(carteInfection.getVille(), carteInfection.getMaladie());
							paquetsInfection.ajouteDefausse(carteInfection);
								
					    	this.paquetsInfection.melangerDefausse();
					    		
					    	while (!this.paquetsInfection.defausseEstVide()) {
					    		this.paquetsInfection.ajoutePile(this.paquetsInfection.retireDefausse());
					    	}
					    	
					    	this.paquetsJoueur.ajouteDefausse(c);
						} else {
							System.out.println(" " + j.getName() + " pioche une carte Joueur");
							j.piocheCarte(c);
						}
						Thread.sleep(1000);
					}
						
					
					// Action
					for (int i=0; (i < Joueur.MAX_ACTIONS) && (j.getActionsRestantes() > 0);i++) {
						actions = new ArrayList<Action>();
						actions.add(new Construire(j,this));
						actions.add(new PasserTour(j));
						actions.add(new SeDeplacer(j));
						actions.add(new TraiterMaladie(j));
						actions.add(new TrouverRemede(j));
						ArrayList<Action> actionPossible = new ArrayList<Action>();
						for (Action action: actions) {
							if (action.estPossible()) {
								actionPossible.add(action);
							}	
						}
						Action action = j.choose("\nQuelle action voulez vous faire ?", actionPossible);
						action.executer();
						Thread.sleep(1000);
						
					}
					j.setActionsRestantes(4);
					
					//Si le joueur a plus de 7 cartes dans la main à la fin de son tour
					while (j.getMain().size()>7){
						ListChooser<CarteJoueur> chooserDefausse;
						if (j.estBot()){
							chooserDefausse=new RandomListChooser<CarteJoueur>();
						}
						else{
							chooserDefausse=new InteractiveListChooser<CarteJoueur>();
						}
						CarteJoueur carteADefausser=chooserDefausse.choose("Choisir une carte à défausser:",j.getMain());
						j.defausseCarte(carteADefausser);
					}
				
					// Carte Infection
					System.out.println("\n Pioche des Cartes Infections");
						for (int i =0; i< this.tauxGlobalInfection;i++) {
							if (this.paquetsInfection.getPile().size() > 0) {
								CarteInfection carteInfection = this.paquetsInfection.retirePile();
								this.board.infection(carteInfection.getVille(), carteInfection.getMaladie());
						    	this.paquetsInfection.ajouteDefausse(carteInfection);
							}
							Thread.sleep(1000);
						}
				}
				nbTour++;
				System.out.println("\n");
				finDePartie = this.jeuEstFini();
			
			}
			catch (FinDePartieException e) {
				// e.getMessage()
				if (e.getMessage() == "Pile est vide") {
					System.out.println("Perdu, vous n'avez pas assez de cartes");
				}
				if (e.getMessage().contains("n'a pas de cube")) {
					System.out.println("Perdu, vous n'avez pas assez de cartes");
					
				}
				return;
			}
		}
		System.out.println("Win");
		return;
	}
	
	/**
	 * Détermine si la partie est fini
	 * @return true si une des conditions de fin de partie est validée, false sinon.
	 */
	private boolean jeuEstFini() {
		// TODO Auto-generated method stub
		boolean toutLesRemedes = true;
		for (Maladie maladie:  this.maladies.values()) {
			toutLesRemedes = toutLesRemedes && maladie.estGuerie();
		}
		return this.board.getNbFoyers() >= 8 || toutLesRemedes;
	}

	/**
	 * Crée les paquets de cartes infections et de cartes joueurs, ainsi que les cartes épidémies à mélanger avec les cartes joueurs
	 */
	public void creationCarte() {
	    // Creation des cartes 
		
	    for (int i : this.secteur.keySet()) {
	    	for(Ville ville : this.secteur.get(i)) {
	    		// Carte Infection
	    		CarteInfection carteInfection = new CarteInfection(ville, this.maladies.get(i),this);
	    		
	    		this.paquetsInfection.ajoutePile(carteInfection);
	    		// Carte Joueur
	    		this.paquetsJoueur.ajoutePile(new CarteJoueur(ville, this.maladies.get(i),this));
	    	}
	    }
	    
	    // Carte Épidemie
	    for (int i = 0; i < (Game.NB_CARTES_EPIDEMIES); i++) {
	    	this.paquetsJoueur.ajoutePile(new CarteEpidemie(this));
	    }
	    this.paquetsJoueur.melangerPile();
	    this.paquetsInfection.melangerPile();
	}
	
	/**
	 * Ajouter un joueur dans la liste des joueurs de la partie
	 * @param j Le joueur à ajouter
	 */
	public void ajouteJoueur(Joueur j) {
		this.joueurs.add(j);
	}
	
	/**
	 * Accesseur du plateau de jeu
	 * @return Le plateau de jeu de la partie
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Accesseur du nombre de stations de recherches que l'on peut encore construire
	 * @return Le nombre de stations de recherches restantes
	 */
	public int getNbStationsRestantes() {
		return this.stationsRestantes;
	}
	
	/**
	 * Décrémente le nombre de stations que l'on peut encore construire de 1
	 */
	public  void baisseNbStationsRestantes() {
		stationsRestantes -=1;
	}

	/**
	 * Accesseur du paquet de cartes joueurs
	 * @return Le paquet de cartes joueurs
	 */
	public Paquets<ICarteJoueur> getPaquetsJoueur(){
		return this.paquetsJoueur;
	}
	
	/**
	 * Accesseur du paquet de cartes infections
	 * @return Le paquet de cartes infections
	 */
	public Paquets<CarteInfection> getPaquetsInfection(){
		return this.paquetsInfection;
	}
	
	/**
	 * Accesseur du taux global d'infection
	 * @return Le taux global d'infection
	 */
	public int getTauxInfection() {
		return this.tauxGlobalInfection;
	}
	
	/**
	 * Incrémente le taux global d'infection de 1
	 */
	public void augmenteTauxInfection() {
		this.tauxGlobalInfection++;
	}
}

