/**
 * Rajith Radhakrishnan
 * 109061463
 * rajith.radhakrishnan@stonybrook.edu
 * HW#2 
 * CSE 214- R06 
 * TA - Frank Migliorino
 * GA - Yu Wang
 * @author radra_000
 */
/**
 * This class contain the attributes pertaining to a train car including,
 * Length in meters, weight in tons,load reference to ProductLoad class 
 * which contain the information about the load that is placed inside the cars 
 * additionally three static variables totalValue, totalWeight and dangerousCount
 * is used to keep track of the total
 *  worth of all product that is inside the train, and 
 * how many of them are dangerous. These static variables are used to avoid the 
 * use of for loops, when the worth or dangerous cars count is requested. They 
 * will be mainly modified from the TrainLinkedList class, since thats where 
 * user inputs about the cars are processed. 
 */
public class TrainCar {

    private double length;
    private double weight;
    private ProductLoad load;
    public static double totalValue = 0.0;
	//public static int dangerousCount = 0;
    //public static double totalWeight = 0.0;

    /**
     * Contructor method that will create a instance of the object with the
     * values entered by the user regarding the train car. The load variable is
     * not set, because the user will be entering it seperately
     *
     * @param length The length of the train car.
     * @param weight The weight of the train car.
     *
     */
    public TrainCar(double length, double weight) {
        this.length = length;
        this.weight = weight;
        //totalWeight += weight;
    }

    /**
     * Constructs an instance of the TrainCar with no object in it.
     */
    public TrainCar() {
    }

    /**
     * gets the load of the particular train car
     *
     * @return the load as a ProductLoad.
     */
    public ProductLoad getLoad() {
        return load;
    }

    /**
     * sets the load of the particular train car to the information entered by
     * the user. Since load variable passed by the user contain the value and
     * the product type, we will update the static variables that was declared
     * above
     *
     * @param load the load information of the particular car entered by user.
     */
    public void setLoad(ProductLoad load) {
        this.load = load;
        //totalWeight +=load.getWeight();
        System.out.println(load.getWeight() + " Tons of " + load.getProductName()
                + " has been added");
        totalValue += load.getContentValue();
    }

    /**
     * Takes the length of the particular train car
     *
     * @return the length of the car (meters) returned as a double
     */
    public double getLength() {
        return length;
    }

    /**
     * Takes the weight of the particular train car
     *
     * @return the weight of the train car(tons) as a double
     */
    public double getWeight() {
        return weight;
    }

    /**
     * tells weather the particular train car contains anything.
     *
     * @return If the load is null then returns true otherwise false;
     */
    public boolean isEmpty() {
        return load == null;
    }

    /**
     * String representation of the attributes that make up the train car.
     * Depending on weather the train is empty or not the string will be
     * changed. Overrides the toString method from the object class.
     *
     * @return String representation of the train car.
     */
    @Override
    public String toString() {
        if (load == null) {
            return String.format("%-10.2f%-10.2f%-2s%-15s%-13s%-13s%-10s",
                    length, weight, "|", "Empty", "0.0", "0.0", "NO");
        } else {
            return String.format("%-10.2f%-10.2f%-2s", length, weight,
                    "|") + load.toString();
        }
    }
}
