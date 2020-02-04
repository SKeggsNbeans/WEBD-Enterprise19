package webd4201.forrestere;

/**
 * StudentDA - this file is contains all of the data access methods, that actually get/set data to the database. 
 * Note: that all the methods are static this is because you do not really create StudentDA objects (does not make sense)
 * @author Darren Puffer
 * @version 2.0 (2019/4/2)
 * @since 1.0
 * @edit 
 */

import java.sql.*;


public  class StudentDA extends UserDA
{
	static Student aStudent;
	//NotFoundException was "e" and i changed it to "k" do i have to change them all to "k"

	// declare variables for the database connection
	static Connection aConnection;
	static Statement aStatement;

	static long id;
	static String programCode;
	static String programDescription;
	static int    year;
	

	// establish the database connection
	public static void initialize(Connection c)
	{
		try {
			aConnection=c;
			aStatement=aConnection.createStatement();
		}
		catch (SQLException e)
		{ System.out.println(e);	}
	}

	// close the database connection
	public static void terminate()
	{
		try
		{ 	// close the statement
			aStatement.close();
		}
		catch (SQLException e)
		{ System.out.println(e);	}
	}
	
	/**
	 * Retrieves the student id and checks to see if is empty
	 * @param key
	 * @return aStudent
	 * @throws NotFoundException
	 */
	public static Student retrieve(long key) throws NotFoundException
	{ // retrieve Student and Float data
		aStudent = null;
		// define the SQL query statement using the phone number key
		String sqlQuery = "SELECT Id, ProgramCode, ProgramDescription , Year FROM Students, Users  " +
				          " WHERE Id = users.id ";
		//System.out.println(sqlQuery);
		// execute the SQL query statement
		try
		{
			PreparedStatement psStudentSelect = aConnection.prepareStatement(sqlQuery);
			
			psStudentSelect.setLong(1,key);
			psStudentSelect.setString(2,programCode);
			psStudentSelect.setString(3,programDescription);
			psStudentSelect.setInt(4,year);
			ResultSet rs = psStudentSelect.executeQuery();
			
			boolean gotIt = rs.next();
			if (gotIt)
			{	// extract the data
				id = rs.getLong("Id");
				/*password = rs.getString("Password");
				firstName = rs.getString("FirstName");
				lastName = rs.getString("LastName");
				emailAddress = rs.getString("EmailAddress");
				lastAccess = rs.getDate("LastAccess");
				enrolDate = rs.getDate("EnrolDate");
				type = (char) rs.getShort("Type");
				enabled = (boolean) rs.getBoolean("Enabled");*/
				programCode = rs.getString("ProgramCode");
				programDescription = rs.getString("ProgramDescription");
				year = rs.getInt("Year");

				// create Student
				try{
					aStudent = new Student();
				}catch (Exception e)
				{ 
					System.out.println("Record for " + id +" contains an invalid Student ID.  Verify and correct.");
				}

			} else	// nothing was retrieved
			{throw (new NotFoundException("Problem retrieving Student record, phone number " + key +" does not exist in the system."));}
			rs.close();
		}catch (SQLException e)
		{ System.out.println(e);}

		return aStudent;
	}

	/**
	 * Retrieves the student id and checks to see if if exist
	 * @param aStudent
	 * @return boolean
	 * @throws DuplicateException
	 */
	public static boolean create(Student aStudent) throws DuplicateException
	{	
		boolean inserted = false; //insertion success flag
		// retrieve the student attribute values
		id = aStudent.getId();
		programCode = aStudent.getProgramCode();
		programDescription = aStudent.getProgramDescription();
		year = aStudent.getYear();

		// create the SQL insert statement using attribute values
		String sqlInsertStudent = "INSERT INTO Students (Id, ProgramCode, ProgramDescription, Year)VALUES(" + "?, ?, ?, ?)";
		//PreparedStatement psUpdateStudent = aConnection.prepareStatement(sqlUpdate);
		try{
			aConnection.setAutoCommit(false);
			if(UserDA.create(aStudent))	{
			
				PreparedStatement psInsertStudent = aConnection.prepareStatement(sqlInsertStudent);
				psInsertStudent.setLong(1, id);
				psInsertStudent.setString(2, programCode);
				psInsertStudent.setString(3, programDescription);
				psInsertStudent.setInt(4, year);	
				int recordsInserted = psInsertStudent.executeUpdate();
				if (recordsInserted > 0){
					inserted = true;
					aConnection.commit();
				}else{
					inserted = false;
					aConnection.rollback();
				}
					 
		
			/*	System.out.println(sqlInsertUser);*/
				System.out.println(sqlInsertStudent);
				
				try
				{
					retrieve(id);
					throw (new DuplicateException("Problem with creating Student record, Student: " + id +" ,already exists in the system."));
				}
				// if NotFoundException, add student to database
				catch(NotFoundException e)
				{
					try
					{  // execute the SQL update statement
						//inserted = aStatement.execute(sqlInsertUser);
						//inserted = aStatement.execute(sqlInsertStudent);
						inserted = psInsertStudent.execute();
					}
					catch (SQLException ee)
					{ System.out.println(ee);	}
					
				}
			}else{
				inserted = false;
			}
		}
		catch (SQLException ee)
		{ System.out.println(ee);	}
	return inserted;
}
	
	/**
	 * this will delete aStudent and check to see if the user exist before 
	 * deleting the student
	 * @param aStudent
	 * @return records 
	 * @throws NotFoundException
	 */
	public static int delete(Student aStudent) throws NotFoundException
	{	
		int records = 0;
		// retrieve the phone no (key)
		id = aStudent.getId();
		// create the SQL delete statement
		String sqlDelete = "DELETE FROM Students = ?" +
				"WHERE Id = ?";

		// see if this student already exists in the database
		try
		{
			Student.retrieve(id);  //used to determine if record exists for the passed Student
			// if found, execute the SQL update statement
			records = aStatement.executeUpdate(sqlDelete);
		}catch(NotFoundException e)
		{
			throw new NotFoundException("Student with ID number " + id 
					+ " cannot be deleted, does not exist.");
		}catch (SQLException e)
		{ System.out.println(e);	}
		return records;
	}


	/**
	 * This will update the aStudent 
	 * @param aStudent
	 * @return records
	 * @throws NotFoundException
	 */
	public static int update(Student aUser) throws NotFoundException
	{	
		int records = 0;  //records updated in method

		// retrieve the student argument attribute values
		id = aStudent.getId();
		programCode = aStudent.getProgramCode();
		programDescription = aStudent.getProgramDescription();
		year = aStudent.getYear();
		
		// define the SQL query statement using the program code
		try{
			try
			{
				Student.retrieve(id);  //determine if there is a Student records to be updated
				// if found, execute the SQL update statement
			String sqlUpdate = "Update Students SET ProgramCode  = ?, programDescription = ?, Year = ?" +
					           " WHERE Id = ? ";
					
		    PreparedStatement psUpdateStudent = aConnection.prepareStatement(sqlUpdate);
		    psUpdateStudent.setLong(1, id);
		    psUpdateStudent.setString(2, programCode);
		    psUpdateStudent.setString(3, programDescription);
		    psUpdateStudent.setInt(4, year);
		    records = psUpdateStudent.executeUpdate();

			}
	
			//NotFoundException was "e" and i changed it to "k" do i have to change them all to "k"
			catch(NotFoundException k)
			{
				throw new NotFoundException("Student with ID number " + id 
						+ " cannot be updated, does not exist in the system.");
			}
		}catch (SQLException k)
		{ System.out.println(k);}
		return records;
	}
	
	/**
	 * 
	 * @param key
	 * @param aPassword
	 * @return aStudent
	 * @throws NotFoundException
	 */
	public static Student authenticate(long key, String aPassword) throws NotFoundException
	{ // retrieve User and Boat data
		aStudent = null;
		// define the SQL query statement using the phone number key
		String sqlQuery = "SELECT * FROM Users WHERE Id = ? AND Password = ? ";
		
		//System.out.println(sqlQuery);
		// execute the SQL query statement
		try
		{
			PreparedStatement psLogin = aConnection.prepareStatement(sqlQuery);
			psLogin.setLong(1, key);
			psLogin.setString(2, programCode);
			psLogin.setString(2, programDescription);
			psLogin.setInt(2, year);
			ResultSet rs = psLogin.executeQuery();
			// next method sets cursor & returns true if there is data
			boolean gotIt = rs.next();
			if (gotIt)
			{	// extract the data
				id = rs.getLong("Id");
				password = rs.getString("Password");
				// create Student
				try{
					aStudent = new Student();
				}catch (Exception e)
				{ System.out.println("Record for " + id + " contains an invalid Student ID.  Verify and correct.");}

			} else	// nothing was retrieved
			{throw (new NotFoundException("Problem retrieving Student record, phone number " + key +" does not exist in the system."));}
			rs.close();
		}catch (SQLException e)
		{ System.out.println(e);}

		return aStudent;
	}
	
}


