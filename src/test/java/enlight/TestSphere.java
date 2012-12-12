package enlight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import enlight.geom.IntersectionInfo;
import enlight.geom.primitive.Sphere;
import mikera.vectorz.Vector3;

public class TestSphere {
	@Test public void test1() {
		Sphere s=new Sphere(new Vector3(0,0,3),2);
		
		IntersectionInfo ii=new IntersectionInfo();
		s.getIntersection(new Vector3(3,0,3), new Vector3(-1,0,0), 0, ii);
		assertEquals(1.0,ii.intersectionDistance, 0.0001);
		assertEquals(2.0,ii.intersectionPoint.x, 0.0001);
		assertEquals(0.0,ii.intersectionPoint.y, 0.0001);
		assertEquals(3.0,ii.intersectionPoint.z, 0.0001);
		assertTrue(ii.hasIntersection());
		assertTrue(ii.surfaceNormal.epsilonEquals(new Vector3(1,0,0)));
		assertTrue(ii.intersectionPoint.epsilonEquals(new Vector3(2,0,3)));
		assertTrue(!ii.interior);

		s.getIntersection(new Vector3(3,0,3), new Vector3(1,0,0), 0, ii);
		assertTrue(!ii.hasIntersection());
	}
	
	@Test public void testFromInside() {
		Sphere s=new Sphere(new Vector3(0,0,3),2);
		
		IntersectionInfo ii=new IntersectionInfo();
		s.getIntersection(new Vector3(0,0,3), new Vector3(0,1,0), 0, ii);
		assertEquals(2.0,ii.intersectionDistance, 0.0001);
		assertEquals(0.0,ii.intersectionPoint.x, 0.0001);
		assertEquals(2.0,ii.intersectionPoint.y, 0.0001);
		assertEquals(3.0,ii.intersectionPoint.z, 0.0001);
		assertTrue(ii.surfaceNormal.epsilonEquals(new Vector3(0,-1,0)));
		assertTrue(ii.intersectionPoint.epsilonEquals(new Vector3(0,2,3)));
		assertTrue(ii.interior);

	}
	
	@Test public void testProperties() {
		Sphere s=new Sphere(new Vector3(0,0,3),2);
		Map<Object,Object> p=s.getProperties();
		assertEquals(2.0,(Double)p.get(Key.RADIUS),0.0);
		
		HashMap<Object,Object> hm=new HashMap<Object,Object>();
		hm.put(Key.RADIUS, 3.0);
		
		Sphere t=s.with(hm);
		Map<Object,Object> q=t.getProperties();
		assertEquals(3.0,(Double)q.get(Key.RADIUS),0.0);
	}
}
