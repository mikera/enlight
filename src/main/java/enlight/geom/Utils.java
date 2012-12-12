package enlight.geom;

import mikera.transformz.ATransform;
import mikera.transformz.Transformz;
import mikera.vectorz.Vector3;

public class Utils {
	public static final ATransform IDENTITY_3D=Transformz.identityTransform(3);
	
	public static final ATransform DEFAULT_RGB_FUNCTION=Transformz.constantTransform(3, Vector3.of(1,1,1));
}
