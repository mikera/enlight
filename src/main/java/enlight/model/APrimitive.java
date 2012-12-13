package enlight.model;

import java.util.HashMap;
import java.util.Map;

import enlight.Key;
import enlight.model.Utils;
import mikera.transformz.ATransform;
import mikera.vectorz.Vector3;

/**
 * Abstract base class for primitives (anything that can be hit by rays)
 * @author Mike
 */
public abstract class APrimitive extends ASceneObject {
	public abstract boolean isFinite();
	
	private final ATransform colourFunction;
	
	public APrimitive() {
		colourFunction=Utils.DEFAULT_RGB_FUNCTION;
	}
	
	protected APrimitive(APrimitive old, Map<Object, Object> props) {
		super(old,props);
		colourFunction=(ATransform) (props.containsKey(Key.COLOUR)?props.get(Key.COLOUR):old.colourFunction);
	}

	public void getAmbientColour(Vector3 position, Vector3 colourOut) {
		colourFunction.transform(position,colourOut);
	}
	
	@Override
	public HashMap<Object,Object> getProperties() {
		HashMap<Object,Object> hm=super.getProperties();
		hm.put(Key.TYPE, Key.SPHERE);
		hm.put(Key.COLOUR, colourFunction);
		return hm;
	}

	public abstract void getSupport(Vector3 normal, IntersectionInfo resultOut);
	
	public abstract void getIntersection(Vector3 start, Vector3 direction, double startDist, IntersectionInfo result);
}
