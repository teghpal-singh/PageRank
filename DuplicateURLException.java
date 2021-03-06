/**
 * Teghpal Singh
 * 112224062
 * teghpal.singh@stonybrook.edu
 * Programming Assignment Number 7
 * CSE 214
 * Recitation Number 05
 * Teacher Assistant: Thomas Povinelli
 * 
 * Basically just creates a new Exception called 
 *   <code>DuplicateURLException</code> and extends to the 
 *   <code>RuntimeException</code> so it can implement the declared methods
 *   and actually print a message.
 */

public class DuplicateURLException extends RuntimeException 
{
	/**
	 * Eclipse IDE wrote this for me, basically I need it so Eclipse knows
	 *   that the class is a different version of the 
	 *   <code>RuntimeException</code>  
	 */
    static final long serialVersionUID = 1L;
    
    /**
     * Default constructor for <code>NotADirectoryException</code>
     */
    public DuplicateURLException()
    {
        super();
    }

    /**
     * Second constructor for <code>NotADirectoryException</code> that contains
     *   a String message indicating why the exception has been thrown
     * 
     * @param message - a message indicating what the exception is for
     */
    public DuplicateURLException(String message)
    {
        super(message);
    }
}