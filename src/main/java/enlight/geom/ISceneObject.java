package enlight.geom;

import enlight.maths.Point3D;

public interface ISceneObject {
	public void getSupport (Point3D normal, Point3D supportOut);
	
	public void getIntersection(Point3D start, Point3D direction, double dist, IntersectionInfo result);
}
