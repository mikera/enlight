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
	
	public void normalize() {
		double l=length();
		if (l==0) {set(0,0,0); return;}
		double factor=1.0/l;
		mul(factor);
	}

	public double lengthSquared() {
		return x*x+y*y+z*z;
	}
	
	public double length() {
		return Math.sqrt(x*x+y*y+z*z);
	}

	private void set(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=y;
	}
	
	@Override public boolean equals(Object a) {
		if (!(a instanceof Point3D)) return false;
		return equals((Point3D)a);
	}
	
	public boolean equals(Point3D a) {
		return ((x==a.x)&&(y==a.y)&&(z==a.z));
	}
	
}
