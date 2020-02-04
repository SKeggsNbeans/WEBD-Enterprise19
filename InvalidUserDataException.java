package webd4201.forrestere;

/**
 * This exception will be used to indicate if any passed data 
 * to create a User object is incorrect (i.e. will be used 
 * to re-package the above exceptions in the User constructors).
 * @author eggme
 * @since 1.0 (2019/4/2)
 * @version 1.0
 */

public class InvalidUserDataException extends Exception {

	/**
	 * Suppresses warning in file
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Checks to see if the user is invalid
	 */
	public InvalidUserDataException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Checks to see if the user is good 
	 * @param message
	 */
	public InvalidUserDataException(String message) {
		super(message);
	}

	/**
	 * Checks to see if the user data is valid 
	 * @param cause
	 */
	public InvalidUserDataException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidUserDataException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
