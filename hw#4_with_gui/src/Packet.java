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
 * It contain the information about the details of the product such as the 
 * arrival time, size, and time to reach destination
 */
public class Packet {
    static int packetCount = 0;
    int id;
    int packetSize;
    int timeArrive;
    int timeToDest;// = packetSize/100;

    public Packet(int packetSize, int timeArrive) {
        packetCount++;
        this.id = packetCount;
        this.packetSize = packetSize;
        this.timeArrive = timeArrive;
        this.timeToDest = packetSize/10;
    }

    public static int getPacketCount() {
        return packetCount;
    }

    public static void setPacketCount(int packetCount) {
        Packet.packetCount = packetCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    public int getTimeArrive() {
        return timeArrive;
    }

    public void setTimeArrive(int timeArrive) {
        this.timeArrive = timeArrive;
    }

    public int getTimeToDest() {
        return timeToDest;
    }

    public void setTimeToDest(int timeToDest) {
        this.timeToDest = (timeToDest<0)?0:timeToDest;
    }

    @Override
    public String toString() {
        return "["+ id +", "+ timeArrive + ", " + (timeToDest) + ']';
    }
    
}
