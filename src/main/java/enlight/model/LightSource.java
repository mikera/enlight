package enlight.model;

import java.util.HashMap;
import java.util.Map;

import mikera.transformz.ATransform;
import mikera.vectorz.Vector3;
import mikera.vectorz.geom.BoundBox;
import mikera.vectorz.geom.Ray;
import clojure.lang.Keyword;
import enlight.Colour;
import enlight.Key;
import enlight.model.primitive.APrimitive;

public class LightSource extends ASceneObject {
	public final boolean shadowless;
	public final Vector3 colour;
		
	public LightSource () {
		shadowless=false;
		colour=Colour.WHITE;
	}
	
	protected LightSource(LightSource old, Map<Object, Object> props) {
		super(old,props);
		shadowless=(Boolean) (props.containsKey(Key.SHADOWLESS)?props.get(Key.SHADOWLESS):old.shadowless);
		colour=(Vector3) (props.containsKey(Key.COLOUR)?props.get(Key.COLOUR):old.colour);
	}
	
	@Override
	public HashMap<Keyword, Object> getProperties() {
		HashMap<Keyword,Object> hm=super.getProperties();
		hm.put(Key.COLOUR, colour);
		hm.put(Key.SHADOWLESS, shadowless);
		return hm;
	}
	
	
	@Override
	public double getSupport(Vector3 normal) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getIntersection(Ray ray, IntersectionInfo result) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void includeInBoundBox(BoundBox b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Keyword getType() {
		return Key.LIGHT_SOURCE;
	}

}
