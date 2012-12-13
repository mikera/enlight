package mikera.vectorz.geom;

import mikera.vectorz.AVector;
import mikera.vectorz.Vector3;

/**
 * Class representing a Ray segment
 * @author Mike
 */
public class Ray {
	public final Vector3 origin;
	public final Vector3 direction;
	
	public double start=0;
	public double end=Double.POSITIVE_INFINITY;
	
	public Ray(Vector3 origin, Vector3 direction) {
		this.origin=new Vector3(origin);
		this.direction=new Vector3(direction);
	}
	
	public Ray(AVector origin, AVector direction) {
		this.origin=new Vector3(origin);
		this.direction=new Vector3(direction);
	}
	
	
}
