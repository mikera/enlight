package enlight.model.primitive;

import java.util.Collection;

import mikera.vectorz.Vector3;
import mikera.vectorz.geom.BoundBox;
import mikera.vectorz.geom.Ray;
import enlight.EnlightError;
import enlight.model.ASceneObject;
import enlight.model.IntersectionInfo;

public final class Union extends ACompositeObject {

	private final boolean finite;
	private final int count;
	private final ASceneObject[] objects;
	
	private Union(ASceneObject[] objects) {
		this.objects=objects;
		count=objects.length;
		
		boolean anyInfinite=false;
		for (int i=0; i<count; i++) {
			if (!objects[i].isFinite()) anyInfinite=true;
		}
		finite=!anyInfinite;
	}
	
	public Union of(Collection<ASceneObject> objects) {
		return new Union(objects.toArray(new ASceneObject[objects.size()]));
	}
	
	@Override
	public boolean isFinite() {
		return finite;
	}

	@Override
	public double getSupport(Vector3 normal) {
		if (!isFinite()) throw new EnlightError("Can't get support of union of infinite objects!");
		throw new UnsupportedOperationException("Not yet implemented!");
	}

	@Override
	public boolean getIntersection(Ray ray, IntersectionInfo result) {
		boolean found=false;
		for (ASceneObject o:objects) {
			if (o.getIntersection(ray, result)) found=true;
		}
		return found;
	}

	@Override
	public void includeInBoundBox(BoundBox b) {
		for (ASceneObject o:objects) {
			o.includeInBoundBox(b);
		}
	}

}
