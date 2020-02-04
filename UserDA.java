package webd4201.forrestere;

/**
 * CRUD method for the User while following the similar idea as the StudentDA;
 * StudentDA - this file is contains all of the data access methods, that actually get/set data to the database. 
 * Note: that all the methods are static this is because you do not really create StudentDA objects (does not make sense)~ Darren Puffer
 * @author eggme
 * @since 1.0 (2019/4/2)
 * @version 1.0
 */

import java.sql.*;

public class UserDA //extends StudentDA
{
	static User aUser;
	
	//Declare variable for database connection
	static Connection aConnection;
	static Statement aStatement;
	
	/**
	 * declare static variables for all Users instance attribute values
	 */
	static long id;
	static String password;
	static String firstName;
	static String lastName;
	static String emailAddress;
	static Date   lastAccess;
	static Date   enrolDate;
	static boolean enabled;
	static char   type;
	
	/**
	 * @param c
	 * Initializes the create statement for SQL
	 */
	public static void initialize (Connection c)
	{
		try
		{
			aConnection=c;
			aStatement=aConnection.createStatement();
		}
		catch(SQLException e)
		{System.out.println(e); }
	}
	
	/**
	 * Close the database connection
	 */
	public static void terminate()
	{
		try
		{
			aStatement.close();
			
		}
		catch(SQLException e)
		{System.out.println(e); }
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isExistingId(long id)
	{ 
		String sqlQuery = "SELECT * FROM Users WHERE id= ?";
		boolean exists = true;                  
		try
		{
			PreparedStatement psIsId = aConnection.prepareStatement(sqlQuery);
			psIsId.setLong(1, id);
			ResultSet rs = psIsId.executeQuery();
			exists = rs.next();
		}catch (SQLException e)
		{ 
			System.out.println(e);
		}
		return exists;
	}
	/**
	 * gets the id. if not found then throws not found exception
	 * @param key
	 * @return aUser
	 * @throws NotFoundException
	 */
	public static User retrieve(long key) throws NotFoundException
	{
		//Retrieve User and Float Data 
		aUser = null;
		// define the SQL query statement using the phone number key
		String sqlQuery = "SELECT Id, Password, FirstName, LastName, EmailAddress," +
				"LastAccess, EnrolDate, Enabled, Type FROM Users WHERE Id = ?";
		try
		{
			PreparedStatement psUserSelect = aConnection.prepareStatement(sqlQuery);
			psUserSelect.setLong(1, key);
			ResultSet rs = psUserSelect.executeQuery();
			
			boolean gotIt = rs.next();
			if(gotIt)
			{
				//Get the data
				id = rs.getLong("Id");
				password = rs.getString("Password");
				firstName = rs.getString("FirstName");
				lastName = rs.getString("LastName");
				emailAddress = rs.getString("EmailAddress");
				lastAccess = rs.getDate("LastAccess");
				enrolDate = rs.getDate("Enrol");
				type = rs.getString("Type").charAt(0);
				enabled = (boolean) rs.getBoolean("Enabled");
				
				try
				{
					aUser = new User();
				}catch (Exception e)
				{ 
					System.out.println("Record for " + firstName + " " + lastName + " contains an invalid Student ID.  Verify and correct.");
				}

			} else //if nothing was retrieved happens 
			{throw (new NotFoundException("Problem retrieving Student record, phone number " + key +" does not exist in the system."));}
			rs.close();
		}catch (SQLException e)
		{ System.out.println(e);}

		return aUser;
		}
	
	
		/**
		 * creates a new user and checks to see if the user is already created
		 * @param aStudent
		 * @return boolean
		 * @throws DuplicateException
		 */
		public static boolean create(User aUser) throws DuplicateException
		{	
			boolean inserted = false; //insertion success flag
			// retrieve the student attribute values
			id = aUser.getId();
			password = aUser.getPassword();
			firstName = aUser.getFirstName();
			lastName = aUser.getLastName();
			emailAddress = aUser.getEmailAddress();
			lastAccess = (Date)aUser.getLastAccess();
			type = aUser.getType();
			enabled = aUser.isEnabled();
			
			//Create the SQL insert statement using attribute values
			String sqlInsertUser = "INSERT INTO Users (Id, Password, FirstName," +
					"LastName, EmailAddress, LastAccess, EnrolDate, Type, Enabled)VALUES(" +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			try{
				aConnection.setAutoCommit(false);
				if(UserDA.create(aUser))	{
					
				
					PreparedStatement psInsertUser = aConnection.prepareStatement(sqlInsertUser);
					psInsertUser.setLong(1, id);
					psInsertUser.setString(2, password);
					psInsertUser.setString(3, firstName);
					psInsertUser.setString(4, lastName);
					psInsertUser.setString(5, emailAddress);
					psInsertUser.setDate(6, lastAccess);
					psInsertUser.setString(7,String.valueOf(type));
					psInsertUser.setDate(8, lastAccess);
					psInsertUser.setDate(9, enrolDate);
					psInsertUser.setBoolean(10, enabled);
					int recordsInserted = psInsertUser.executeUpdate();
					if(recordsInserted > 0)
						inserted = true;
					aConnection.commit();
				}else{
					inserted = false;
					aConnection.rollback();
				}
			
					System.out.println(sqlInsertUser);
					/*System.out.println(sqlInsertStudent);*/
					
					try
					{
						retrieve(id);
						throw (new DuplicateException("Problem with creating Student record, ID: " + id +" ,already exists in the system."));
					}
					// if NotFoundException, add student to database
					catch(NotFoundException e)
					{
						try
						{  
							inserted = sqlInsertUser.execute();
						}
						catch (SQLException ee)
						{ System.out.println(ee);	}
						
					}
					
				
				}
				catch (SQLException ee)
				{ System.out.println(ee);	}
			return inserted;
		}
			

			/**
			 * Deletes the user ad checks to see if the user exist befor deleting them
			 * @param aUser
			 * @return records
			 * @throws NotFoundException
			 */
			public static int delete(User aUser) throws NotFoundException
			{	
				int records = 0;
				// retrieve the phone no (key)
				id = aUser.getId();
				// create the SQL delete statement
				String sqlDelete = "DELETE FROM Users " +
						"WHERE Id = ?";

				// see if this student already exists in the database
				try
				{
					User.retrieve(id);  //used to determine if record exists for the passed Student
					// if found, execute the SQL update statement
					records = aStatement.executeUpdate(sqlDelete);
				}catch(NotFoundException e)
				{
					throw new NotFoundException("The USER with ID number " + id 
							+ " cannot be deleted, does not exist.");
				}catch (SQLException e)
				{ System.out.println(e);	}
				return records;
			}
			
			/**
			 * Updates the user and checks if the user exist before updating the user
			 * @param aUser
			 * @return records
			 * @throws NotFoundException
			 */
			public static int update(User aUser) throws NotFoundException
			{	
				int records = 0;  //records updated in method

				// retrieve the student argument attribute values
				id = aUser.getId();
				password = aUser.getPassword();
				firstName = aUser.getFirstName();
				lastName = aUser.getLastName();
				emailAddress = aUser.getEmailAddress();
				lastAccess = (Date)aUser.getLastAccess();
				enrolDate = (Date)aUser.getEnrolDate();
				enabled = aUser.isEnabled();
				type = aUser.getType();
				
				// define the SQL query statement using the user password
				try{
						User.retrieve(id);  //determine if there is a Student records to be updated
						// if found, execute the SQL update statement
						String sqlUpdate = "Update Users SET  Password = ?, FirstName = ?," +
						"LastName = ?,  EmailAddress = ?, LastAccess = ?, EnrolDate = ?,  Enable = ?, Type = ?,WHERE Id = ? ";
						
					    PreparedStatement psUpdateUser = aConnection.prepareStatement(sqlUpdate);
					    psUpdateUser.setLong(1, id);
					    psUpdateUser.setString(2, password);
					    psUpdateUser.setString(3, firstName);
					    psUpdateUser.setString(4,lastName );
					    psUpdateUser.setString(5, String.valueOf(type));
					    psUpdateUser.setBoolean(6,enabled);
					    records = psUpdateUser.executeUpdate();
				}catch(NotFoundException e)
				{
					throw new NotFoundException("User with ID number " + id 
							+ " cannot be updated, does not exist in the system.");
				}catch (SQLException e)
				{ System.out.println(e);}
				return records;
			}
			
			/**
			 * Checks to see i the user exist by checking the id and password
			 * @param key
			 * @param aPassword
			 * @return aUser
			 * @throws NotFoundException
			 */
			public static User authenticate(long key, String aPassword) throws NotFoundException
			{ // retrieve User and Boat data
				aUser = null;
				// define the SQL query statement using the phone number key
				String sqlQuery = "SELECT * FROM Users WHERE Id = ? AND Password = ? ";
				
				//System.out.println(sqlQuery);
				// execute the SQL query statement
				try
				{
					PreparedStatement psLogin = aConnection.prepareStatement(sqlQuery);
					psLogin.setLong(1, key);
					psLogin.setString(2, aPassword);
				    
					ResultSet rs = psLogin.executeQuery();
					// next method sets cursor & returns true if there is data
					boolean gotIt = rs.next();
					if (gotIt)
					{	 
					//extract the data
						id = rs.getLong("Id");
						password = rs.getString("Password");
						firstName = rs.getString("FirstName");
						lastName = rs.getString("LastName");
						emailAddress = rs.getString("EmailAddress");
						enrolDate = rs.getDate("EnrolDate");
						enabled = rs.getBoolean("Enabled");
						lastAccess = rs.getDate("LastAccess");
						type = rs.getString("Type").charAt(0);
					
					    /*psQuery.setLong(1,id);
					    psQuery.setString(2,password);
					    psQuery.setString(3,firstName);
					    psQuery.setString(4,lastName);
					    psQuery.setString(5,emailAddress);
					    psQuery.setDate(6,enrolDate);
					    psQuery.setBoolean(7,enabled);
					    psQuery.setDate(8, lastAccess);
					    psQuery.setString(9,String.valueOf(type));
					    psQuery.execute();*/
					
					// create User
						try{
							aUser = new User (/*id, password, firstName, lastName, emailAddress,enrolDate, enabled, type*/);
						}catch (Exception e)
						{ System.out.println("Record for " + firstName + lastName + " contains an invalid User ID.  Verify and correct.");}

					} else	// nothing was retrieved
					{throw (new NotFoundException("Problem retrieving User record, phone number " + key +" does not exist in the system."));}
					rs.close();
				}catch (SQLException e)
				{ System.out.println(e);}

				return aUser;
			}
			
		}