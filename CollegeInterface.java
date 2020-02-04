package webd4201.forrestere;
/**
 *An interface stores college information
 * @author eggme
 * @version 1.0 (2019/01/11)
 * @since 1.0
 */

public interface CollegeInterface {
	/**
	 * Name of educational institute
	 */
	 public static final String COLLEGE_NAME = "Durham college";
	 /**
	  * A string that holds the phone number 
	  */
	 public static final String PHONE_NUMBER = "(905)721-2000";
	 /**
	  * the minimum id number stored as a long
	  */
	 public static final long  MINIMUM_ID_NUMBER  = 100000000L;
	 /**
	  * the maximum id number stored as a long
	  */
	 public static final long  MAXIMUM_ID_NUMBER  = 999999999L;
	 
	 /**
	 * maximum id length 
	 */
	public static final long  MAX_ID_LENGTH = 9;
	
	/**
	 *  minimum id length 
	 */
	public static final long  MIN_ID_LENGTH = 9;
	
	/**
	 * program code minimum length
	 */
	public static final long MIN_PROGRAM_CODE = 4;
	
	/**
	 * program code maximum length
	 */
	public static final long MAX_PROGRAM_CODE = 5;
	
	 /**
	  * A method header that takes no arguments
	  * @return the user type in a string 
	  */
	 public String getTypeForDisplay();

}
