package pandemic.role;
import pandemic.*;
import pandemic.carte.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JoueurTest {
	private String name;
	private Ville ville;
	private Joueur joueur;
	
	@Before
	public void init() {
		this.name = "Name";
		this.ville = new Ville("roubaix");
		this.joueur = new Joueur(this.name, this.ville);
	}
	
	@Test
	public void GoodInit() {
		assertEquals(this.name ,this.joueur.getName());
		assertEquals(this.ville ,  this.joueur.getVille());
		assertEquals(this.joueur.getActionsRestantes(), this.joueur.MAX_ACTIONS);
		assertEquals(this.joueur.getMain().size(),0);
	}
	
	/**
	 * Test Action avec Carte : 
	 * piocher une carte = carte bien ajouté a la main
	 * defausser une carte =  carte retirée de la main
	 */
	@Test
	public void testActionsCartes() {
		Joueur j1 = new Joueur("Paul", new Ville("Lille"));
		assertEquals(0,j1.getMain().size());
		j1.piocheCarte(new CarteJoueur(new Ville("Roubaix"), new Maladie("covid")));
		j1.piocheCarte(new CarteJoueur(new Ville("Tourcoing"), new Maladie("bluewaffle")));
		assertEquals(2,j1.getMain().size());
		j1.defausseCarte(main.get(1));
		assertEquals(1,j1.getMain().size());
	}
	
	@Test
	public void test() {
		//TODO autres test possible pour joueur
	}

}
