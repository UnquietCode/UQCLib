package unquietcode.stately.closure;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public class ClosureException  extends RuntimeException {
	public ClosureException(String message) {
		super(message);
	}

	public ClosureException(String message, Throwable ex) {
		super(message, ex);
	}
}
