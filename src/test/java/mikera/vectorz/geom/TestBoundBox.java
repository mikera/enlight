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
	
	@Test 
	public void testInner() {
		BoundBox bb=new BoundBox();
		
		bb.include(1,1,1);
		bb.include(-1,-1,-1);
		
		BoundBox bb2=new BoundBox(bb);
		assertTrue(bb2.contains(bb));
		assertTrue(bb.contains(bb2));
		
		bb2=new BoundBox(bb,1.0);
		assertTrue(bb2.contains(bb));
		assertFalse(bb.contains(bb2));
		assertTrue(bb2.contains(1.5,-1.5,1.5));

	}
}
