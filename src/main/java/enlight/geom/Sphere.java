package enlight.geom;

import mikera.vectorz.Vector3;

public class Sphere extends AFinitePrimitive {
	private final Vector3 centre=new Vector3();
	private final double radius;
	
	public Sphere(Vector3 centre, double radius) {
		this.centre.set(centre);
		this.radius=radius;
	}
	
	
	@Override
	public void getSupport(Vector3 normal, Vector3 supportOut) {
		supportOut.set(centre);
		supportOut.addMultiple(normal, radius);
	}
	
	@Override
	public void getIntersection(Vector3 start, Vector3 direction, double dist,
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
		if (dist>=(centreDist+rootDisc)) {
			result.intersectionObject=null;
			return;			
		}
		
		// we definitely have a collision, ensure we have right distance
		double collDist = centreDist-rootDisc;
		if (dist>=collDist) {
			result.interior=true;
			collDist=centreDist+rootDisc;
		} else {
			result.interior=false;
		}
		if (dist>collDist) throw new Error("Shouldn't be possible!");
		
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
