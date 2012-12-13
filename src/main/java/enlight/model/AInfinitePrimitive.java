package enlight.model;

import mikera.vectorz.Vector3;

public abstract class AInfinitePrimitive extends APrimitive {
	@Override
	public boolean isFinite() {
		return false;
	}

	@Override
	public void getSupport(Vector3 normal, IntersectionInfo resultOut) {
		throw new UnsupportedOperationException("Support vector calculation not possible on infinite primitives!!!");
	}
}
