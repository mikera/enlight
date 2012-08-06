package enlight.maths;

public class Point3D {
	public double x;
	public double y;
	public double z;
	
	public Point3D() {
	}
	
	public Point3D(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public Point3D(Point3D a) {
		this(a.x,a.y,a.z);
	}
	
	
	public void add(Point3D a) {
		x+=a.x;
		y+=a.y;
		z+=a.z;
	}
	
	public void addMultiple(Point3D a, double factor) {
		x+=a.x*factor;
		y+=a.y*factor;
		z+=a.z*factor;
	}
	
	public void add(double dx, double dy, double dz) {
		x+=dx;
		y+=dy;
		z+=dz;
	}
	
	public void addMultiple(double dx, double dy, double dz, double factor) {
		x+=dx*factor;
		y+=dy*factor;
		z+=dz*factor;
	}
	
	public void sub(Point3D a) {
		x-=a.x;
		y-=a.y;
		z-=a.z;
	}
	
	public void sub(double dx, double dy, double dz) {
		x-=dx;
		y-=dy;
		z-=dz;
	}
	
	public void mul(double factor) {
		x*=factor;
		y*=factor;
		z*=factor;
	}
	
}
