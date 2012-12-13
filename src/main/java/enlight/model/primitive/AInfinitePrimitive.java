package enlight.model.primitive;

import mikera.vectorz.Vector3;
import mikera.vectorz.geom.BoundBox;

public abstract class AInfinitePrimitive extends APrimitive {
	@Override
	public boolean isFinite() {
		return false;
	}

	@Override
	public double getSupport(Vector3 normal) {
		throw new UnsupportedOperationException("Support vector calculation not possible on infinite primitives!!!");
	}
	
	@Override
	public void includeInBoundBox(BoundBox b) {
		b.includeEverything();
	}
}
