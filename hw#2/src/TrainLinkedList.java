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
 * The TrainLinkedList class implements a double linked list ADT. It contains
 * references to head, tail of the list which of type TrainCarNode. There is
 * also a cursor that will be used to point/show the node in the list. It
 * provides various methods to perform insertion, deletion, search, print,
 * moving back and forth etc.
 */
public class TrainLinkedList {

    private TrainCarNode head;// = new TrainCarNode();
    private TrainCarNode tail;// = new TrainCarNode();
    private TrainCarNode cursor;// = new TrainCarNode();
    private int count = 0;
    private double totalLength = 0.0;
    private double totalWeight = 0.0;
    private int dangerousCount = 0;

    /**
     * constructs a instance of the TrainLinkedList object with all variables
     * set to null and the count will be set to 0;
     */
    public TrainLinkedList() {
        super();
        this.head = null;
        this.tail = null;
        this.cursor = null;
        count = 0;
    }

    /**
     * Increases the total weight of the train when a load is added with certain
     * weight, weight will sometimes be passed as negative, if in case the user
     * replaces the load that is in the cart.
     *
     * @param weight weight of the load entered by the user.
     */
    public void updateTotalWeight(double weight) {
        totalWeight += weight;
    }

    /**
     * increases the dangerous count when a dangerous material is added to the
     * train. if this method is called and bool if false, that means either
     * there was not a dangerous material present earlier, or if the car is null
     * the dangerous count will be incremented.
     *
     * @param bool Boolean values, when its true that means there was a
     * Dangerous material present in the cart before.
     */
    public void updateDangerousCount(boolean bool) {
        if (cursor.getCar().getLoad() == null) {
            dangerousCount++;
        } else if (bool) {
            dangerousCount--;
        }
    }

    /**
     * gets the car that is enclosed in the node
     *
     * @return Information about the car of the type TrainCar
     */
    public TrainCar getCursorData() {
        return cursor.getCar();

    }

    /**
     * sets the information about the car to the node where is cursor is pointed
     * at.
     *
     * @param car information about the car that the user entered.
     */
    public void setCursorData(TrainCar car) {
        cursor.setCar(car);
    }

    /**
     * Moves the cursor to the next linked node. Checks if the cursor is not at
     * the end of the list. If it is then no action will be taken, and prints
     * out a caution to the user.
     */
    public void cursorForward() {
        if (cursor != tail) {
            cursor = cursor.getNext();
            System.out.println("The cursor is moved foward");
        } else {
            System.out.println("The cursor is at the end of the list");
        }
    }

    /**
     * Moves the cursor to the previous linked node. Checks if the cursor is not
     * at the start/head of the list. if its is then no action will be taken,
     * and just prints out a caution to the user.
     */
    public void cursorBackward() {
        if (cursor != head) {
            cursor = cursor.getPrevious();
            System.out.println("The cursor is moved backward");
        } else {
            System.out.println("The cursor is at the head of the list");
        }
    }

    /**
     * Inserts the car, right after the node to which the cursor is pointed at.
     * The method will break the existing link, if there is any, and add the new
     * car and relink the cars together, while taking precautions not to lose
     * the link to the list completely. First checks if the car is not null, to
     * avoid null pointer exceptions later, and then uses a different algorithm
     * for different circumstances, such as empty list, only one item in the
     * list and just insert in the middle. Also updates the total length and
     * weight of the train, and moves the cursor to the new item that is added.
     *
     * @param newCar TrianCar information like length and weight entered by the
     * user.
     * @throws IllegalArgumentException when the newCar is null, an exception is
     * thrown to avoid null pointer exceptions when the list is accessed in the
     * future.
     */
    public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException {
        if (newCar == null) { // checking if the car is null
            throw new IllegalArgumentException("The car has null");
        } else {
            TrainCarNode newNode = new TrainCarNode();
            newNode.setCar(newCar);
            totalLength += newCar.getLength();
            totalWeight += newCar.getWeight();
			// if(cursor == head||cursor == tail){//checking if cursor is at
            // head
            if (head == null) {// checking if the list is empty
                cursor = newNode;
                head = newNode;
                tail = newNode;
            } else {// if there is a content in head already
                if (cursor.getNext() == null) {// check if its at tail
                    cursor.setNext(newNode);
                    newNode.setPrevious(cursor);
                    tail = newNode;
                } else {// if its to add somewhere in the middle
                    newNode.setNext(cursor.getNext());
                    newNode.setPrevious(cursor);
                    cursor.setNext(newNode);
                    cursor.getNext().getNext().setPrevious(newNode);
                }
            }
            count++;
            cursor = newNode; // check if its right
            System.out.println("New trian car with length " + newCar.getLength()
                    + "m and weight " + newCar.getWeight() + " tons has been added");
        }
    }

    /**
     * removes the node, which encloses a train car, that is at the location at
     * where the cursor is pointed/referenced. Before deleting the node the car
     * referenced by the cursor, will be saved to the temp variable of type
     * TrainCar. Depending on weather the cursor is head or tail or the middle
     * of the list, different algorithm will be performed to remove the node.
     *
     * @return temp that was removed which is of the type TrainCar
     * @throws NullPointerException catches null pointer exception which is
     * thrown where there are no more items in list.
     */
    public TrainCar removeCursor() {
        TrainCar temp = new TrainCar();
        try {
            temp = cursor.getCar();
            if (temp.getLoad() != null) {
                TrainCar.totalValue -= temp.getLoad().getContentValue();// check it
                if (temp.getLoad().isProductType() == true) {
                    dangerousCount--;
                }
            }
            totalLength -= temp.getLength();
            totalWeight -= temp.getWeight();
            totalWeight -= temp.getLoad().getWeight();

            if (cursor == head) {
                head = cursor.getNext();
                cursor = head;

            } else if (cursor == tail) {
                tail = cursor.getPrevious();
				// cursor.getNext().setPrevious(null);
                // cursor.setNext(null);
                cursor = tail;
            } else {// ask if i have to make the pointer null
                cursor.getNext().setPrevious(cursor.getPrevious());
                cursor.getPrevious().setNext(cursor.getNext());
                cursor = cursor.getPrevious();
            }
            if (count > 0) {
                count--;
            }

        } catch (Exception e) {
            System.out.println("NONE");
        }
        return temp;
    }

    /**
     * take the count of the links in the list.
     *
     * @return The count of the type int.
     */
    public int size() {
        return count;
    }

    /**
     * gets the total length of the train. operation completes in O(1)
     *
     * @return totalLength of the type double.
     */
    public double getLength() {
        return totalLength;
    }

    /**
     * gets the total value of the contents in the train. The totalValue is a
     * static variable declared in the TrainCar class.
     *
     * @return TrainCar.totalValue which of the type double
     */
    public double getValue() {
        return TrainCar.totalValue;
    }

    /**
     * gets the total weight of the just the train not including the weight of
     * the contents inside the them.
     *
     * @return totalWeight of the train of the type Double
     */
    public double getWeight() {
        return totalWeight;
    }

    /**
     * checks weather there are any dangerous cars present in the train. This is
     * done by checking the Static variable dangerousCount which was declared in
     * the TrainCar class.
     *
     * @return boolean value, depending of weather the dangerousCount is 0 or
     * not
     */
    public boolean isDangerous() {
        return (dangerousCount != 0);
    }

    /**
     * goes through the whole list to see if there is any product with the
     * searched name is present in any of the cars in the train. If there is
     * then it prints the number of cars, weight, worth and weather it is
     * dangerous or not.
     *
     * @param name name is the product name to be searched in the cars in the
     * list.
     */
    public void findProduct(String name) {
        TrainCarNode temp;// = new TrainCarNode();
        temp = cursor;
        int carCount = 0;
        double weight = 0;
        double value = 0;
        boolean isDangerous = false;
        cursor = head; // check weather i have to use .equal
        for (int i = 1; i <= count; i++) {
            if (cursor.getCar().getLoad().getProductName().equals(name)) { // check															// equals
                weight += cursor.getCar().getLoad().getWeight();
                value += cursor.getCar().getLoad().getContentValue();
                carCount++;
                isDangerous = (isDangerous || cursor.getCar().getLoad().isProductType());
            }
            cursor = cursor.getNext();
        }
        String bool;
        if (isDangerous == false) {// making the ouput for dangerous? yes or no
            bool = "not Dangerous";
        } else {
            bool = "Dangerous";
        }
        if (weight > 0) {
            System.out.println("The product " + name + " was found in " + carCount + " car(s)" + " with a Total weight of "
                    + weight + " tons, with total worth of " + value + " dollars and the content is " + bool);
        } else {
            System.out.println("There was no product named " + name);
        }
        cursor = temp;
    }

    /**
     * This method will be used to print out the details of the all the cars in
     * the list in a tabular form. If a certain link is where the cursor is
     * pointed at, a astrix (*) symbol will be placed in the front to denote it.
     * This method will call the toString methods in TrainCarNode, and the
     * classes below, and the information will be printed.
     */
    public void printManifest() {
        TrainCarNode temp;// = new TrainCarNode();
        temp = cursor;
        cursor = head;
        String cursorPointer;
        System.out.println(String.format("%-25s%-2s%-60s", "Car:", "|", "Load:"));
        System.out.println(String.format("%-5s%-10s%-10s%-2s%-15s%-13s%-13s%-10s", "Num", "Length(m)", "Weight(t)", "|",
                "Name", "Weight(t)", "Value($)", "Dangerous"));
        System.out.println(
                "=========================+==========================" + "=====================================");
        for (int i = 1; i <= count; i++) {
            if (cursor == temp) {
                cursorPointer = "*";
            } else {
                cursorPointer = " ";
            }
            System.out.println(String.format("%-1s%-4s", cursorPointer, i) + cursor.toString());
            cursor = cursor.getNext();
        }
        cursor = temp;
    }

    /**
     * Removes all the dangerous cars in the train. Goes through the list and
     * checks to see if any car contains dangerous material, if it does it calls
     * the removeCursor method that was declared above to remove them. The while
     * loop will move the cursor to point to a non dangerous node, if the node
     * that its currently pointing to was found dangerous
     */
    public void removeDangerousCars() {
        TrainCarNode temp;// = new TrainCarNode();
        temp = cursor;
        //code added to make sure to properly place the cursor, if 
        // the cursor already contained dangerous car. 
         while(temp.getCar().getLoad().isProductType()){
             if(temp.getNext() != null){
                 temp = temp.getNext();
             }
             else{
                 temp = tail;
                 break;
             }
         }
       
        cursor = head;
        if (dangerousCount == 0) {
            System.out.println("There are no Dangerous cars present");
        } else {
            System.out.println(dangerousCount + " Dangerous cars removed");
            while (cursor != null) {
                if (cursor.getCar().getLoad().isProductType() == true) {
                    removeCursor();
                }
                if (dangerousCount < count) {
                    cursor = cursor.getNext();
                }
            }
        }
        cursor = temp;
    }

    /**
     * String representation of the train, with the number of cars, total
     * length, total weight, product worth, and if there is any dangerous cars.
     * Override of the toString method from object class.
     *
     * @return The string representation Train.
     */
    @Override
    public String toString() {
        String tempBoolean;
        if (isDangerous() == true) {
            tempBoolean = "";
        } else {
            tempBoolean = "no";
        }
        return ("Train : " + count + " car(s), " + totalLength + "m in length, weighing " + String.format("%.2f", totalWeight)
                + " tons, with total worth of " + TrainCar.totalValue + " dollars and contains " + tempBoolean + " dangerous materials");
    }

}
