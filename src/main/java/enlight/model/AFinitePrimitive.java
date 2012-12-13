package enlight.model;

import java.util.HashMap;
import java.util.Map;

public abstract class AFinitePrimitive extends APrimitive {

	public AFinitePrimitive() {
		
	}
	
	protected AFinitePrimitive(AFinitePrimitive old, Map<Object, Object> props) {
		super(old,props);
	}

	@Override
	public HashMap<Object,Object> getProperties() {
		HashMap<Object,Object> hm=super.getProperties();
		return hm;
	}
	
	@Override
	public boolean isFinite() {
		return true;
	}

}