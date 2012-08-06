package enlight;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import enlight.maths.Point3D;

public class TestPoints {
	@Test public void testPoint3D() {
		assertEquals(new Point3D(1,1,1), new Point3D(1,1,1));
	}
}
