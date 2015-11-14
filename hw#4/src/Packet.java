/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#4 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 *
 * @author radra_000
 */


/**
 *This class represents a packet that will be sent through the network. 
 * It contains the information about the details of the product such as the 
 * arrival time, size, and time to reach destination
 */
public class Packet {
    static int packetCount = 0;
    int id;
    int packetSize;
    int timeArrive;
    int timeToDest;

    /**
     * This is the constructor method that will assign the size and creation time 
     * to the packet object. The timeToDest is initialized to tenth of the packet 
     * size, and will be decremented for every time. The id is assigned to the 
     * value based on the number of packet objects created. This is the value
     * of the packetCount, which is the static integer variable. 
     * @param packetSize
     *      integer value of the information that was chosen at random, everytime
     *      a new packet object is created. assigned to packetSize Variable. 
     * @param timeArrive
     *      This is the integer value that is the value of the simulation time 
     *      that the object was created. Assigned to the timeArrive variable. 
     */
    public Packet(int packetSize, int timeArrive) {
        packetCount++;
        this.id = packetCount;
        this.packetSize = packetSize;
        this.timeArrive = timeArrive;
        this.timeToDest = packetSize/10;
    }

    /**
     *Getter method for the packetCount static variable. will return the integer
     * value of current value of the packetCount;
     * @return 
     *      The static variable packetCount of the type integer. 
     */
    public static int getPacketCount() {
        return packetCount;
    }

    /**
     * Setter method that will assign the int packetCount that is passed in to 
     * the static variable packetCount. This method is never used since we dont 
     * need to modify this static variable. It will mess up the count if we did
     * @param packetCount
     *      integer value passed in if in case we have to restart the count or 
     *      change the count value
     */
    public static void setPacketCount(int packetCount) {
        Packet.packetCount = packetCount;
    }

    /**
     *Getter method to return the id of this packet object. 
     * @return
     *      The variable id of the type int, from this object. 
     */
    public int getId() {
        return id;
    }

    /**
     *setter method to assign the id value passed in to the id variable of this 
     * object
     * @param id
     *      integer value passed in by user or other class to reassign the name 
     *      or id of this object. 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method to return the packetSize of this packet object. 
     * @return
     *      int value that is the size of this packet object. 
     */
    public int getPacketSize() {
        return packetSize;
    }

    /**
     *  setter method to assing the packetSize parameter passed in by user or 
     * other class to the packetSize of this object. 
     * @param packetSize
     *      packetSize is the integer value assigned by the user or other class 
     *      to set the packetSize of this object
     */
    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    /**
     * Getter method to return the creation time of a packet with respect to the 
     * simulation time. 
     * @return
     *      the int value of the creation/arrival time of the packet of this object
     */
    public int getTimeArrive() {
        return timeArrive;
    }

    /**
     *Setter method that will assign the value of the timeArrive parameter to the 
     * timeArrive variable of this object. 
     * @param timeArrive
     *      int value passed in by the user or another class to set/assign the 
     *      timeArrive variable of this object. 
     */
    public void setTimeArrive(int timeArrive) {
        this.timeArrive = timeArrive;
    }

    /**
     *  Getter method to return the timeToDest of this object. 
     * @return
     *      the integer value that describes the time that is left to arrive at 
     *      the destination. 
     */
    public int getTimeToDest() {
        return timeToDest;
    }

    /**
     * Setter method that will assign the timeToDest parameter to the timeToDest
     * variable of this class. precaution is taken to avoid the time from being
     * a negative value. Depending on this value, the packet will be sent to the 
     * intermediate router. 
     * @param timeToDest
     *      integer value that is assigned by another class and will be reduced
     *      for every simulation time. until it reaches 0. 
     */
    public void setTimeToDest(int timeToDest) {
        this.timeToDest = (timeToDest<0)?0:timeToDest;
    }
    /**
     * override string method to output the details about this class in string 
     * format: [id, created time, time to destnation] = [5,2,56]..
     * @return 
     *      String representation of this packet object. 
     */
    @Override
    public String toString() {
        return "["+ id +", "+ timeArrive + ", " + (timeToDest) + ']';
    }
    
}
