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

}
