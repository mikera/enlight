package enlight;

import mikera.vectorz.Vector3;
import mikera.vectorz.Vector4;
import enlight.geom.ISceneObject;

public class Scene {
	public Vector3 cameraPosition=new Vector3(0,0,0);
	public Vector3 cameraDirection=new Vector3(0,0,1);
	public Vector3 upVector=new Vector3(0,1,0);
	public Vector3 rightVector=new Vector3(1,0,0);
	
	public ISceneObject root;
	
	public ISceneObject getRoot() {
		return root;
	}

	public void getBackGroundColour(Vector3 direction, Vector4 colourOut) {
		colourOut.setValues(0,0,0,0);
	}
}
