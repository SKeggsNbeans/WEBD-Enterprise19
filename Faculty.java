package webd4201.forrestere;
/**
 *An interface stores faculty information
 * @author eggme
 * @version 1.0 (2019/01/11)
 * @since 1.0
 */
import java.util.Date;

public abstract class Faculty extends User implements CollegeInterface {
	
	/**
	 * DEFAULT_SCHOOL_CODE is the school code
	 */
	public static final String DEFAULT_SCHOOL_CODE = "SET";
	/**
	 * DEFAULT_SCHOOL_DESCRIPTION holds the school description
	 */
	public static final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";
	/**
	 * DEFAULT_OFFICE holds the default office number
	 */
	public static final String DEFAULT_OFFICE = "H-140";
	/**
	 * DEFAULT_PHONE_EXTENSION holds the default phone extension for the office phone
	 */
	public static final int DEFAULT_PHONE_EXTENSION = 1234 ;
	
	String schoolCode;
	String schoolDescription;
	String office;
	int extension;
	/**
	 * @return the schoolCode
	 */
	public String getSchoolCode() {
		return schoolCode;
	}
	/**
	 * @param schoolCode the schoolCode to set
	 */
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	/**
	 * @return the schoolDescription
	 */
	public String getSchoolDescription() {
		return schoolDescription;
	}
	/**
	 * @param schoolDescription the schoolDescription to set
	 */
	public void setSchoolDescription(String schoolDescription) {
		this.schoolDescription = schoolDescription;
	}
	/**
	 * @return the office
	 */
	public String getOffice() {
		return office;
	}
	/**
	 * @param office the office to set
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * @return the extension
	 */
	public int getExtension() {
		return extension;
	}
	/**
	 * @param extension the extension to set
	 */
	public void setExtension(int extension) {
		this.extension = extension;
	}
	/**
	 * @return the defaultSchoolCode
	 */
	public static String getDefaultSchoolCode() {
		return DEFAULT_SCHOOL_CODE;
	}
	/**
	 * @return the defaultSchoolDescription
	 */
	public static String getDefaultSchoolDescription() {
		return DEFAULT_SCHOOL_DESCRIPTION;
	}
	/**
	 * @return the defaultOffice
	 */
	public static String getDefaultOffice() {
		return DEFAULT_OFFICE;
	}
	/**
	 * @return the defaultPhoneExtension
	 */
	public static int getDefaultPhoneExtension() {
		return DEFAULT_PHONE_EXTENSION;
	}
	
	/**
	 * types for the parameters
	 * creates a super class for the parameters
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param lastAccess
	 * @param enrolDate
	 * @param enabled
	 * @param type
	 * @param schoolCode
	 * @param schoolDescription
	 * @param office
	 * @param extension
	 * @throws InvalidUserDataException throws InvalidUserDataException
	 */
	
	public Faculty(long id, String password, String firstName, String lastName,
			String emailAddress, Date lastAccess, Date enrolDate,
			boolean enabled, char type, String schoolCode,
			String schoolDescription, String office, int extension) throws InvalidUserDataException {
		super(id, password, firstName, lastName, emailAddress, lastAccess,
				enrolDate, enabled, type);
		this.schoolCode = schoolCode;
		this.schoolDescription = schoolDescription;
		this.office = office;
		this.extension = extension;
		
	}
	
	/**
	 * an empty string that returns faculty
	 */
	public String getTypeForDisplay(){
	return "Faculty";
	}
	
	/**
	 * Override the base class’ toString() 
	 * method so that the Faculty’s info is displayed 
	 */
	public String toString(){
		String output = super.toString();
		output = output.replaceFirst("User", getTypeForDisplay());
		output += "\n\t" + getSchoolDescription()
			+ "\n\tOffice: " + " "+ getOffice()
			+ "\n\t(905)721-2000" +" "+ getExtension();
		return output;
	}
		
	
}
 