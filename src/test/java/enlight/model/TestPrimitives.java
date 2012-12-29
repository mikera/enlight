package enlight.model;

import org.junit.Test;
import static org.junit.Assert.*;

import enlight.model.ASceneObject;
import enlight.model.Utils;
import enlight.model.primitive.Plane;
import enlight.model.primitive.Sphere;
import enlight.model.primitive.Union;
import mikera.vectorz.Vector3;
import mikera.vectorz.geom.BoundBox;

public class TestPrimitives {

	private void doSupportBoundsTest(ASceneObject s) {
		BoundBox b=new BoundBox();
		s.includeInBoundBox(b);

		BoundBox b2=new BoundBox();
		Utils.includeSupportBounds(s, b2);

		assertEquals(b,s.getBoundBox());
		assertEquals(b,b2);
	}

	private void doFinitePrimitiveTests(ASceneObject s) {
		doSupportBoundsTest(s);
	}
	


	private void doPrimitiveTests(ASceneObject s) {
		if (s.isFinite()) {
			doFinitePrimitiveTests(s);
		}
		
	}


	@Test public void genericPrimitiveTests() {
		ASceneObject sphere=new Sphere(Vector3.of(1.5,-1,0),1.0);
		doPrimitiveTests(sphere);
		
		ASceneObject plane=new Plane(Vector3.of(0,1,0),10.0);
		doPrimitiveTests(new Plane(Vector3.of(0,1,0),10.0));
		
		doPrimitiveTests(Union.of(sphere,plane));
	}


}
