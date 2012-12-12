package enlight.geom;

import java.util.HashMap;

/**
 * Abstract base class for all scene objects
 * 
 * @author Mike
 */
public abstract class ASceneObject implements ISceneObject {
	
	public HashMap<Object,Object> getProperties() {
		return new HashMap<Object,Object>();
	}
	
	public boolean isFinite() {
		return false;
	}
}
