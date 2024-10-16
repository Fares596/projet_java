package pandemic.action;
import pandemic.role.*;
import pandemic.Ville;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	private Joueur joueur;
	private Ville ville2;
	private Ville ville1;
	private GlobeTrotter globetrotter;
	
	@Before
	public void init() {
		this.ville1 = new Ville("ville1");
		this.ville2 = new Ville("ville2");
		this.joueur  = new Joueur("joueur1", ville1);
		this.globetrotter = new GlobeTrotter("fares", ville1);
	
	@Test
	public void constructeurTest() {
		SeDeplacer sedeplacer = new SeDeplacer(globetrotter);
		assertEquals(sedeplacer.getVilleDest(),null);
		assertEquals(sedeplacer.getEstGlobeTrotter(), true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	