package annotation;

@SuppressWarnings("serial")
public abstract class ReifyingException extends RuntimeException {

	public ReifyingException() {
	}

	public ReifyingException(String message) {
		super(message);
	}

	public ReifyingException(Throwable cause) {
		super(cause);
	}

	public ReifyingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReifyingException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
