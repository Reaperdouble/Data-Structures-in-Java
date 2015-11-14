/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#5 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 * @author radra_000
 */
/**
 * exception class that will be accessed when a exception is thrown. The exception
 * is thrown when the directory/file does not exist in the particular name that
 * the user might be looking for. 
 */
public class NoSuchFileOrDirectoryException extends Exception{

    static String message = "There is no directory/file with the name you are searching"
            + "for";

    /**
     *Default constructor, print the static string message when its being
     * called by extending the exception class. 
     */
    public NoSuchFileOrDirectoryException() {
        super(message);
    }

    /**
     *Constructor method that will print the String message passed in, when 
     * this class was used in the DirectoryTree class or BashTerminal Class. 
     * @param message
     *      String message that was entered in the DirectoryNode or BashTerminal
     * class when the exception is thrown. 
     */
    public NoSuchFileOrDirectoryException(String message) {
        super(message);
    }
}
