import static org.junit.Assert.*;
import org.junit.Test;
public class BeeTester {

	@Test

	public void testConstructor1(){
	Bee Bee1 = new Bee("Pujaa Rajan", 2013, 2, 'F');
	assertEquals(Bee1.getNickname(),"Pujaa Rajan");
	assertEquals(Bee1.getYear(), 2013);
	assertEquals(Bee1.getMonth(), 2);
	assertEquals(Bee1.isMale(), false);
	assertEquals(Bee1.getMom(), null);

	}
	
	@Test
	
	public void testGroupB(){
	Bee Bee1 = new Bee("Pujaa Rajan", 2013, 2, 'F');
	Bee mother  = new Bee("Punitha Rajan", 2000, 4, 'F');
	Bee1.addMom(mother);
	assertEquals(mother, Bee1.getMom());
	assertEquals(1, mother.getNumChildren());
    Bee father =  new Bee("Rajan Srinivasan", 2000, 8, 'M');
	Bee1.addDad(father);
    assertEquals(father, Bee1.getDad());
	assertEquals(1, father.getNumChildren());
	}
	
	@Test
	
	public void testGroupC() {
		Bee mother  = new Bee("Punitha Rajan", 2000, 4, 'F');
		Bee father =  new Bee("Rajan Srinivasan", 2000, 8, 'M');
		Bee pujaaRajan = new Bee("Pujaa Rajan", 2013, 1, mother);
	    assertEquals(mother, pujaaRajan.getMom());
		Bee pranavRajan = new Bee("Pranav Rajan", 2014, 2, mother, father);
		assertEquals(mother, pranavRajan.getMom());
		assertEquals(father, pranavRajan.getDad());
	}
	
	@Test
	
	public void testGroupD(){
		Bee mother  = new Bee("Punitha Rajan", 2000, 4, 'F');
		Bee father =  new Bee("Rajan Srinivasan", 2000, 8, 'M');
		Bee pujaaRajan = new Bee("Pujaa Rajan", 2013, 1, mother, father);
		Bee pranavRajan = new Bee("Pranav Rajan", 2014, 2, mother);
		assertTrue(pranavRajan.getYear() > pujaaRajan.getYear());
		assertEquals(false , mother.isSibling(mother));
		assertFalse(pranavRajan.getYear() < pujaaRajan.getYear());
		assertEquals(pujaaRajan.getMom(), pranavRajan.getMom());
		assertEquals(pranavRajan.getDad(), null);
		assertEquals(pujaaRajan.getDad(), father);
	}
}