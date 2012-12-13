package enlight.model.primitive;

import java.util.HashMap;
import java.util.Map;

import enlight.Key;
import enlight.model.ASceneObject;
import enlight.model.IntersectionInfo;
import enlight.model.Utils;
import mikera.transformz.ATransform;
import mikera.vectorz.Vector3;
import mikera.vectorz.geom.Ray;

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

	public abstract double getSupport(Vector3 normal);
	
	public abstract boolean getIntersection(Ray ray, IntersectionInfo result);
}
