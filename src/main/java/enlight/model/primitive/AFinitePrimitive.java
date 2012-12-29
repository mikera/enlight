package enlight.model.primitive;

import java.util.HashMap;
import java.util.Map;

import clojure.lang.Keyword;

import enlight.model.Utils;

import mikera.vectorz.geom.BoundBox;

public abstract class AFinitePrimitive extends APrimitive {

	public AFinitePrimitive() {
		
	}
	
	protected AFinitePrimitive(AFinitePrimitive old, Map<Object, Object> props) {
		super(old,props);
	}

	@Override
	public HashMap<Keyword, Object> getProperties() {
		HashMap<Keyword,Object> hm=super.getProperties();
		return hm;
	}
	
	@Override
	public boolean isFinite() {
		return true;
	}
	
	@Override public void includeInBoundBox(BoundBox b) {
		Utils.includeSupportBounds(this, b);
	}

}
