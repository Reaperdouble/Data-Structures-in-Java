
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
 * IndexComparator class implements the comparator class. when printing the table
 * the table will be sorted in ascending order of index. 
 */
public class IndexComparator implements Comparator<WebPage>{
    /**
     * override of the compare method of the Comparator class. it will compare
     * o1 and o2 and returns 0,1,-1 depending on weather if its equal, greater
     * than or less then respectively.
     * @param o1
     *     webpage object 1 whose index to be compared with.
     * @param o2 webpage object 2 whose index to be compared with.
     * @return integer values 0,1,-1 depending on weather equal, less than, or
     * greater
     */
    @Override
    public int compare(WebPage o1, WebPage o2) {
        if (o1.getIndex() == o2.getIndex()) {
            return 0;
        } else if (o1.getIndex() > o2.getIndex()) {
            return 1;
        } else {
            return -1;
        }
    }

}
