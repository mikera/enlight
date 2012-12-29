package enlight.model;

import java.util.HashMap;
import java.util.Map;

import clojure.lang.ILookup;
import clojure.lang.Keyword;

import mikera.vectorz.geom.BoundBox;

import enlight.EnlightError;

/**
 * Abstract base class for all scene objects
 * 
 * @author Mike
 */
public abstract class ASceneObject implements ISceneObject, Cloneable, ILookup {
	
	public ASceneObject() {
		
	}
	
	public ASceneObject assoc(Object key, Object value) {
		HashMap<Object, Object> hm=new HashMap<Object,Object>();
		hm.put(key,value);
		return with(hm);
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
		return getProperties().toString();
	}
	
	public boolean isFinite() {
		return false;
	}
	
	@Override
	public abstract Keyword getType();
	
	@Override
	public Object valAt(Object key) {
		Map<Object,Object> props=getProperties();
		return props.get(key);
	}

	@Override
	public Object valAt(Object key, Object notFound) {
		Map<Object,Object> props=getProperties();
		return props.containsKey(key)?props.get(key):notFound;
	}	
}
