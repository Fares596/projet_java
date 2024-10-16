package pandemic.role;
import pandemic.*;
import pandemic.carte.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class RoleTest {
<<<<<<< HEAD
	/**
	 * Test Action avec Carte : 
	 * piocher une carte = carte bien ajouté a la main
	 * defausser une carte =  carte retirée de la main
	 */
	@Test
	public void testActionsCartes() {
		Joueur j1 = new Joueur("Paul", new Ville("Lille"), true);
		assertEquals(0,j1.getMain().size());
		j1.piocheCarte(new CarteJoueur(new Ville("Roubaix"), new Maladie("covid")));
		j1.piocheCarte(new CarteJoueur(new Ville("Tourcoing"), new Maladie("bluewaffle")));
		assertEquals(2,j1.getMain().size());
		j1.defausseCarte(main.get(1));
		assertEquals(1,j1.getMain().size());
	}
	
	/**
	 * 	Test sedeplacer : Avec et sans Globetrotter 
	 */
	@Test
	public void testseDeplacer() {
		
	}
	
	/**
	 * Test construire : Avec et sans Expert	
	 */
	@Test
	public void testConstruire() {
		
	}
	
	/**
	 * Test Remede : avec ou sans scientifique
	 */
	@Test
	public void testRemede() {
		ville = new Ville("Lille");
		maladie = new Maladie("grippe");
		ville.ajouteMaladie(maladie);
		j2 = new Scientifique("Jack",ville,true);
		remede = new TrouverRemede(j2);
		System.out.println(remede.getMaladiesGuerissables());
		remede.executer(); /* censé ne rien produire car le joueur n'a pas 4 cartes joueurs avec le maladie grippe  */
		System.out.println(remede.getMaladiesGuerissables());/* la liste des maladies guerissable n'a pas changée*/
		j2.piocheCarte(new CarteJoueur(new Ville("Roubaix"), maladie));
		j2.piocheCarte(new CarteJoueur(new Ville("Paris"), maladie));
		j2.piocheCarte(new CarteJoueur(new Ville("Marseille"), maladie));
		j2.piocheCarte(new CarteJoueur(new Ville("Lyon"), maladie));
		ville.ajouteStation();
		remede.executer(); /* ici la maladie grippe doit être guérie */

	}
	
	/**
	 * Test traiterMaladie : avec ou sans Medecin
	 */
	@Test
	public void testTraiterMaladie() {
		
	}
=======


>>>>>>> branch 'main' of git@gitlab-etu.fil.univ-lille.fr:nicolas.senecaux.etu/l2s4-projet-2023.git
}