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
	
	/**
	 * Assoc a new key/value pair into the scene object
	 */
	public ASceneObject assoc(Keyword key, Object value) {
		HashMap<Object, Object> hm=new HashMap<Object,Object>();
		hm.put(key,value);
		return with(hm);
	}
	
	protected ASceneObject(ASceneObject old, Map<Object, Object> props) {
		this();
	}

	@Override
	public HashMap<Keyword, Object> getProperties() {
		return new HashMap<Keyword,Object>();
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
		Map<Keyword,Object> props=getProperties();
		return props.get(key);
	}

	@Override
	public Object valAt(Object key, Object notFound) {
		Map<Keyword,Object> props=getProperties();
		return props.containsKey(key)?props.get(key):notFound;
	}	
}
