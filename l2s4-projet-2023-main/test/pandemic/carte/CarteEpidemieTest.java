package pandemic.carte;

import pandemic.*;

import java.io.FileNotFoundException;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CarteEpidemieTest {
	private Board board;
	private Game jeu;
	private CarteEpidemie ce;
	private CarteInfection ci;
	private CarteInfection ci2;
	private Maladie maladie;
	private int lvlMaladie;
	
	@Before
	public void init() {
	    Map<String, Ville> villes = new HashMap<String, Ville>();
	    Map<Integer, ArrayList<Ville>> secteur = new HashMap<Integer, ArrayList<Ville>>();
	    Map<Integer, Maladie> maladies = new HashMap<Integer, Maladie>();
	    String[] args = ["carte1.json"]; 
	   
	    try {
	    	secteur = lectureFichierJSON(args,villes, maladies);
	    } catch (FileNotFoundException e) {
	    	System.out.println("Le fichier " + args[0] + " n'existe pas ");
	    	System.exit(1);
	    }
	   
	    this.board = new Board();
	    for (String ville : villes.keySet()) {
	    	board.addVille(villes.get(ville));
	    }
	    this.jeu = new Game(board,maladies,secteur);
	  
		this.ce = new CarteEpidemie(jeu);
		this.maladie = new Maladie("ebola");
		this.ci = new CarteInfection(this.board.getVilles()[0],maladie,jeu);
		this.ci2 = new CarteInfection(this.board.getVilles()[1],new Maladie("covid"),jeu);
		this.jeu.paquetsInfection.ajoutePile(ci);
		this.lvlMaladie = this.board.getVilles()[0].getNivMaladie(this.maladie);
		this.jeu.paquetsInfection.ajouteDefausse(ci2);
		
	}
	
	@Test
	public void effetTest() {
		ce.effet();
		assertEquals(jeu.getTauxInfection(),3);
		//verifier que la pile a diminué + la carte est bien la bonne + la ville a pris unniveau de la bonne maladie + la defausse est remise dans le paquet
		assertEquals(this.jeu.getPaquetsInfection().size(),2);
		assertEquals(this.board.getVilles()[0].getNivMaladie(this.maladie),this.lvlMaladie +1);
	}
}
