package webd4201.forrestere;

/**
 * Stores the data entered in the database.
 * @author eggme
 * @since 1.0 (2019/4/2)
 * @version 1.0
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.sql.*;

public class UpdateServlet extends HttpServlet
{
    /**
	 * private static final long serialVersionUID = 1L; ~ suppresses warning for file 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request,
                            HttpServletResponse response )
                                throws ServletException, IOException
   {
	try 
	{ /* 	retrieve the customer attribute from the session
			and cast it to an object of type Customer */

            User aUser = (User) request.getSession(false).getAttribute( "user");
            // get the name and address values from the HTML page
            
            /**
             * request user First Name
             */
            String firstName = request.getParameter("First Name");
            System.out.println("firstName:" + request.getParameter("First Name"));
            
            /**
             * request user Last Name
             */
            String lastName = request.getParameter("Last Name");
            System.out.println("lastname:" + request.getParameter("Last Name"));
            
            /**
             * request user Password
             */
            String password = request.getParameter("Password");
            System.out.println("password:" + request.getParameter("Password"));
            
            /**
             * request user Email Address
             */
            String emailAddress = request.getParameter("Email Address");
            System.out.println("emailAddress:" + request.getParameter("Email Address"));
            
           
          
            /* if id,firstName, lastnName, password  or emailAddress from HTML page does not match
             info in DB, update DB */
            if
            (!firstName.equals(aUser.getFirstName()) ||
            (!lastName.equals(aUser.getLastName()) ||
            (!password.equals(aUser.getPassword())||
            (!emailAddress.equals(aUser.getEmailAddress())))))  
            	
            {
                aUser.setFirstName(firstName);
                aUser.setLastName(lastName);
                aUser.setPassword(password);
                aUser.setEmailAddress(emailAddress);
                aUser.update();
            }
            // invoke the CustomerOptions.jsp program
            response.sendRedirect("./index.jsp");
        }catch (NotFoundException e)
		{	} //do nothing
    }
}