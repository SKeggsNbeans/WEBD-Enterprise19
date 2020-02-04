package webd4201.forrestere;
/**
 * @author eggme
 * @version 1.0 (2019/04/09)
 * @since 1.0
 */
/**
 * This file contains extends the generic Exception class so that
 * we have a custom Exception, this one will be used to flag an attempt
 * at a duplicate record in our database
 * @author Darren Puffer
 * @version 1.0 
 * @since 1.0
 */ 

/**
 * @author eggme
 *duplicate exception parameter and message
 *
 *@SuppressWarnings
 *("serial") is there to suppress warnings 
 * with in the project when using this a exception
 */
@SuppressWarnings("serial")
public class DuplicateException extends Exception
{
    public DuplicateException()
    { super();}
    
    public DuplicateException(String message)
    { super(message);}
}