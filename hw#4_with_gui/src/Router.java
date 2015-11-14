
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
//import java.util.Queue;

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
 * @author radra_000
 */
public class Router extends Vector<Packet>{
    private int count = 0;
    public void router(){}
    public static int sendPacketTo(Collection routers, Packet packet){
        ArrayList<Router> array = (ArrayList<Router>)routers;
        int lowestCount = array.get(0).size();
        int freeArray = 0;
        for(int i=1; i<array.size();i++){
           // System.out.println(Simulator.maxBufferSize);
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
        //routers = array;    //set the old stuff back, ask if needed
        
    }
    public void enqueue(Packet p){//add exceptions
        super.add(p);
        count++;
        
    }
    public Packet dequeue() throws IllegalArgumentException{
        if(super.isEmpty()){
            throw new IllegalArgumentException("The queue is empty");
        }
        else{
            return super.remove(0);
            
        }
    }
    public Packet peek() throws IllegalArgumentException{
        if(super.isEmpty()){
            throw new IllegalArgumentException("The queue is empty");
        }
        else{
            return super.get(0);
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public String toString(){
        String output = "{";
       for(int i =0; i < super.size();i++){
           output = output.concat(super.get(i).toString());
           output = output.concat((i==super.size()-1)?"":", ");
       }
       return output;
    }
    
}
