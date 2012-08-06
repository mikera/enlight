package enlight.geom;

import enlight.maths.Point3D;

/**
 * Class used to store the result of a ray/object intersection test
 * @author Mike
 *
 */
public class IntersectionInfo {
	public final Point3D intersectionPoint=new Point3D();
	public final Point3D surfaceNormal=new Point3D();
	public double intersectionDistance=0.0;
	public boolean hasIntersection=false;
 	public boolean interior=false;;
}
