
package webd4201.forrestere;

/**
 * This exception will be with the setFirstName() and setLastName() 
 * methods (and subsequently the constructors that use the setFirstName() 
 * and setLastName() methods)
 * @author eggme
 * @since 1.0 (2019/4/2)
 * @version 1.0
 */
public class InvalidNameException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public InvalidNameException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public InvalidNameException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public InvalidNameException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidNameException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message - shows message 
	 * @param cause
	 * @param enableSuppression - suppression on the data entered
	 * @param writableStackTrace - traces when and where the error was thrown
	 */
	public InvalidNameException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
