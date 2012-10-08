package enlight;

import mikera.vectorz.Vector3;
import mikera.vectorz.Vector4;
import enlight.geom.APrimitive;
import enlight.geom.IntersectionInfo;

public class Tracer {
	
	public Scene scene;
	
	
	// traces a ray from a screen position
	public void trace(double sx,double sy, Vector4 colourOut) {
		Vector3 d=scene.cameraDirection.clone();
		d.addMultiple(scene.upVector, 1-(2*sy));
		d.addMultiple(scene.rightVector, (2*sx)-1);
		d.normalise();
		
		trace(scene.cameraPosition,d,colourOut);
	}
	
	public void trace(Vector3 pos, Vector3 direction, Vector4 colourOut) {		
		IntersectionInfo ii=new IntersectionInfo();
		scene.getRoot().getIntersection(pos, direction, 0.0, ii);
		
		if (ii.hasIntersection()) {
			APrimitive p=ii.intersectionObject;
			p.getAmbientColour(ii.intersectionPoint, colourOut);
		} else {
			scene.getBackGroundColour(direction,colourOut);
		}
	}
}
