package enlight;

import mikera.vectorz.Vector3;
import enlight.geom.ISceneObject;

public class Tracer {
	
	public ISceneObject scene;
	
	public Vector3 cameraPosition=new Vector3(0,0,0);
	public Vector3 cameraDirection=new Vector3(0,0,1);
	public Vector3 upVector=new Vector3(0,1,0);
	public Vector3 rightVector=new Vector3(1,0,0);
	
	// traces a ray from a screen position
	public void trace(double sx,double sy) {
		Vector3 d=cameraDirection.clone();
		d.addMultiple(upVector, 1-(2*sy));
		d.addMultiple(rightVector, (2*sx)-1);
		
	}
}
