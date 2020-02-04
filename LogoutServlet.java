package webd4201.forrestere;

/**
 * Ends the session that was created between the LoginServlet and the database.
 * @author eggme
 * @since 1.0 (2019/4/2)
 * @version 1.0
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")

public class LogoutServlet extends HttpServlet 
{public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException{HttpSession session = request.getSession(true); 
	//retrieve the session (or start)   
	session.removeAttribute("aUser");
	//remove the object stored at login session.
	session.setAttribute("message","You have successfully logged out");          
	//give an informational 
	response.sendRedirect("./index.jsp");  // redirect to index    }}
	}
}
