package enlight.model.primitive;

import java.util.HashMap;
import java.util.Map;

import clojure.lang.Keyword;

import mikera.vectorz.geom.Ray;
import enlight.Key;
import enlight.model.IntersectionInfo;

public final class SkySphere extends AInfinitePrimitive {

	public SkySphere(SkySphere skySphere, Map<Object, Object> props) {
		super(skySphere,props);
	}
	
	public SkySphere() {
		
	}
	
	@Override
	public HashMap<Object,Object> getProperties() {
		HashMap<Object,Object> hm=super.getProperties();
		hm.put(Key.TYPE, getType());
		return hm;
	}
	
	@Override public Keyword getType() {
		return Key.SKY_SPHERE;
	}

	@Override
	public boolean getIntersection(Ray ray,
			IntersectionInfo result) {
		if (ray.end<Double.POSITIVE_INFINITY) return false;
		
		ray.end=Double.POSITIVE_INFINITY;
		result.intersectionDistance=Double.POSITIVE_INFINITY;	
		result.intersectionObject=this;
		
		// we force the intersection point to be on the unit sphere according to direction
		// this is needed for colour calculation
		result.intersectionPoint.set(ray.direction);
		
		// surface normal is opposite of direction
		result.surfaceNormal.set(ray.direction);
		result.surfaceNormal.negate();
		return true;
	}
	
	@Override public SkySphere with(Map<Object,Object> props) {
		return new SkySphere(this,props);
	}
}
