package enlight.model;

import java.util.HashMap;
import java.util.Map;

import mikera.vectorz.Vector3;
import mikera.vectorz.geom.BoundBox;
import mikera.vectorz.geom.Ray;
import clojure.lang.Keyword;
import enlight.Key;

public class Scene extends ASceneObject {

	public ASceneObject root;
	public Map<Object,Object> camera;
	
	public Scene() {
		root=null;
		camera=null;
	}
	
	protected Scene(Scene old, Map<Object, Object> props) {
		super(old,props);
		this.root=(ASceneObject) (props.containsKey(Key.ROOT)?props.get(Key.ROOT):old.root);
		this.camera=(Map<Object,Object>) (props.containsKey(Key.CAMERA)?props.get(Key.CAMERA):old.camera);
	}

	@Override
	public HashMap<Keyword, Object> getProperties() {
		HashMap<Keyword,Object> props= super.getProperties();
		props.put(Key.ROOT, root);
		props.put(Key.CAMERA, camera);
		return props;
	}
	
	@Override
	public Scene with(Map<Object,Object> properties) {
		return new Scene(this,properties);
	}
	
	@Override
	public double getSupport(Vector3 normal) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getIntersection(Ray ray, IntersectionInfo result) {
		if ( root.getIntersection(ray, result)) {
			return true;
		}
		return false;
	}

	@Override
	public void includeInBoundBox(BoundBox b) {
		root.includeInBoundBox(b);
	}

	@Override
	public Keyword getType() {
		return Key.SCENE;
	}

}
