
/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#3 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 *
 * @author radra_000
 */

/**
 * The complexity class represents the bigOh complexity of a block of code for
 * this assignment the notation will be limited to n^x and log(n)^x where x
 * represents the power. The two variables npower and logpower will keep track
 * of what power each of the base types is present in the complexity object.
 */
public class Complexity {

    private int nPower;
    private int logPower;

    /**
     * Constructor method that will be used to set variables npower and logpower
     *
     * @param nPower int value representing the power of n, ie O(n^nPower)
     * @param logPower int value representing the power of log(n), ie
     * O(log(n)^logPower)
     */
    public Complexity(int nPower, int logPower) {
        this.nPower = nPower;
        this.logPower = logPower;
    }

    /**
     * empty constructor method
     */
    public Complexity() {
    }

    /**
     * getter method used to return the nPower value
     *
     * @return nPower variable of the type integer.
     */
    public int getnPower() {
        return nPower;
    }

    /**
     * assigns nPower(int) to the nPower inside this object.
     *
     * @param nPower int value representing the power of n, ie O(n^nPower)
     */
    public void setnPower(int nPower) {
        this.nPower = nPower;
    }

    /**
     * getter method used to return the logPower value(int)
     *
     * @return logPower variable of the type integer
     */
    public int getLogPower() {
        return logPower;
    }

    /**
     * assigns logPower(int) to the logPower variable inside this object
     *
     * @param logPower int value representing the power of log(n), ie
     * O(log(n)^logPower)
     */
    public void setLogPower(int logPower) {
        this.logPower = logPower;
    }

    /**
     * isGreater method will be used to simplify the processing of identfying,
     * weather a complexity is greater than the complexity of this object, by
     * comparing the variables npower and logpower
     *
     * @param complexity Complexity object to be compared with.
     * @return boolean; true if the complexity object passed in is greater,
     * false otherwise
     */
    public boolean isGreater(Complexity complexity) {
        if (this.nPower == complexity.getnPower()) {
            return this.logPower >= complexity.getLogPower();
        } else {
            return this.nPower > complexity.getnPower();
        }
        //if it returns true complexity is smaller
    }

    @Override
    /**
     * toString method overrides the toString method of the object class.
     *
     * @return String repesentation of the npower and logpower variables in the
     * format of O(n^npower * log(n)^logPower), considering some special cases
     * where either of these two variables are either 0 or 1.
     */
    public String toString() {
        String output = "";
        if (nPower + logPower == 0) {
            output = output.concat("1");
        } else if (nPower == 0 && logPower >= 1) {
            output = (logPower > 1) ? ("log(n)^" + logPower) : "log(n)";
        } else if (nPower >= 1 && logPower == 0) {
            output = (nPower > 1) ? ("n^" + nPower) : "n";
        } else if (nPower == 1 && logPower == 1) {
            output = "n * log (n)";
        } else {
            output = (nPower > 1) ? ("n^" + nPower) : "n";
            output = output + " * ";
            output += (logPower > 1) ? ("log(n)^" + logPower) : "log(n)";
        }
        return ("O(" + output + ")");
    }

    /**
     * overrides the default equals method from the object class. assigns the
     * values of the variables in the complexity object to this object
     *
     * @param complexity Complexity object whose variables will be assigned to
     * this object
     */
    public void equals(Complexity complexity) {
        this.nPower = complexity.nPower;
        this.logPower = complexity.logPower;
    }
}
