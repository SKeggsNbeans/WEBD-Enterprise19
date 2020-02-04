package webd4201.forrestere;
/**
 * duplicate existingID parameter and message
 * @author eggme
 * @version 1.0 (2019/4/2)
 * @since 1.0
 */
/**
 *@SuppressWarnings
 *("serial") is there to suppress warnings 
 * with in the project when using this a exception
 */
@SuppressWarnings("serial")
public class ExistingId extends Exception
{
	public ExistingId()
	{ super();}
	
	public ExistingId(String message)
	{ super(message);}
}