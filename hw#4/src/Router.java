
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;


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
 * This class represents a router in the network(ie a queue implementation)
 * a seperate router object is created for dispatcher and intermediate router. 
 * Since we are inheriting from the vector class, we did not have to add size, 
 * and isEmpty method. We had to create enqueue, dequeue, peek methods
 * @author radra_000
 */
public class Router extends Vector<Packet>{
   // private int count = 0;

    /**
     *  Default constructor method that will be used everytime a router object is 
     * created. 
     */
    public void router(){}

    /**
     *sendPacketTo is a static method that is the heart of this assignment. This 
     * method will assign the packet to intermediate routers, based on fairness. 
     * So the algorithm will look at each intermediate routers to see how many 
     * items are currently in the queue, and depending on the one that has the least
     * number of packets. If all the routers contains the number of packets equal 
     * to the buffer size then the packet gets dropped and the packetDropped 
     * static variable from the simulator class is incremented and print out 
     * the a string notifying the user that a specific packet is dropped.
     * otherwise the packet is just added to the appropriate router. 
     * This method is called from the simulator class. 
     * @param routers
     *      Collection object that contains the various intermediate routers that
     *      was created in the simulator class. 
     * @param packet
     *      Packet object that is passed in. Its the object that was created at 
     *      this simulation time and its currently residing in the dispatcher. 
     * @return
     *      int value containing the exact router that the packet was added to. 
     */
    public static int sendPacketTo(Collection routers, Packet packet){
        ArrayList<Router> array = (ArrayList<Router>)routers;
        int lowestCount = array.get(0).size();
        int freeArray = 0;
        for(int i=1; i<array.size();i++){
            if(array.get(i).size()<Simulator.maxBufferSize && array.get(i).size()<lowestCount){
                
                lowestCount= array.get(i).size();
                freeArray = i;
            }
        }
        if(array.get(freeArray).size()<Simulator.maxBufferSize){
            array.get(freeArray).enqueue(packet);
            return freeArray+1;
        }
        else{
            System.out.println("Packet "+packet.getId()+" dropped due to network congestion");
            Simulator.packetsDropped++;
            return -1;
        }
        
    }

    /**
     * enqueue method is the method to add a specific object(in this case a packet
     * object) to the router queue. It will used the add method that was inherited
     * from the vector class to do this operation. 
     * @param p
     *      p of the type Packet is passed in by the simulator class.
     */
    public void enqueue(Packet p){//add exceptions
        super.add(p);
       // count++;
        
    }

    /**
     *dequeue method that was is used to remove a object(in this case packet object
     * from the router queue.
     * @return
     *      the packet object that was removed from the top of the queue.
     * @throws IllegalArgumentException
     *      Exception is thrown when the queue is empty and the dequeue method is
     *      called. 
     */
    public Packet dequeue() throws IllegalArgumentException{
        if(super.isEmpty()){
            throw new IllegalArgumentException("The queue is empty");
        }
        else{
            return super.remove(0);
            
        }
    }

    /**
     * peek method is used to check and return details on what packet object is
     * on the top of the queue
     * 
     * @return 
     *      Packet object that is residing on top of the queue. 
     * @throws IllegalArgumentException
     *      exception is thrown when the peek method is called when the list is 
     *      empty. 
     */
    public Packet peek() throws IllegalArgumentException{
        if(super.isEmpty()){
            throw new IllegalArgumentException("The queue is empty");
        }
        else{
            return super.get(0);
        }
    }

    /**
     *
     * @return
     */
//    public int getCount() {
//        return count;
//    }

    /**
     *
     * @param count
     */
//    public void setCount(int count) {
//        this.count = count;
//    }
    /**
     * toString method that is an override of the toString method from the vector
     * class. This method calls the toString methods of the packet Objects that 
     * are inside the router queue. And just adds a open parenthesis and a comma
     * depending on the needs. 
     * @return 
     *      String representation of the packet objects that are insides this 
     *      objects queue.
     */
    public String toString(){
        String output = "{";
       for(int i =0; i < super.size();i++){
           output = output.concat(super.get(i).toString());
           output = output.concat((i==super.size()-1)?"":", ");
       }
       return output;
    }
    
}
