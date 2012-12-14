package enlight.model.primitive;

import mikera.vectorz.geom.Ray;
import enlight.model.IntersectionInfo;

public final class SkySphere extends AInfinitePrimitive {

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
}
