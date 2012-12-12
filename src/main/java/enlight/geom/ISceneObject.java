package enlight.geom;

import mikera.vectorz.Vector3;

public interface ISceneObject {
	public void getSupport (Vector3 normal,  IntersectionInfo result);
	
	public void getIntersection(Vector3 start, Vector3 direction, double startDist, IntersectionInfo result);
}
