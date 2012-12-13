package enlight.model.primitive;

import mikera.vectorz.Vector3;
import mikera.vectorz.geom.BoundBox;
import enlight.model.AInfinitePrimitive;
import enlight.model.IntersectionInfo;

public class SkySphere extends AInfinitePrimitive {

	@Override
	public void getIntersection(Vector3 start, Vector3 direction, double dist,
			IntersectionInfo result) {
		
		result.intersectionDistance=Double.POSITIVE_INFINITY;	
		result.intersectionObject=this;
		
		// surface normal is opposite of direction
		result.surfaceNormal.set(direction);
		result.surfaceNormal.negate();
		
	}




}
