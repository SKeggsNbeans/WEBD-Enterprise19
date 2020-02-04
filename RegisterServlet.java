package webd4201.forrestere;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

/**
 * Takes data that fits the criteria of the variables and stores it in a database.
 * @author eggme
 * @since 1.0 (2019/4/2)
 * @version 1.0
 */

public class RegisterServlet extends HttpServlet {
	
	static final long  MINIMUM_ID_NUMBER = User.MINIMUM_ID_NUMBER;
	private static final long serialVersionUID = 1L;
	private static final byte MININUM_PASSWORD_LENGTH = User.MININUM_PASSWORD_LENGTH;
	private static final byte MAXIMUN_PASSWORD_LENGTH = User.MAXIMUN_PASSWORD_LENGTH;
	private static final long MAX_ID_LENGTH = CollegeInterface.MAX_ID_LENGTH;
	private static final long MIN_ID_LENGTH = CollegeInterface.MIN_ID_LENGTH;
	private static final long MIN_PROGRAM_CODE = CollegeInterface.MIN_PROGRAM_CODE;
	private static final long MAX_PROGRAM_CODE = CollegeInterface.MAX_PROGRAM_CODE;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            Connection c = DatabaseConnect.initialize();
            User.initialize(c);
            HttpSession session = request.getSession();
            StringBuffer errorBuffer = new StringBuffer();
            boolean anyErrors = false;

            String login = new String();
            long id = 0;
            String password = new String();
            String firstname = new String();
            String lastname = new String();
            String emailaddress = new String();
            String programcode = new String();
            String programdescription = new String();
            String year = new String();
            
            try {
               login = request.getParameter("login").trim();
               password = request.getParameter("password").trim();
               firstname = request.getParameter("firstname").trim();
               lastname = request.getParameter("lastname").trim();
               emailaddress = request.getParameter("emailaddress").trim();
               programcode = request.getParameter("programcode").trim();
               programdescription = request.getParameter("programdescription").trim();
               year = request.getParameter("year").trim();

               
               //Ensuring the password is not empty
                
               if(login.length() == 0){
                   errorBuffer.append("You must provide your student number as your id.");
                   anyErrors = true;

               }else {
                   try {
                       id = Long.parseLong(login);
                       
                       //Ensuring the id number is not less then the minimum id length 
                        
                       if(id < MINIMUM_ID_NUMBER) {
                           errorBuffer.append("Your id is less than " + MINIMUM_ID_NUMBER);
                           anyErrors = true;
                       }
                       
                       //Ensuring the user or student id isn't already used
                        
                       if (User.isExistingId(id)) {
                           errorBuffer.append("User Number is already in use. Try another one.");
                           anyErrors = true;
                       }
                       
                       //Sees if the user is logged in and if the id is valid
                       
                   } catch (NumberFormatException nfe){
                       errorBuffer.append("Your id is your student number, therefore ");
                       errorBuffer.append(login);
                       errorBuffer.append(" is not a valid id.");
                       anyErrors = true;
                   }
               }
              
               //Ensuring the id number is not less then the minimum password length      
               
               if(password.length() < MININUM_PASSWORD_LENGTH){
                   errorBuffer.append("Your password must be more than" + MININUM_PASSWORD_LENGTH + " characters");
                   anyErrors = true;

               }else {
            	  
            	    //Ensuring the id number is not more then the maximum password length
            	   
                   if( password.length() > MAXIMUN_PASSWORD_LENGTH ) {
                       errorBuffer.append("Your password is more than " + MAXIMUN_PASSWORD_LENGTH);
                       anyErrors = true;
                   }
                   
               }   
               
                //Fit criteria for max id length
                
               if(firstname.length() > MAX_ID_LENGTH){
                   errorBuffer.append("You must provide your student number as your id.");
                   anyErrors = true;
               }
               
                //Fit criteria for min id lenght 
                
               if(lastname.length() < MIN_ID_LENGTH){
                   errorBuffer.append("You must provide your student number as your id.");
                   anyErrors = false;

               }  
               
                 //Fit criteria for emailAdress is not empty
                
               if(emailaddress.length() != 0){
                   errorBuffer.append("You must provide your student number as your id.");
                   anyErrors = true;
               }
               
                //Fit criteria for min 
                
               if(programcode.length() == MIN_PROGRAM_CODE){
                   errorBuffer.append("Your program code must be more than 0 characters");
                   anyErrors = false;

               }else {
                   if( programcode.length() < MAX_PROGRAM_CODE) {
                       errorBuffer.append("Your program code is less than 5");
                       anyErrors = true;
                   }
                   
               } 
               
                // Fit criteria for 
                
                   if( programdescription.length() < 20 ) {
                       errorBuffer.append("Your program description is less than 20");
                       anyErrors = true;
                   }
                   
                   
             if (anyErrors == false) {
                try  {
                  password = User.hashedPassword(password);
                   Student aStudent = new Student();
                   
                    // StudentDA.create(aStudent);
                    
                   
                   aStudent.setId(id);
                   aStudent.setPassword(password);
                   aStudent.setFirstName(firstname);
                   aStudent.setLastName(lastname);
                   aStudent.setEmailAddress(emailaddress);
                   aStudent.setType('s');
                   
                   aStudent.setProgramCode(programcode);
                   aStudent.setProgramDescription(programdescription);
                   aStudent.setYear(Integer.parseInt(year));
                   StudentDA.create(aStudent);
                   
                   } 
                catch (Exception e){
                e.printStackTrace();
                
                }
               }

         
             //A connection was not established 
          
        } catch (Exception e) 
        {
            System.out.println(e);
            String line1 = "<h2>A network error has occurred!</h2>";
            String line2 = "<p>Please notify your system " + "administrator and check log. " + e.toString() + "</p>";
            formatErrorPage(line1, line2, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
            doPost(request, response);
    }


    public void formatErrorPage(String first, String second, HttpServletResponse response) throws IOException {
        PrintWriter output = response.getWriter();
        response.setContentType("text/html");
        output.println(first);
        output.println(second);
        output.close();
    }
}
