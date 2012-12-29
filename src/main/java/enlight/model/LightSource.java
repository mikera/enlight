package enlight.model;

import mikera.vectorz.Vector3;
import mikera.vectorz.geom.BoundBox;
import mikera.vectorz.geom.Ray;
import clojure.lang.Keyword;
import enlight.Key;

public class LightSource extends ASceneObject {
	public final Boolean shadowless;
		
	public LightSource () {
		shadowless=false;
	}
	
	@Override
	public double getSupport(Vector3 normal) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getIntersection(Ray ray, IntersectionInfo result) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void includeInBoundBox(BoundBox b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Keyword getType() {
		return Key.LIGHT_SOURCE;
	}

}
