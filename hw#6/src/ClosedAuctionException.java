/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#6 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 *
 * @author radra_000
 */

public class ClosedAuctionException extends Exception{
    static String message = "The auction is closed";

    /**
     *ClosedAuctionException class is a subclass of the exception. this constructor
     * will be invoked when the user tries to access a auction that has already 
     * ended. we will used the super method to invoke the constructor of the 
     * exception class. 
     */
    public ClosedAuctionException() {
        super(message);
    }

    /**
     *exception class created to throw exception when the time of the auction 
     * has ended. 
     * if the empty constructor is called then the method will print the message
     * by extending the exception class. 
     * @param message
     */
    public ClosedAuctionException(String message) {   
        super(message);
    }
}
