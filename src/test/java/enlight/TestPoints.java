package enlight;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import mikera.vectorz.Vector3;

public class TestPoints {
	@Test public void testPoint3D() {
		assertEquals(new Vector3(1,1,1), new Vector3(1,1,1));
		
		Vector3 a=new Vector3();
		assertEquals(new Vector3(0,0,0),a);
		a.setValues(1,2,3);
		assertEquals(new Vector3(1,2,3), a);
		
		a.setValues(3,4,0);
		assertEquals(5.0,a.magnitude(),0.0001);
		a.normalise();
		assertEquals(0.6,a.x,0.0001);
		assertEquals(0.8,a.y,0.0001);
		assertEquals(0.0,a.z,0.0001);
		
		a.setValues(1,2,3);
		assertEquals(14.0,a.magnitudeSquared(),0.0001);
		a.add(-2,-2,-2);
		assertEquals(2.0,a.magnitudeSquared(),0.0001);
		
	}
}
