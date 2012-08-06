package enlight.geom;

import enlight.maths.Point3D;

public class Sphere implements ISceneObject {
	public Point3D midPoint;
	public double radius;
	
	@Override
	public void getSupport(Point3D normal, Point3D supportOut) {
		supportOut.set(midPoint);
		supportOut.addMultiple(normal, radius);
	}
	
	@Override
	public void getIntersection(Point3D start, Point3D direction, double dist,
			IntersectionInfo result) {
		// c is centre of sphere translated so line starts at 0,0
		Point3D c=new Point3D(midPoint);
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
		if (dist>=collDist) collDist=centreDist+rootDisc;
		
		result.hasIntersection=true;
		result.intersectionPoint.set(direction);
		result.intersectionPoint.mul(collDist);
		result.surfaceNormal.set(result.intersectionPoint);
		result.surfaceNormal.sub(c);
		result.surfaceNormal.normalize();
		result.intersectionPoint.add(start);
		
	}
	
}
