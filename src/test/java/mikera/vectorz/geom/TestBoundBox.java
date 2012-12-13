package mikera.vectorz.geom;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBoundBox {
	
	@Test 
	public void testBlank() {
		BoundBox bb=new BoundBox();
		
		assertFalse(bb.contains(0,0,0));
		bb.include(1,1,1);
		assertFalse(bb.contains(0,0,0));
		bb.include(-1,-1,-1);
		assertTrue(bb.contains(0,0,0));	
	}
}
