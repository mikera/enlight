package enlight.model;

import java.util.HashMap;
import java.util.Map;

import mikera.vectorz.Vector3;
import mikera.vectorz.geom.BoundBox;
import mikera.vectorz.geom.Ray;

public interface ISceneObject {
	public double getSupport (Vector3 normal);
	
	/**
	 * Gets the intersection of the scene object with a ray
	 * Returns true if an intersection was found, in which case IntersectionInfo should be populated.
	 * @param result Out parameter for populated intersection info if intersection was found, unchanged otherwise
	 */
	public boolean getIntersection(Ray ray, IntersectionInfo result);

	public void includeInBoundBox(BoundBox b);
	
	public BoundBox getBoundBox();

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
