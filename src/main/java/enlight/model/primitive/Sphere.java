package enlight.model.primitive;

import java.util.HashMap;
import java.util.Map;

import clojure.lang.Keyword;

import enlight.Key;
import enlight.model.IntersectionInfo;
import mikera.vectorz.Vector3;
import mikera.vectorz.geom.BoundBox;
import mikera.vectorz.geom.Ray;

public class Sphere extends AFinitePrimitive {
	private final Vector3 centre;
	private final double radius;
	
	@Override
	public HashMap<Object,Object> getProperties() {
		HashMap<Object,Object> hm=super.getProperties();
		hm.put(Key.TYPE, getType());
		hm.put(Key.CENTRE, centre);
		hm.put(Key.RADIUS, Double.valueOf(radius));		
		return hm;
	}
	
	@Override public Keyword getType() {
		return Key.SPHERE;
	}

	
	protected Sphere(Sphere old, Map<Object,Object> props) {
		super(old,props);
		centre=(Vector3) (props.containsKey(Key.CENTRE)?props.get(Key.CENTRE):old.centre);
		radius=((Number)(props.containsKey(Key.RADIUS)?props.get(Key.RADIUS):old.radius)).doubleValue();	
	}
	
	@Override public Sphere with(Map<Object,Object> props) {
		return new Sphere(this,props);
	}
	
	public Sphere(Vector3 centre, double radius) {
		this.centre=centre;
		this.radius=radius;
	}
	
	
	@Override
	public double getSupport(Vector3 normal) {
		return normal.dotProduct(centre)+radius;
	}
	
	@Override
	public boolean getIntersection(Ray ray,	IntersectionInfo result) {
		// c is centre of sphere translated so line starts at 0,0
		Vector3 c=new Vector3(centre);
		c.sub(ray.origin);
		
		double centreDist = ray.direction.dotProduct(c);

		// discriminant of quadratic
		double disc=(centreDist*centreDist)-c.magnitudeSquared()+(radius*radius);
		
		// bailout if line misses completely
		if (disc<=0.0) return false;
		
		double rootDisc=Math.sqrt(disc);
		// bailout if not far enough
		if (ray.end<=(centreDist-rootDisc)) return false;
		
		// bailout if start of line is past sphere
		if (ray.start>=(centreDist+rootDisc)) return false;
		
		// we definitely have a collision, ensure we have right distance
		double collDist = centreDist-rootDisc;
		if (ray.start>=collDist) {
			result.interior=true;
			collDist=centreDist+rootDisc;
		} else {
			result.interior=false;
		}
		assert (ray.start<=collDist);
		
		result.intersectionObject=this;
		result.intersectionPoint.set(ray.direction);
		result.intersectionPoint.multiply(collDist);
		result.surfaceNormal.set(result.intersectionPoint);
		result.surfaceNormal.sub(c);
		result.surfaceNormal.normalise();
		if (result.interior) result.surfaceNormal.multiply(-1.0);
		result.intersectionPoint.add(ray.origin);
		result.intersectionDistance=collDist;
		ray.end=collDist;
		return true;
	}

	@Override
	public void includeInBoundBox(BoundBox b) {
		b.include(centre,radius);
	}


}
