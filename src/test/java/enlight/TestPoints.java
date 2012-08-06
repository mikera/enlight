package enlight;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import enlight.maths.Point3D;

public class TestPoints {
	@Test public void testPoint3D() {
		assertEquals(new Point3D(1,1,1), new Point3D(1,1,1));
		
		Point3D a=new Point3D();
		assertEquals(new Point3D(0,0,0),a);
		a.set(1,2,3);
		assertEquals(new Point3D(1,2,3), a);
		
		a.set(3,4,0);
		assertEquals(5.0,a.length(),0.0001);
		a.normalize();
		assertEquals(0.6,a.x,0.0001);
		assertEquals(0.8,a.y,0.0001);
		assertEquals(0.0,a.z,0.0001);
		
		a.set(1,2,3);
		assertEquals(14.0,a.lengthSquared(),0.0001);
		a.add(-2,-2,-2);
		assertEquals(2.0,a.lengthSquared(),0.0001);

		
		
	}
}
