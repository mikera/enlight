package enlight.geom;

import mikera.vectorz.Vector3;

/**
 * Class used to store the result of a ray/object intersection test
 * @author Mike
 *
 */
public class IntersectionInfo {
	public Object intersectionObject=false;
	public final Vector3 intersectionPoint=new Vector3();
	public final Vector3 surfaceNormal=new Vector3();
	public double intersectionDistance=0.0;
 	public boolean interior=false;
 	
 	public boolean hasIntersection() {
 		return intersectionObject!=null;
 	}
}
