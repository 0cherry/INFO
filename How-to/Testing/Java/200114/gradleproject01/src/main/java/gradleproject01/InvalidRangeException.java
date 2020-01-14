package gradleproject01;

@SuppressWarnings("serial")
public class InvalidRangeException extends RuntimeException {
	public InvalidRangeException(String msg) {
		super(msg);
	}
}
