package mikera.vectorz.geom;

import mikera.vectorz.AVector;
import mikera.vectorz.Vector3;

/**
 * 3D Bounding box constructed from two Vector3 components
 * 
 * @author Mike
 *
 */
public class BoundBox {
	public final Vector3 lower;
	public final Vector3 upper;
	
	
	
	public BoundBox(Vector3 a, double margin) {
		lower=new Vector3(a.x-margin,a.y-margin,a.z-margin);
		upper=new Vector3(a.x+margin,a.y+margin,a.z+margin);
	}
	
	public BoundBox(AVector a) {
		if (a instanceof Vector3) {
			Vector3 v=(Vector3)a;
			lower=v.clone();
			upper=v.clone();
		} else {
			double x=a.get(0);
			double y=a.get(1);
			double z=a.get(2);
			lower=new Vector3(x,y,z);
			upper=new Vector3(x,y,z);
		}
	}
	
	public BoundBox(AVector a, AVector b) {
		this(a);
		include(b);
	}
	
	public void include(AVector a) {
		if (a instanceof Vector3) {
			include((Vector3) a);
			return;
		}
		include(a.get(0),a.get(1),a.get(2));
	}
	
	public void include(Vector3 a) {
		include(a.x,a.y,a.z);
	}

	private void include(double x, double y, double z) {
		if (x<lower.x) {lower.x=x;} else if (x>upper.x) {upper.x=x;}
		if (y<lower.y) {lower.y=y;} else if (y>upper.y) {upper.y=y;}
		if (z<lower.z) {lower.z=z;} else if (z>upper.z) {upper.z=z;}
	}
}
