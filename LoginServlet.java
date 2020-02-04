package webd4201.forrestere;
/**
 * Establishes connection to the database and redirects the user to a new page.
 * @author eggme
 * @since 1.0 (2019/4/2)
 * @version 1.0
 */

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.sql.*;


public class LoginServlet extends HttpServlet {

    /**
	 * private static final long serialVersionUID = 1L ~ suppress warning 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request,
                            HttpServletResponse response) 
					throws ServletException, IOException
    {
	   	  
	   	//CREATE A TEXT FILE 
        try
        { 
            // connect to database
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);
            HttpSession session = request.getSession(true);
            String login = new String();
            long id = 0l;
            StringBuffer errorBuffer = new StringBuffer();
            
            try 
            {   // retrieve data from DB
                login = request.getParameter( "Login" ); //this is the name of the text input box on the form
                System.out.println("Login stores: " + login);
                id = Long.parseLong(login);
                Student aStudent = Student.retrieve(id); //attempt to find the Student, this could cause a NotFoundException
                // if the Student was found and retrieved from the db
				//put the found Student onto the session
                session.setAttribute("student", aStudent);
				//empty out any errors if there were some
                session.setAttribute("errors", "This is not good, Try again please.");
         
                // redirect the user to a JSP
                response.sendRedirect("./studentWelcome.jsp");
            }catch( NumberFormatException nfe)
            {
                //new code == way better, if I do say so myself
                //sending errors to the page thru the session
                errorBuffer.append("<strong>Your id is your student number, therefore <i>"+login+"</i> is not valid.<br/>");
                errorBuffer.append("Please try again.</strong>");
                session.setAttribute("errors", errorBuffer.toString());
                session.setAttribute("login", "");
                response.sendRedirect("./login.jsp");
            
            //for the first deliverable you will have to check if there was a problem
            //with just the password, or login id and password
            //this will require a special method e.g. public static boolean isExistingLogin(String arg);
            }catch( NotFoundException nfe)
            {
                //new code == way better, if I do say so myself
                //sending errors to the page thru the session
                errorBuffer.append("<strong>Your sign in information is not valid.<br/>");
                errorBuffer.append("Please try again.</strong>");

                session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./login.jsp");
            
            //for the first deliverable you will have to check if there was a problem
            //with just the password, or login id and password
            //this will require a special method e.g. public static boolean isExistingLogin(String arg);
            }
        }    
   	 catch (Exception e) //not connected
        {
            System.out.println(e);
            String line1="<h2>A network error has occurred!</h2>";
            String line2="<p>Please notify your system " +
                                                    "administrator and check log. "+e.toString()+"</p>";
            formatErrorPage(line1, line2,response);
        }
    }
    public void doGet(HttpServletRequest request,
                            HttpServletResponse response)
                                    throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @param first
     * @param second
     * @param response
     * @throws IOException
     */
    public void formatErrorPage( String first, String second,
            HttpServletResponse response) throws IOException
    {
        PrintWriter output = response.getWriter();
        response.setContentType( "text/html" );
        output.println(first);
        output.println(second);
        output.close();
    }
}