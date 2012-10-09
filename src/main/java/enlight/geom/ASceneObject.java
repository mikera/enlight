package enlight.geom;

/**
 * Abstract base class for all scene objects
 * 
 * @author Mike
 */
public abstract class ASceneObject implements ISceneObject {
	
	public boolean isFinite() {
		return false;
	}
}
