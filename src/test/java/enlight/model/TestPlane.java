package enlight.model;

import static org.junit.Assert.*;
import org.junit.Test;

import enlight.Key;
import enlight.model.IntersectionInfo;
import enlight.model.primitive.Plane;
import mikera.vectorz.Vector3;
import mikera.vectorz.geom.Ray;

public class TestPlane {
	@Test public void test1() {
		Plane p=new Plane(new Vector3(0,1,0),0);
		
		IntersectionInfo ii=new IntersectionInfo();
		assertTrue(p.getIntersection(new Ray(new Vector3(3,3,3), new Vector3(0,-1,0)), ii));

		assertEquals(3.0,ii.intersectionDistance,0.000001);
		assertTrue(ii.intersectionPoint.epsilonEquals(Vector3.of(3,0,3)));
	}
	
	@Test public void testProperties() {
		Plane p=new Plane(new Vector3(0,2,0),1.0);
		
		assertEquals(0.5,p.getProperties().get(Key.DISTANCE));
	}
}
