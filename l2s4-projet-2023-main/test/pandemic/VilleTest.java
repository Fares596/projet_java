package pandemic;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VilleTest{
	
	private Ville ville;
	private Ville voisin;
	private Maladie maladie;
	
	@Before
	public void init() {
		this.voisin = new Ville ("test 1");
		this.ville = new Ville("test");
		this.ville.ajouteVoisin(this.voisin);
		this.maladie = new Maladie("m1");
	}
	
	@Test
	public void stateIsCorrectAtCreation() {
		assertEquals("test",this.ville.getNom());
		assertEquals(1,this.ville.getVoisins().size());
		assertFalse(this.ville.aUneStation());
		assertEquals(new ArrayList<Maladie>(),this.ville.getMaladies());
	}
	
	
	@Test
	public void testEstVoisin() {
		assertFalse(this.ville.estVoisin(new Ville("1")));
		assertTrue(this.ville.estVoisin(this.voisin));		
	}
	
	@Test
	public void testNiveauMaladie() {
		assertTrue(0 == this.ville.getNivMaladie(this.maladie));
		this.ville.ajouteMaladie(this.maladie);
		assertTrue(1 == this.ville.getNivMaladie(this.maladie));
	}
	
	@Test
	public void aUneStationEtAjouteStationTest() {
		assertFalse(this.ville.aUneStation());
		this.ville.ajouteStation();
		assertTrue(this.ville.aUneStation());
	}
	
	@Test
	public void ajouteMaladieTest() {
		this.ville.ajouteMaladie(this.maladie);
		assertEquals(1,this.ville.getMaladies().size());
		this.ville.ajouteMaladie(this.maladie);
		assertEquals(2,this.ville.getNivMaladie(this.maladie));
	}
	
	@Test
	public void enleveMaladieTest() throws MaladieNonTrouveeException{
		this.ville.ajouteMaladie(this.maladie);
		this.ville.enleveMaladie(this.maladie);
		assertEquals(0,this.ville.getNivMaladie(this.maladie));
	}
	
	@Test(expected=MaladieNonTrouveeException.class)
	public void exceptionRenvoyeeSiMaladieNEstpasDansVoisins() throws MaladieNonTrouveeException{
		this.ville.enleveMaladie(this.maladie);
	}
	
	@Test(expected=MaladieNonTrouveeException.class)
	public void exceptionRenvoyeeSiNivMaladieVautZero() throws MaladieNonTrouveeException{
		this.ville.ajouteMaladie(this.maladie);
		this.ville.enleveMaladie(this.maladie);
		this.ville.enleveMaladie(this.maladie);
	}
	
	@Test
	public void estFoyerTest() {
		assertFalse(this.ville.estFoyer());
		for (int i=0;i<Ville.NIVFOYER;i++) {
			this.ville.ajouteMaladie(this.maladie);
		}
		assertTrue(this.ville.estFoyer());
	}

}
