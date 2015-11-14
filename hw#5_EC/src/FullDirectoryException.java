/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#5 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 * @author radra_000
 */
public class FullDirectoryException extends Exception{
     static String message = "This is directory is full, the node contains 3 "
             + "childrens already";

    /**
     *exception class created to throw exception when the directory is full, ie 
     * has three children. 
     * if the empty constructor is called then the method will print the message
     * by extending the exception class. 
     */
    public FullDirectoryException() {
        super(message);
    }

    /**
     *If the exception is thrown with a string passed in then that string will 
     * be displayed to the user. 
     * @param message
     *      String passed in when using this constructor in the DirectoryTree
     * class and BashTerminal Class. 
     */
    public FullDirectoryException(String message) {
        super(message);
    }
}
