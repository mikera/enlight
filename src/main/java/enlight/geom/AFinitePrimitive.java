package enlight.geom;

import java.util.HashMap;

public abstract class AFinitePrimitive extends APrimitive {

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
