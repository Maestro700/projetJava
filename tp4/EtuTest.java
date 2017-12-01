package ephec.tp4;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EtuTest {
	Etu david, filsDecorde, cloneDavid;
	@Before
	public void setUp(){
		david = new Etu("Jacobs", "David");
		filsDecorde = new Etu("Bouterfa", "Youssef");
		cloneDavid = new Etu("Jacobs", "David");
	}

	@Test
	public void test() {
		david.setResultat(new int[]{12, 12, 12, 12, 12});
		assertEquals(12.0, david.moyenne(), 0.0);
		david.setResultat(new int[]{20, 20, 20, 20, 20});
		assertEquals(20.0, david.moyenne(), 0.0);
		david.setResultat(new int[]{0, 0, 0, 0, 0});
		assertEquals(0.0, david.moyenne(), 0.0);
		david.setResultat(new int[]{20, 20, 0, 0, 10});
		assertEquals(10.0, david.moyenne(), 0.0);
		}
	@Test
	public void testEquals(){
		assertFalse(filsDecorde.equals(null));
		assertFalse(filsDecorde.equals(david));
		assertFalse(david.equals(cloneDavid));
		assertTrue(david.equals(david));
	}
	@Test
	public void testCompareTo(){
		assertTrue(filsDecorde.compareTo(david)>0);
		assertTrue(david.compareTo(filsDecorde)<0);
		assertTrue(filsDecorde.compareTo(filsDecorde)== 0);
		
	}

}
