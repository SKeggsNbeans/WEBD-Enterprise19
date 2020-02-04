package webd4201.forrestere;
/**
 *An interface stores user information
 * @author eggme
 * @version 1.0 
 * @since 1.0 (2019/01/11)
 */

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author eggme
 *
 */
public class User implements CollegeInterface{

	/**
	 * Default id stored as a long 
	 */
	public static final long DEFAULT_ID = 100123456L;
	
	/**
	 * Default password stored as a string
	 */
	public static final String DEFAULT_PASSWORD = "password";
	
	/**
	 * Default user first name stored as a string
	 */
	public static final String DEFAULT_FIRST_NAME = "John";
	
	/**
	 * Default user last name 
	 */
	public static final String DEFAULT_LAST_NAME = "Doe";
	
	/**
	 * Default user email address
	 */
	public static final String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.com";
	
	/**
	 * Default user status 
	 */
	public static final boolean DEFAULT_ENABLED_STATUS  = true;
	
	/**
	 * Default user type 
	 */
	public static final char DEFAULT_TYPE = 's';
	
	/**
	 * User id number length
	 */
	public static final byte ID_NUMBER_LENGTH  = 9;
	
	/**
	 * minimum password length 
	 */
	public static final byte MININUM_PASSWORD_LENGTH = 8;
	
	/**
	 * maximum password length
	 */
	public static final byte MAXIMUN_PASSWORD_LENGTH = 20;

	/**
	 * date format
	 */
	public static final DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);
		
	/**
	 * String for SHA1
	 */
	public static final String HASH_ALGO = ("SHA1");
	
	
	 /**
	 * this is the id for the user
	 */
	long id;
	/**
	 * this is the password for the user 
	 */
	 String password;
	 /**
	  * this is the First name for the user 
	  */
	 String firstName;
	 /**
	   * this is the last name for the user 
	   */
	 String lastName;
	 /**
	   * this is the email address for the user 
	   */
	 String emailAddress;
	 /**
       * this is the last accessed for the user 
	   */
	 Date lastAccess;
	 /**
	   * this is the last enroll data for the user 
	   */
	 Date enrolDate;
	 /**
	   * this is the enabled for the user 
	   */
	boolean enabled;
	
	/**
	 * this is the type for the user 
	 */
	char type;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the lastAccess
	 */
	public Date getLastAccess() {
		return lastAccess;
	}
	/**
	 * @param lastAccess the lastAccess to set
	 */
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}
	/**
	 * @return the enrolDate
	 */
	public Date getEnrolDate() {
		return enrolDate;
	}
	/**
	 * @param enrolDate the enrolDate to set
	 */
	public void setEnrolDate(Date enrolDate) {
		this.enrolDate = enrolDate;
	}
	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * @return the type
	 */
	public char getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(char type) {
		this.type = type;
	}
	
	/**
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param date2 
	 * @param lastAccess 
	 * @param enabled
	 * @param type
	 * @throws InvalidUserDataException 
	 */
	public User(long id, String password, String firstName, String lastName, String emailAddress, Date enrol, Date lastAccess, boolean enabled, char type) throws InvalidUserDataException {
			
		super();
	try {
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.lastAccess = lastAccess;
		this.enrolDate = enrol;
		this.enabled = enabled;
		this.type = type;
	}catch (Exception i)
		{
			throw new InvalidUserDataException(i.getMessage());
		}
		
	}
	
	/**
	 * Instance attributes to the public class attributes 
	 * @param year 
	 * @param programDescription 
	 * @param programCode 
	 * @param id 
	 * @param programDescription2 
	 * @param programCode2 
	 * @param enable 
	 * @param enrolDate 
	 * @param lastAccess 
	 * @param emailAddress 
	 * @throws InvalidUserDataException 
	 */
	public User(/*long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enable, char type*/) throws InvalidUserDataException
	{
		this(DEFAULT_ID, 
				DEFAULT_PASSWORD, 
				DEFAULT_FIRST_NAME, 
				DEFAULT_LAST_NAME, 
				DEFAULT_EMAIL_ADDRESS, 
				new Date(), 
				new Date(), 
				DEFAULT_ENABLED_STATUS, 
				DEFAULT_TYPE);
				
	}

	
	/**
	 * An instance method named toString() that overloads the java.Object’s toString() 
	 */
	public String toString() {

		String output = "User Info for: " + getId()  
				+ "\n\tName:" + getFirstName() + " " + getLastName()
				+ "\n\tCreated on: " + getEnrolDate()
				+ "\n\tLast access: " + getLastAccess();
		return output;
	}
	/**
	 * Instance method that takes no arguments and returns nothing
	 */
	public void dump(){
		System.out.println(toString());
	}
	
	/**
	 * Class method that accepts long arguments, checks the values for a 
	 * valid id.
	 * @param possibleId
	 * @return a boolean
	 */
	public static boolean verifyId(long possibleId){
		boolean validId = true;
		if(possibleId < MINIMUM_ID_NUMBER || possibleId > MAXIMUM_ID_NUMBER)
			validId = false;
		return validId;
	
	/**
	 * verifyPassword
	 * Checks to see if the user password is grater than the minimum
	 * and maximum password length.
	 */
	}
	public static boolean verifyPassword(String password){
		boolean validPassword = true;
		 if (password.length() < MININUM_PASSWORD_LENGTH || password.length() > MAXIMUN_PASSWORD_LENGTH)
			 validPassword = false;
		 return validPassword;
	}
	
	/**
	 * Checks to see if user first name is empty 
	 * @param firstName
	 * @return validFirstName
	 */
	public static boolean validFirstName (String firstName){
		boolean validFirstName = true;
		if (firstName.equals(" "))
			validFirstName = false;
		return validFirstName;	
	}
	
	/**
	 * Checks to see if user last name is empty 
	 * @param lastName
	 * @return validLastName
	 */
	public static boolean validLastName (String lastName){
		boolean validLastName = true;
		if (lastName.equals(" "))
			
			validLastName = false;
		return validLastName;
	}
	
	@Override
	/**
	 * gets the type of user for the display
	 */
	public String getTypeForDisplay() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @param id
	 * @return UserDA and will retrieve ID
	 * @throws NotFoundException
	 */
	public static User retrieve(long id) throws NotFoundException {
		return UserDA.retrieve(id);
		
	}
	/**
	 * @param anId
	 * @param aPassword
	 * @return anId, aPassword
	 * @throws NotFoundException
	 */
	public static User authenticate(long anId, String aPassword) throws NotFoundException {
		return UserDA.authenticate(anId, aPassword);
		
	}
	/**
	 * @return updated UserDA
	 * @throws NotFoundException
	 */
	public int update() throws NotFoundException
	{
		return UserDA.update(this);
	}
	
	/**
	 * @param c
	 */
	public static void initialize(Connection c)
	{UserDA.initialize(c);}
	
	/**
	 * Terminates the db connection
	 */
	public static void terminate()
	{UserDA.terminate();}
	
	/**
	 * @param id
	 * @return UserDA with an existing user ID
	 */
	public static boolean DuplicateException(long id)
		{ return UserDA.isExistingId(id);}
	
	
	/**
	 * @param id
	 * @return existing ID
	 * @throws ExistingId
	 */
	public static boolean isExistingId(long id)throws ExistingId {
		return UserDA.isExistingId(id);
	}
		
	/**
	 * stores password from decimal to hexadecimal
	 * @param bytes
	 * @return passwordHex
	 */
	public static String decToHex(byte[] bytes)
	{
		String passwordHex = "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++)
		{
			//System.out.println(bytes[i]+ "as hex is" + Integer.toHexString(bytes[i]));
			System.out.println(bytes[i] + "as 2-digit hex is" + String.format("%02x", bytes[i]));
			sb.append(String.format("%02x",bytes[i]));
		}
		passwordHex = sb.toString();
		return passwordHex;
	}
			
	/**
	 * hashes password
	 * @param passToBeHashed
	 * @return hashedPassword
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashedPassword (String passToBeHashed) throws NoSuchAlgorithmException
	{
		String hashedPassword = "";
        MessageDigest md = MessageDigest.getInstance(HASH_ALGO); 
        md.update(passToBeHashed.getBytes()); 
        byte[] bytesOfHashedString = md.digest();
        hashedPassword = decToHex(bytesOfHashedString);
        return hashedPassword; 
		
	}	
	
}


