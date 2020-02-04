package webd4201.forrestere;

/**
 * InvalidIdException to be used with the setId() 
 * method (and subsequently the constructors that 
 * use the setId() method).
 * @author eggme
 * @since 1.0
 * @version 1.0(2019/4/2)
 */

/**
 * @InvalidIdException checks to see if the data entered is valid
 */
public class InvalidIdException extends Exception {
	private static final long serialVersionUID = 1L;

	
	public InvalidIdException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public InvalidIdException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public InvalidIdException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public InvalidIdException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public InvalidIdException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
}
