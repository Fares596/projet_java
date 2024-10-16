package pandemic;

import static org.junit.Assert.*;
import org.junit.Test;

public class MaladieTest{
	
	
	@Test
	public void GoodInit() {
		Maladie m = new Maladie("ebola");
		assertTrue(m.getName().equals("ebola"));
	}
	
	@Test
	public void retireCubeStockSiSuperieurA0 () {
		Maladie m = new Maladie("ebola");
		m.retireCubeStock();
		assertEquals(m.getCubes(), Maladie.MAX_CUBES-1);
		//on boucle + qu'il ne faut pour verifier qu'on ne retire plus de cube quand cubesRestants = 0
		for (int i = 0 ; i < Maladie.MAX_CUBES+1 ; i++) {
			m.retireCubeStock();
		}
		assertEquals(m.getCubes(),0);
    }
    
	@Test
	public void ajouteCubeStockSiInferieurAVilleNIVFOYER () {
		Maladie m = new Maladie("ebola");
		m.ajouteCubeStock(); //n'ajoute rien car cubesRestants = Ville.NIVFOYER
		assertEquals(m.getCubes(), Maladie.MAX_CUBES);
		m.retireCubeStock();
		m.retireCubeStock();
		m.ajouteCubeStock();
		assertEquals(m.getCubes(), Maladie.MAX_CUBES-1);
	}
	
}
