package pandemic;


import pandemic.role.*;

import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import listchooser.*;
import listchooser.util.Input;

public class PandemicMain {	
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException, MaladieNonTrouveeException {
	    
		// Les villes
	    Map<String, Ville> villes = new HashMap<String, Ville>();
	    // Les secteurs (le nombre du secteur, la liste des villes liée)
	    Map<Integer, ArrayList<Ville>> secteur = new HashMap<Integer, ArrayList<Ville>>();
	    // Maladie
	    Map<Integer, Maladie> maladies = new HashMap<Integer, Maladie>();	    
	    
	    // Lecture du fichier json
	    try {
	    	secteur = lectureFichierJSON(args,villes, maladies);
	    } catch (FileNotFoundException e) {
	    	System.out.println("Le fichier " + args[0] + " n'existe pas ");
	    	System.exit(1);
	    }
	    
	    // Création de board
	    Board board = new Board();
	    // Ajoute des villes à Board
	    for (String ville : villes.keySet()) {
	    	board.addVille(villes.get(ville));
	    }
	    
	    Game game = new Game(board,maladies,secteur);
	    
	    InteractiveListChooser<Integer> IJoueurChooser = new InteractiveListChooser<Integer>();
	    InteractiveListChooser<Joueur> IRoleChooser = new InteractiveListChooser<Joueur>();
	    RandomListChooser<Joueur> RRoleChooser = new RandomListChooser<Joueur>();
	    
	    ArrayList<Integer> ChoixJoueur = new ArrayList<Integer>();
	    ChoixJoueur.add(1);
	    ChoixJoueur.add(2);
	    ChoixJoueur.add(3);
	    ChoixJoueur.add(4);  
	    
	    ArrayList<Joueur> ChoixRole;
	    ArrayList<Ville> ListVilles = new ArrayList<Ville>(villes.values());
		Random random = new Random();	
	    String name;
	    Ville ville;
		
	    int nbJoueur = IJoueurChooser.choose("Quel est le nombre de joueur", ChoixJoueur);
	    int i;
	    

	    ArrayList<Integer> ChoixBoot = new ArrayList<Integer>();
	    for (i=0; i < 4-nbJoueur; i++) {
	    	ChoixBoot.add(i+1);
	    }
	    int nbBoot = 0;
	    if (i > 0) {
	    	nbBoot= IJoueurChooser.choose("Quel est le nombre de Bot", ChoixBoot);
	    }
	    
	    for (i=0; i < nbJoueur; i++) {
	    	ChoixRole = new ArrayList<Joueur>();
	    	System.out.println("\nQuel est votre nom (Joueur "+ (i+1) +")?");
	    	name = Input.readString();
	    	System.out.println();
	    		
	    	ville = ListVilles.get(random.nextInt(ListVilles.size()));
	    	ChoixRole.add(new Expert(name,ville,false,game));
	    	ChoixRole.add(new GlobeTrotter(name,ville,false,game));
	    	ChoixRole.add(new Medecin(name,ville,false,game));
	    	ChoixRole.add(new Scientifique(name,ville,false,game));
	    	Joueur joueur = IRoleChooser.choose("Quel role jouez vous ?", ChoixRole);
		    game.ajouteJoueur(joueur);
		    System.out.println(joueur.getName() + " a choisi " + joueur );
	    	
	    }
	    for (i=0; i < nbBoot; i++) {
	    	name = "Bot "+(i+1);
	    	ville = ListVilles.get(random.nextInt(ListVilles.size()));
	    	ChoixRole = new ArrayList<Joueur>();
	    	
	    	ChoixRole.add(new Expert(name,ville,true,game));
	    	ChoixRole.add(new GlobeTrotter(name,ville,true,game));
	    	ChoixRole.add(new Medecin(name,ville,true,game));
	    	ChoixRole.add(new Scientifique(name,ville,true,game));
	    	Joueur joueur = RRoleChooser.choose("\nQuel role jouez vous ?", ChoixRole);
		    game.ajouteJoueur(joueur);
		    System.out.println("\n" + joueur.getName() + " a choisi " + joueur );
		    Thread.sleep(1000);
	    	
	    }
	    game.jouer();
	    
	   
	    
	    

	}
	
	public static Map<Integer, ArrayList<Ville>> lectureFichierJSON(String args[], Map<String, Ville> villes, Map<Integer, Maladie> maladies) throws FileNotFoundException {
		String filename = args[0];
		FileReader reader = new FileReader(filename);
	    JSONObject phonebook = new JSONObject(new JSONTokener(reader));
	    Map<Integer, ArrayList<Ville>> secteur = new HashMap<Integer, ArrayList<Ville>>();
		
	    Iterator<String> entries = phonebook.keys(); // Permets de parcourir les noms groupes 
	    // System.out.println("\nAffichage des données disponibles par entrée");
	    
	    while (entries.hasNext()) {
	    	String entryKey = entries.next(); // Nom du groupe suivant
	    	// System.out.println(entryKey);
	    	
	    	JSONObject entry = phonebook.getJSONObject(entryKey); // avoir l'objet grâce au nom du groupe
	    	Iterator<String> datakeys = entry.keys(); // avoir les noms des sous-groupes
	    	
	    	
	    	
	    	while (datakeys.hasNext()) {
	    		String entryData = datakeys.next(); // Nom du sous-groupe suivant
	    		// System.out.print("\t"+entryData);
	    		
	    		
		    	if (entryKey.equals("cities")) { // groupe cities, donc sous la forme nom : valeur (int)
		    	 	JSONObject entrydata = phonebook.getJSONObject(entryKey); // Transforme le groupe en objet
				    // System.out.println(" -> "+entrydata.getInt(entryData)); // Permets d'avoir la valeur (int) associer à une valeur(entryData) du groupe
		    	
				    villes.put(entryData, new Ville(entryData));
				    if (!secteur.containsKey(entrydata.getInt(entryData))) {
				    	maladies.put(entrydata.getInt(entryData), new Maladie("M"+entrydata.getInt(entryData)));
				    	secteur.put(entrydata.getInt(entryData), new ArrayList<Ville>());
				    }
				    secteur.get(entrydata.getInt(entryData)).add(villes.get(entryData));
		    	}
		    	else { // groupe neighbors, sous la forme de nom : liste de valeur (list<String>)
				    JSONArray a = entry.getJSONArray(entryData); // Permets d'avoir la liste associer à une valeur(entryData) du groupe
				    // System.out.println();
				    
				    for (Object o : a) { // Parcourir les élements de la liste
				    	String voisin = (String)o;
			    	    // System.out.print("\t\t -> " +voisin+"\n");
			    	    
						villes.get(entryData).ajouteVoisin(villes.get(voisin));
				    }
		    	}
		    }
	    }
	    return secteur;
	}
}
