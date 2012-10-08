package enlight.geom;

import mikera.vectorz.Vector3;

public abstract class APrimitive extends ASceneObject {
	public Vector3 getAmbientColour(Vector3 position) {
		return new Vector3(1,1,1);
	}
	

}
