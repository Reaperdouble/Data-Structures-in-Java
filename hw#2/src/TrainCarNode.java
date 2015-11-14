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
 * This TrianCarNode class acts as a node wrapper around the TrainCar Object.
 * The class contain two TrainCarNode reference previous and next and a TrainCar
 * reference to mean a particular car. The class will help help the
 * TrainLinkedList class move across the train.
 *
 */
public class TrainCarNode {

    private TrainCarNode previous;
    private TrainCarNode next;
    private TrainCar car;

    /**
     * Constructor that will assign the data with details of the neighboring
     * nodes surrounding a car to the variables.
     *
     * @param previous Information of the previous node(if there are any)
     * @param next Information of the next node(if there are any)
     * @param car Tells which car the class is looking at or inside.
     */
    public TrainCarNode(TrainCarNode previous, TrainCarNode next, TrainCar car) {
        super();
        this.previous = previous;
        this.next = next;
        this.car = car;
    }

    /**
     * empty constructor that creates a new instance of the TrainCarNode class
     */
    public TrainCarNode() {
    }

    /**
     * Takes the information about the node of the previous car.
     *
     * @return previous variable of the type TrainCarNode, containing
     * information about the previous node with respect to a particular car
     */
    public TrainCarNode getPrevious() {
        return previous;
    }

    /**
     * Sets or points the information about a particular car's previous node So
     * they both can be linked.
     *
     * @param previous contain the memory address/ location of the node of the
     * previous car
     */
    public void setPrevious(TrainCarNode previous) {
        this.previous = previous;
    }

    /**
     * takes the information of the node of a car that is next to the particular
     * car
     *
     * @return variable next of the type TrainCarNode with information about the
     * node of the next car which will be supplied by the TrainLinkedList class
     */
    public TrainCarNode getNext() {
        return next;
    }

    /**
     * Sets or links the information about a particular car's next node so they
     * both are linked
     *
     * @param next contains the memory address/location of the node of the next
     * car which will be supplied by the TrainLinkedList class.
     */
    public void setNext(TrainCarNode next) {
        this.next = next;
    }

    /**
     * Takes the information of a pariticular car like the weight, length, load
     * information.
     *
     * @return Variable car of type TrainCar with all the information of a
     * Particular car .
     */
    public TrainCar getCar() {
        return car;
    }

    /**
     * Sets the information entered by the user about the lenght, weight, load
     * pertaining to car.
     *
     * @param car Car variable of the type TrainCar contain information about a
     * car
     *
     */
    public void setCar(TrainCar car) {
        this.car = car;
    }

    /**
     * override of the toString method from object class. Since this class
     * contains information only regarding the memory address this method will
     * just be used to call the toString method of the TrainCar class of
     * particular car denoted by variable car.
     *
     * @return String representation of car with details of its attributes
     */
    @Override
    public String toString() {
        return car.toString();
    }

}
