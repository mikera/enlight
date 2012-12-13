package enlight.model;

import java.util.HashMap;
import java.util.Map;

import mikera.vectorz.Vector3;

public interface ISceneObject {
	public double getSupport (Vector3 normal);
	
	public void getIntersection(Vector3 start, Vector3 direction, double startDist, IntersectionInfo result);

	/**
	 * Gets the property map for this scene object
	 * @return
	 */
	public HashMap<Object, Object> getProperties();

	/**
	 * Creates a clone of this scene object with updated properties as specified.
	 * @param properties New prperties to update
	 * @return
	 */
	public ASceneObject with(Map<Object, Object> properties);
}
