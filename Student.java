package webd4201.forrestere;
/**
 * Hold the marks and displays student info
 * @author eggme
 * @version 1.0
 * @since 1.0 (2019/02/01)
 */
import java.util.Date;
import java.util.Vector;
import java.sql.*;

public class Student extends User{
	
	/**
	 * stores the default program code for users
	 */
	public static final String DEFAULT_PROGRAM_CODE  = "UNDC";
	
	/**
	 * stores the default program description
	 */
	public static final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";
	
	/**
	 * stores default years enrolled for students
	 */
	public static final int DEFAULT_YEAR = 1;

	private String programCode;
	private String programDescription;
	private int year;
	/**
	 * A vector that will hold marks
	 */
	private Vector<Mark> marks = new Vector<Mark>();

	//*******SETTERS AND GETTERS*******//
	
	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return programCode;
	}
	/**
	 * @param programCode the programCode to set
	 */
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	/**
	 * @return the programDescription
	 */
	public String getProgramDescription() {
		return programDescription;
	}
	/**
	 * @param programDescription the programDescription to set
	 */
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * @return the student marks
	 */
	public Vector<Mark> getMarks() {
		return marks;
	}
	/**
	 * @param marks the marks to set
	 */
	public void setMarks(Vector<Mark> marks) {
		this.marks = marks;
	}
	/**
	 * @return the defaultProgramCode
	 */
	public static String getDefaultProgramCode() {
		return DEFAULT_PROGRAM_CODE;
	}
	/**
	 * @return the defaultProgramDescription
	 */
	public static String getDefaultProgramDescription() {
		return DEFAULT_PROGRAM_DESCRIPTION;
	}
	/**
	 * @return the defaultYear
	 */
	public static int getDefaultYear() {
		return DEFAULT_YEAR;
	}
	

	/**
	 * 
	 */
	   /** A parameterized constructor that takes one argument each for 
	     * the inherited and new Student attributes
		 * @param id
		 * @param password
		 * @param firstName
		 * @param lastName
		 * @param emailAddress
		 * @param lastAccess
		 * @param enrolDate
		 * @param enabled
		 * @param type
	     * @throws InvalidUserDataException 
		 */
		public Student(long id, String password, String firstName, String lastName,
				String emailAddress, Date lastAccess, Date enrolDate,
				boolean enabled, char type, String programCode, String programDescription, int year, Vector<Mark> marks) throws InvalidUserDataException {
			super();

			setProgramCode(programCode);
			setProgramDescription(programDescription);
			setYear(year);
			setMarks(marks);		
		}
		/**
		 * A parameterized constructor sets the arguments for the 
		 * constructor.
		 * @param id
		 * @param password
		 * @param firstName
		 * @param lastName
		 * @param emailAddress
		 * @param lastAccess
		 * @param enrolDate
		 * @param enabled
		 * @param type
		 * @param programCode
		 * @param programDescription
		 * @param year
		 * @throws InvalidUserDataException 
		 */
		public Student(long id, String password, String firstName, String lastName,
				String emailAddress, Date lastAccess, Date enrolDate,
				boolean enabled, char type, String programCode, String programDescription, int year) throws InvalidUserDataException {
			this(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate,
					enabled, type, programCode, programDescription, year, new Vector<Mark>());
		}
		/**
		 * A default constructor should calls the parameterized 
		 * one defined  that does not include a Vector of Mark objects 
		 * passing all of the default values using the this() keyword 
		 * @param year2 
		 * @param programDescription2 
		 * @param programCode2 
		 * @param id 
		 * @throws InvalidUserDataException 
		 */
		public Student() throws InvalidUserDataException{
			this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, 
					DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, 
							new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE, 
					 DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION, DEFAULT_YEAR);
		}
		// DA static methods, you DO NOT need to be a Student object to do these *********************************
		/**
		 * parameter for initializing the connection string 
		 * @param c
		 */
		public static void initialize(Connection c)
			{StudentDA.initialize(c);}
		/**
		 * @param key
		 * @return the "key" from StudentDA
		 * @throws NotFoundException
		 */
		public static Student retrieve(long key) throws NotFoundException
			{return StudentDA.retrieve(key);}
		/**
		 * closes the database connection
		 */
		public static void terminate()
			{StudentDA.terminate();}

		/**
		 * Override the base class’ toString() 
		 * method so that the Student’s info
		 * and suffix
		 * @param num
		 * @return the suffix for the student year
		 */
		public static String getSuffix(int num)
		{
			
			 if (num %100 >= 4 && num %100 >= 20) {
				 	return "th";
			 }
			 		else 		  
			if ( num %10 == 3){
					return "rd";
			 }
					else
			 if ( num %10 == 2){
					return num + "nd";	
			 }
					else
			 if ( num %10 == 1){
					return num + "st";		
			 }
			 return "";
		}
		 
		/**
		 * Displays formatted information about a student
		 */
		public String toString(){
			String output = "Student Info for: "
					+ "\n\t" + getFirstName() + " " + getLastName() + " " + getId()
					+ "\n\tCurrently in " + getYear() + getSuffix(getYear()) + " year of " + getProgramDescription() + " " + getProgramCode()
					+  "\n\tEnrolled: " + getEnrolDate();
			return output;
		}
		public String getTypeForDisplay()
		{
			return "Student";
		}
		
}
