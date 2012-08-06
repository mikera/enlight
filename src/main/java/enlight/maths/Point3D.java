package enlight.maths;

public final class Point3D {
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
		double len=length();
		if (len==0) {set(0,0,0); return;}
		double factor=1.0/len;
		mul(factor);
	}

	public double lengthSquared() {
		return x*x+y*y+z*z;
	}
	
	public double length() {
		return Math.sqrt(x*x+y*y+z*z);
	}

	public void set(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public void set(Point3D a) {
		x=a.x;
		y=a.y;
		z=a.z;
	}
	
	public double dot(Point3D a) {
		return x*a.x+y*a.y+z*a.z;
	}
	
	public void cross(Point3D a) {
		double tx=y*a.z-z*a.y;
		double ty=z*a.x-x*a.z;
		double tz=x*a.y-y*a.x;			
		x=tx;
		y=ty;
		z=tz;
	}
	
	@Override public boolean equals(Object a) {
		if (!(a instanceof Point3D)) return false;
		return equals((Point3D)a);
	}
	
	public boolean equals(Point3D a) {
		return ((x==a.x)&&(y==a.y)&&(z==a.z));
	}
	
	@Override
	public String toString() {
		return "Point3D ["+x+", "+y+", "+z+"]";
	}


	
}
