package enlight;

public class EnlightError extends RuntimeException {
	public EnlightError(String s) {
		super (s);
	}
	
	public EnlightError(Throwable t) {
		super (t);
	}
	
	private static final long serialVersionUID = -7281357458922836283L;

}
