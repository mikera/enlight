package enlight.model.primitive;

import java.util.HashMap;
import java.util.Map;

import enlight.Key;
import enlight.model.AFinitePrimitive;
import enlight.model.IntersectionInfo;
import mikera.vectorz.Vector3;

public class Sphere extends AFinitePrimitive {
	private final Vector3 centre;
	private final double radius;
	
	@Override
	public HashMap<Object,Object> getProperties() {
		HashMap<Object,Object> hm=super.getProperties();
		hm.put(Key.CENTRE, centre);
		hm.put(Key.RADIUS, Double.valueOf(radius));		
		return hm;
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
	public void getSupport(Vector3 normal, IntersectionInfo supportOut) {
		supportOut.intersectionObject=this;
		supportOut.intersectionPoint.set(centre);
		supportOut.intersectionPoint.addMultiple(normal, radius);
		supportOut.surfaceNormal.set(normal);
		supportOut.intersectionDistance=Double.NaN;
	}
	
	@Override
	public void getIntersection(Vector3 start, Vector3 direction, double startDist,
			IntersectionInfo result) {
		// c is centre of sphere translated so line starts at 0,0
		Vector3 c=new Vector3(centre);
		c.sub(start);
		
		double centreDist = direction.dotProduct(c);

		// discriminant of quadratic
		double disc=(centreDist*centreDist)-c.magnitudeSquared()+(radius*radius);
		
		// bailout if line misses completely
		if (disc<0) {
			result.intersectionObject=null;
			return;
		}
		
		double rootDisc=Math.sqrt(disc);
		// bailout if start of line is past sphere
		if (startDist>=(centreDist+rootDisc)) {
			result.intersectionObject=null;
			return;			
		}
		
		// we definitely have a collision, ensure we have right distance
		double collDist = centreDist-rootDisc;
		if (startDist>=collDist) {
			result.interior=true;
			collDist=centreDist+rootDisc;
		} else {
			result.interior=false;
		}
		assert (startDist<=collDist);
		
		result.intersectionObject=this;
		result.intersectionPoint.set(direction);
		result.intersectionPoint.multiply(collDist);
		result.surfaceNormal.set(result.intersectionPoint);
		result.surfaceNormal.sub(c);
		result.surfaceNormal.normalise();
		if (result.interior) result.surfaceNormal.multiply(-1.0);
		result.intersectionPoint.add(start);
		result.intersectionDistance=collDist;
	}	
}
