package enlight.model;

import mikera.transformz.ATransform;
import mikera.transformz.Transformz;
import mikera.transformz.impl.IdentityTranslation;
import mikera.vectorz.Vector3;
import mikera.vectorz.geom.BoundBox;

public class Utils {
	public static final Vector3 X_POSITIVE=Vector3.of(1,0,0);
	public static final Vector3 Y_POSITIVE=Vector3.of(0,1,0);
	public static final Vector3 Z_POSITIVE=Vector3.of(0,0,1);
	public static final Vector3 X_NEGATIVE=Vector3.of(-1,0,0);
	public static final Vector3 Y_NEGATIVE=Vector3.of(0,-1,0);
	public static final Vector3 Z_NEGATIVE=Vector3.of(0,0,-1);
	
	public static final IdentityTranslation IDENTITY_3D=Transformz.identityTranslation(3);
	
	public static final ATransform DEFAULT_RGB_FUNCTION=Transformz.constantTransform(3, Vector3.of(1,1,1));

	public static void includeSupportBounds(ASceneObject o, BoundBox b) {
		assert(o.isFinite());
		b.includeX(o.getSupport(X_POSITIVE));
		b.includeX(-o.getSupport(X_NEGATIVE));
		b.includeY(o.getSupport(Y_POSITIVE));
		b.includeY(-o.getSupport(Y_NEGATIVE));
		b.includeZ(o.getSupport(Z_POSITIVE));
		b.includeZ(-o.getSupport(Z_NEGATIVE));
	}
}
