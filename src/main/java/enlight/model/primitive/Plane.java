package enlight.model.primitive;

import java.util.HashMap;
import java.util.Map;

import clojure.lang.Keyword;

import enlight.Key;
import enlight.model.IntersectionInfo;
import mikera.vectorz.Vector3;
import mikera.vectorz.geom.Ray;

public class Plane extends AInfinitePrimitive {
	private final Vector3 normal;
	private final double distance;
	
	@Override
	public HashMap<Keyword, Object> getProperties() {
		HashMap<Keyword,Object> hm=super.getProperties();
		hm.put(Key.TYPE, getType());
		hm.put(Key.NORMAL, normal);
		hm.put(Key.DISTANCE, Double.valueOf(distance));		
		return hm;
	}
	
	@Override public Keyword getType() {
		return Key.PLANE;
	}

	
	protected Plane(Plane old, Map<Object,Object> props) {
		super(old,props);
		Vector3 n=(Vector3) (props.containsKey(Key.NORMAL)?props.get(Key.NORMAL):old.normal);
		double d=((Number)(props.containsKey(Key.DISTANCE)?props.get(Key.DISTANCE):old.distance)).doubleValue();	
	
		n=n.clone();
		double sv=n.normalise();
		d/=sv;
		this.normal=n;
		this.distance=d;
	}
	
	@Override public Plane with(Map<Object,Object> props) {
		return new Plane(this,props);
	}
	
	public Plane(Vector3 normal, double dist) {
		Vector3 n=normal.clone();
		double sv=n.normalise();
		double d=dist/sv;
		this.normal=n;
		this.distance=d;
	}
	
	
	@Override
	public boolean getIntersection(Ray ray,	IntersectionInfo result) {
		double a=ray.direction.dotProduct(normal);
		
		if (a==0.0) return false;
		double op=ray.origin.dotProduct(normal); 
		double opd=op-distance; // distance of origin from plane
		
		double dist=-opd/a; // distance of collision in direction of ray
		if (dist>ray.end) return false; // ray not long enough
		if (ray.start>=dist) return false; // ray starts past plane
		
		result.intersectionObject=this;
		result.intersectionPoint.set(ray.origin);
		result.intersectionPoint.addMultiple(ray.direction,dist);
		result.surfaceNormal.set(normal);
		if (opd<0) result.surfaceNormal.multiply(-1.0);
		result.intersectionDistance=dist;
		ray.end=dist;
		return true;
	}

}
