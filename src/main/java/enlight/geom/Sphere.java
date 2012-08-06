package enlight.geom;

import enlight.maths.Point3D;

public class Sphere implements ISceneObject {
	private final Point3D centre=new Point3D();
	private final double radius;
	
	public Sphere(Point3D centre, double radius) {
		this.centre.set(centre);
		this.radius=radius;
	}
	
	
	@Override
	public void getSupport(Point3D normal, Point3D supportOut) {
		supportOut.set(centre);
		supportOut.addMultiple(normal, radius);
	}
	
	@Override
	public void getIntersection(Point3D start, Point3D direction, double dist,
			IntersectionInfo result) {
		// c is centre of sphere translated so line starts at 0,0
		Point3D c=new Point3D(centre);
		c.sub(start);
		
		double centreDist = direction.dot(c);

		// discriminant of quadratic
		double disc=centreDist*centreDist-c.lengthSquared()+radius*radius;
		
		// bailout if line misses completely
		if (disc<0) {
			result.hasIntersection=false;
			return;
		}
		
		double rootDisc=Math.sqrt(disc);
		// bailout if start of line is past sphere
		if (dist>=(centreDist+rootDisc)) {
			result.hasIntersection=false;
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
		
		result.hasIntersection=true;
		result.intersectionPoint.set(direction);
		result.intersectionPoint.mul(collDist);
		result.surfaceNormal.set(result.intersectionPoint);
		result.surfaceNormal.sub(c);
		result.surfaceNormal.normalize();
		if (result.interior) result.surfaceNormal.mul(-1.0);
		result.intersectionPoint.add(start);
		result.intersectionDistance=collDist;
	}
	
}
