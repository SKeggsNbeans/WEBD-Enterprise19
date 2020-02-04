package webd4201.forrestere;
/**
 *An interface stores marks and GPA's
 * @author eggme
 * @version 1.0 (2019/01/28)
 * @since 1.0
 */

import java.text.DecimalFormat;


public class Mark {

	/**
	 * MINIMUM GPA
	 */
	public static final float MINIMUM_GPA = 0.0f;
	
	/**
	 * MAXIMUM GPA
	 */
	public static final float MAXIMUM_GPA = 0.5f;
	
	/**
	 * GPA stored in decimal format
	 */
	public static final DecimalFormat gpaFormat = new DecimalFormat ("#0.00");
	
	/**
	 * Constant string stored as course code
	 */
	private static String courseCode = "WEBD4202";
	
	/**
	 * Constant string stored as course name
	 */
	private static String courseName = "Web Development - ENTERPRISE";

	

	/**
	 * store the student’s final result  in the course
	 */
	int result;
	
	/**
	 * holds GPA course weighting 
	 */
	float gpaWeighting;
	
	
	/**
	 * @return the minimumGpa
	 */
	public static float getMinimumGpa() {
		return MINIMUM_GPA;
	}
	/**
	 * @return the maximumGpa
	 */
	public static float getMaximumGpa() {
		return MAXIMUM_GPA;
	}

	/**
	 * @return the gpaformat
	 */
	public static DecimalFormat getGpaformat() {
		return gpaFormat;
	}
	/**
	 * @return the courseCode
	 */
	public static String getCourseCode() {
		return courseCode;
	}
	/**
	 * @param courseCode the courseCode to set
	 */
	public static void setCourseCode(String courseCode) {
		Mark.courseCode = courseCode;
	}
	/**
	 * @return the courseName
	 */
	public static String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public static void setCourseName(String courseName) {
		Mark.courseName = courseName;
	}
	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}
	/**
	 * @return the gpaWeighting
	 */
	public float getGpaWeighting() {
		return gpaWeighting;
	}
	/**
	 * @param gpaWeighting the gpaWeighting to set
	 */
	public void setGpaWeighting(float gpaWeighting) {
		this.gpaWeighting = gpaWeighting;
	}
	/**
	 * String output that displays the info in a formatted 
	 * output
	 */
	public String toString(){
			String output;
			output = "\n" + getCourseCode() + " " + getCourseName() + " " 
			+ "71" + " " + getGpaformat();
			String.format("%-35s" , getCourseName());
		return output;
	}	
	
}
