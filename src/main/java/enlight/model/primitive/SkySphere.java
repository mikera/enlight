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
		
		// surface normal is opposite of direction
		result.surfaceNormal.set(ray.direction);
		result.surfaceNormal.negate();
		return true;
	}
}
