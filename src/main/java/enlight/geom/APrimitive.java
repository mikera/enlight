package enlight.geom;

import mikera.vectorz.Vector3;
import mikera.vectorz.Vector4;

/**
 * Abstract base class for primitives that can be hit by rays
 * @author Mike
 */
public abstract class APrimitive extends ASceneObject {
	public void getAmbientColour(Vector3 position, Vector4 colourOut) {
		colourOut.setValues(1,1,1,1);
	}

	public abstract void getSupport(Vector3 normal, IntersectionInfo resultOut);
	
	public abstract void getIntersection(Vector3 start, Vector3 direction, double dist, IntersectionInfo result);
}
