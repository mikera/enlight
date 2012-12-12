package enlight.geom;

import enlight.geom.Utils;
import mikera.transformz.ATransform;
import mikera.vectorz.Vector3;

/**
 * Abstract base class for primitives that can be hit by rays
 * @author Mike
 */
public abstract class APrimitive extends ASceneObject {
	public abstract boolean isFinite();
	
	private final ATransform colourFunction;
	
	public APrimitive() {
		colourFunction=Utils.DEFAULT_RGB_FUNCTION;
	}
	
	public void getAmbientColour(Vector3 position, Vector3 colourOut) {
		colourOut.setValues(1,1,1,1);
	}

	public abstract void getSupport(Vector3 normal, IntersectionInfo resultOut);
	
	public abstract void getIntersection(Vector3 start, Vector3 direction, double dist, IntersectionInfo result);
}
