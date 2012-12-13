package enlight.model;

import mikera.vectorz.Vector3;

public abstract class AInfinitePrimitive extends APrimitive {
	@Override
	public boolean isFinite() {
		return false;
	}

	@Override
	public double getSupport(Vector3 normal) {
		throw new UnsupportedOperationException("Support vector calculation not possible on infinite primitives!!!");
	}
}
