package enlight.model;

import java.util.HashMap;
import java.util.Map;

import mikera.vectorz.geom.BoundBox;

import enlight.EnlightError;

/**
 * Abstract base class for all scene objects
 * 
 * @author Mike
 */
public abstract class ASceneObject implements ISceneObject, Cloneable {
	
	public ASceneObject() {
		
	}
	
	protected ASceneObject(ASceneObject old, Map<Object, Object> props) {
		this();
	}

	@Override
	public HashMap<Object,Object> getProperties() {
		return new HashMap<Object,Object>();
	}
	
	@Override
	public ASceneObject with(Map<Object,Object> properties) {
		try {
			return (ASceneObject) this.clone();
		} catch (CloneNotSupportedException e) {
			throw new EnlightError(e);
		}
	}
	
	@Override
	public BoundBox getBoundBox() {
		BoundBox b=new BoundBox();
		includeInBoundBox(b);
		return b;
	}
	
	public String toString() {
		return super.toString();
		// TODO: fix this once we get latest vectorz with fixed hashcodes
		// return getProperties().toString();
	}
	
	public boolean isFinite() {
		return false;
	}
}
