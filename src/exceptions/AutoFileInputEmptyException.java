package exceptions;

public class AutoFileInputEmptyException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public AutoFileInputEmptyException() {
		super();
	}
	
	public AutoFileInputEmptyException(String message) {
		super(message);
		System.err.println("AutoFileInputEmptyException: " + message);
	}

}
