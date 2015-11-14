/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#5 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 * @author radra_000
 */
public class NotADirectoryException extends Exception{
    static String message = "This is not a directory";

    /**
     *This exception class will be accessed when the user tries to move the cursor
     * to a file node instead of a directory node. When the default constructor
     * is called the method will print the static message by extending the exception
     * class. 
     */
    public NotADirectoryException() {
        super(message);
    }

    /**
     *Overloaded constructor that will print out the String that was entered
     * when this method was called. 
     * @param message
     *      String message is the String entered when throwing this exception. 
     */
    public NotADirectoryException(String message) {
        super(message);
    }
    
    
}

