package pandemic;

import java.util.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	private Board board;
	private Ville ville2;
	private Ville ville1;
	private Maladie maladie;
	
	
	@Before
	public void init() {
		this.board = new Board();
		this.ville1 = new Ville("ville1");
		this.board.addVille(ville1);
		this.ville2 = new Ville("ville2");
		this.maladie  =  new Maladie("ebola");
		for (int i=0;i<Ville.NIVFOYER;i++) {
			this.ville2.ajouteMaladie(this.maladie);
		}
	}
	
	@Test
	public void constructeurTest() {
		Board board=new Board();
		assertEquals(board.getVilles().size(),0);
	}
	
	@Test
	public void addVilleTest(){
		this.board.addVille(ville2);
		assertEquals(2,this.board.getVilles().size());
	}
	
	@Test
	public void getNbFoyersTest() {
		assertEquals(0,this.board.getNbFoyers());
		this.board.addVille(ville2);
		assertEquals(1, this.board.getNbFoyers());
	}
	
	@Test
	public void getVillesTest() {
		ArrayList<Ville> test = new ArrayList<Ville>();
		test.add(ville1);
		assertEquals(test,this.board.getVilles());
	}
	
	@Test
	public void infectionTest() {
		this.board.infection(this.ville1,this.maladie);
		assertEquals(this.ville1.getNivMaladie(this.maladie),1);
		this.board.infection(this.ville1,this.maladie);
		assertEquals(this.ville1.getNivMaladie(this.maladie),2);
	}
	
	@Test
	public void propagationTest() {
		this.board.addVille(this.ville2);
		this.ville2.ajouteVoisin(this.ville1);
		this.ville1.ajouteVoisin(this.ville2);
		this.board.infection(this.ville2,this.maladie);
		assertEquals(Ville.NIVFOYER,this.ville2.getNivMaladie(this.maladie));
		assertEquals(1,this.ville1.getNivMaladie(this.maladie));
		
		Ville ville3=new Ville("Montcuq"); //Ceci est une vraie ville
		this.board.addVille(ville3);
		this.ville1.ajouteVoisin(ville3);
		ville3.ajouteVoisin(ville2);
		for (int i=0;i<Ville.NIVFOYER-1;i++) {
			this.ville1.ajouteMaladie(this.maladie);
		}
		assertEquals(3,this.ville1.getNivMaladie(this.maladie));
		this.board.infection(this.ville1,this.maladie);
		assertEquals(3,this.ville1.getNivMaladie(this.maladie));
		assertEquals(3,this.ville2.getNivMaladie(this.maladie));
		assertEquals(1,ville3.getNivMaladie(this.maladie));
	}

}