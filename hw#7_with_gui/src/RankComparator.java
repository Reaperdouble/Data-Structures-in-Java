
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
 * RankComparator class implements the comparator class. when called this class
 * will compare the ranks of two webpage objects and return an int value 
 * based on the comparison. 
 * @author radra_000
 */
public class RankComparator implements Comparator<WebPage> {
    /**
     * override of the compare method from the Comparator class which 
     * will compare ranks of the webpage object and return the int value 0,-1,1
     * based on weather o1 is equal to, less than, or greather than o2.
     * @param o1
     *     webpage object 1 whose rank to be compared with.
     * @param o2 
     *      webpage object 2 whose rank to be compared with.
     * @return
     *      integer values 0,1,-1 depending on weather ranks are equal, less than, or
     * greater
     */
    @Override
    public int compare(WebPage o1, WebPage o2) {
        if (o1.getRank() == o2.getRank()) {
            return 0;
        } else if (o1.getRank() < o2.getRank()) {
            return 1;
        } else {
            return -1;
        }
    }
}
