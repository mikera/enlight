package enlight;

import clojure.lang.Keyword;

public class Key {
	public static final Keyword CAMERA=Keyword.intern(null, "camera");
	
	public static final Keyword TYPE=Keyword.intern(null, "type");
	public static final Keyword SPHERE=Keyword.intern(null, "sphere");
	public static final Keyword SKY_SPHERE=Keyword.intern(null, "sky-sphere");
	public static final Keyword UNION = Keyword.intern(null, "union");
	public static final Keyword PLANE = Keyword.intern(null, "plane");
	
	public static final Keyword COLOUR=Keyword.intern(null, "colour");
	
	public static final Keyword OBJECTS=Keyword.intern(null, "objects");

	public static final Keyword CENTRE=Keyword.intern(null, "centre");	
	public static final Keyword RADIUS=Keyword.intern(null, "radius");

	public static final Keyword POSITION = Keyword.intern(null, "position");
	public static final Keyword NORMAL = Keyword.intern(null, "normal");
	public static final Keyword DISTANCE = Keyword.intern(null, "distance");

	public static final Keyword SCENE = Keyword.intern(null, "scene");
	public static final Keyword ROOT = Keyword.intern(null,"root");

	public static final Keyword LIGHT_SOURCE = Keyword.intern(null,"light-source");

	public static final Keyword SHADOWLESS =  Keyword.intern(null,"shadowless");
	public static final Keyword INTENSITY =  Keyword.intern(null,"intensity");

}
