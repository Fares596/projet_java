package pandemic.carte;

import pandemic.*;

import java.util.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CarteJoueurTest {
	private Ville ville;
	private Maladie maladie;
	private Game jeu;
	private CarteInfection ci;
	
	@Before
	public void init() {
		this.ville =  new Ville("Roubaix");
		this.maladie =  new Maladie("ebola");
		this.jeu =  new Game();
		this.ci = new CarteInfection(ville,maladie,jeu);
	}
	
	@Test
	public void GoodInit() {
		assertEquals(ci.getVille(),ville);
		assertEquals(ci.getMaladie(),maladie);
		assertEquals(ci.getJeu(),jeu);
	}
	
	@Test
	public void test() {
		
	}

}
