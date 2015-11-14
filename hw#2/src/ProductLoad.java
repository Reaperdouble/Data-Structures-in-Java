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
 * This class will be used to contain the details of the attributes that 
 * make up the product inside the train cars. The Attributes include 
 * Name, Weight in tons, Value in dollars, and the product type (dangerous or not)
 *
 */
public class ProductLoad {

    private String productName;
    private double weight = 0;
    private double contentValue = 0;
    private boolean productType = false;

    /**
     * creates a new ProductLoad object with given values Overloaded Constructor
     *
     * @param productName name of the product placed in the car
     * @param weight weight of that product in tons
     * @param contentValue Cost of the product in dollars
     * @param productType Weather the product is dangerous or not
     */
    public ProductLoad(String productName, double weight, double contentValue, boolean productType) {
        super();
        this.productName = productName;
        this.weight = weight;
        this.contentValue = contentValue;
        this.productType = productType;
    }

    /**
     * gets the name of the product
     *
     * @return the productName as a string
     */
    public String getProductName() {
        return productName;
    }

    /**
     * sets the productName variable of the object to the String value passed
     * in.
     *
     * @param productName name of the product entered by the user
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * gets the weight of the product
     *
     * @return the weight as a double
     */
    public double getWeight() {
        return weight;
    }

    /**
     * sets the weight variable of the object to the double value entered by the
     * user
     *
     * @param weight the weight of the product entered by user.
     * @throws IllegalArgumentException indicates when the value of weight
     * entered is negative
     */
    public void setWeight(double weight) throws IllegalArgumentException {
        if (weight < 0) {
            throw new IllegalArgumentException("The weight shouldnt be negative");
        }
        this.weight = weight;
    }

    /**
     * gets the content value stored in the object
     *
     * @return the contentValue as a double
     */
    public double getContentValue() {
        return contentValue;
    }

    /**
     * sets the contentWorth variable in the object to the value entered.
     *
     * @param contentValue the cost of the product entered by the user.
     * @throws IllegalArgumentException when the value is less than 0, the
     * exception is thrown, to indicate the value cannot be zero.
     */
    public void setContentValue(double contentValue) throws IllegalArgumentException {
        if (contentValue < 0) {
            throw new IllegalArgumentException("The value shouldnt be negative");
        }
        this.contentValue = contentValue;
    }

    /**
     * gets the product type weather dangerous or not
     *
     * @return the productType which is a boolean true or false
     */
    public boolean isProductType() {
        return productType;
    }

    /**
     * sets productType variable in the object with the value entered by user
     *
     * @param productType boolean variable containing the user input.
     */
    public void setProductType(boolean productType) {
        this.productType = productType;
    }

    /**
     * String representation of all the variables inside the ProductLoad object.
     * The string is formatted to display in a nice tabular form. overrides the
     * default toString method present in the object class.
     *
     * @return String representation of the ProductLoad Object
     */
    @Override
    public String toString() {
        String bool;
        if (isProductType() == true) {
            bool = "YES";
        } else {
            bool = "NO";
        }
        return String.format("%-15s%-13.2f%-13.2f%-10s", productName, weight,
                contentValue, bool);
    }
}
