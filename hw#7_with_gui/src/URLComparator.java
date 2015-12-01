
import java.util.Comparator;

/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#7 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 */
/**
 * URLComparator class implements the comparator class. when called this class
 * will compare the letters of URL of two webpage objects and return an int value 
 * based on the comparison. 
 * @author radra_000
 */
public class URLComparator implements Comparator<WebPage>{
    /**
     * override of the compare method of the Comparator class. it will compare
     * o1 and o2 and returns 0,1,-1 depending on weather if its equal, greater
     * than or less than alphabetically respectively.
     * @param o1
     *     webpage object 1 whose alphabets in url to be compared with.
     * @param o2
     *     webpage object 2 whose alphabets in url to be compared with.
     * @return
     *     integer values 0,1,-1 depending on weather equal, less than, or
     * greater in terms of alphabetical order
     */
    @Override
    public int compare(WebPage o1, WebPage o2) {
          return o1.getUrl().compareToIgnoreCase(o2.getUrl());
    }
    
}
