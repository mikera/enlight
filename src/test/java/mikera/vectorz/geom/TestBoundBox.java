package mikera.vectorz.geom;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBoundBox {
	
	@Test 
	public void testUnbounded() {
		BoundBox bb=new BoundBox();
		
		assertFalse(bb.contains(0,0,0));
	}
}
