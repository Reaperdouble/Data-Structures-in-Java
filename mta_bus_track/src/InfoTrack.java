
import big.data.DataSource;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author radra_000
 */
public class InfoTrack implements Serializable{
    private final String URL = "http://bustime.mta.info/api/siri/stop-monitoring.xml?key=fecc346b-a5c9-4e90-83cb-7616c2287641";
    //"C:\\Users\\radra_000\\Desktop\\tmp.xml";//
   // Queue<BusInfo> table = new Queue<BusInfo>();
    private Queue<BusInfo> table = new LinkedList<BusInfo>();
    InfoTrack info;
    //+"&OperatorRef=MTA&MonitoringRef=502100&PublishedLineName="+busNumber
    //Siri/ServiceDelivery/VehicleMonitoringDelivery/VehicleActivity/MonitoredVehicleJourney/MonitoredCall/Extensions/Distances/DistanceFromCall"
    //VehicleActivity/MonitoredVehicleJourney/MonitoredCall/Extensions/Distances/PresentableDistance
    //VehicleActivity/MonitoredVehicleJourney/VehicleRef
    //VehicleActivity/MonitoredVehicleJourney/MonitoredCall/Extensions/Distances/
    //VehicleActivity/MonitoredVehicleJourney/
    //VehicleActivity/MonitoredVehicleJourney/FramedVehicleJourneyRef/
    //VehicleActivity/MonitoredVehicleJourney/FramedVehicleJourneyRef/
    //VehicleActivity/MonitoredVehicleJourney/FramedVehicleJourneyRef/
    //VehicleActivity/MonitoredVehicleJourney/MonitoredCall/Extensions/
    //VehicleActivity/MonitoredVehicleJourney/MonitoredCall/Extensions/
    //VehicleActivity/MonitoredVehicleJourney/VehicleLocation/
    //VehicleActivity/MonitoredVehicleJourney/VehicleLocation/
    public void buildFromUrl(String busNumber){
        DataSource source = DataSource.connectXML("C:\\Users\\radra_000\\Desktop\\stop-monitoring.xml").load();
        //System.out.println(URL+"&OperatorRef=MTA&MonitoringRef=502100&PublishedLineName="+busNumber);
        //String[] name = (source.fetchStringArray("ServiceDelivery/StopMonitoringDelivery/MonitoredStopVisit/MonitoredVehicleJourney/VehicleRef"));
        //System.out.println(source.getFullPathURL());
         
          String xxxx = source.fetchString("ServiceDelivery/StopMonitoringDelivery/MonitoredStopVisit/MonitoredVehicleJourney/MonitoredCall/Extensions");
//        for(int i = 0; i <DistanceFromCall.length; i++ ){
//            System.out.println(DistanceFromCall[i]);
//        }
          System.out.println(xxxx);
          String[] DistanceFromCall = source.fetchStringArray("ServiceDelivery/StopMonitoringDelivery/MonitoredStopVisit/MonitoredVehicleJourney/MonitoredCall/Extensions");
        //System.out.println(source.getFieldSpec());
       
        String[] presentableDistance = source.fetchStringArray("PresentableDistance");
        String[] vehicleRef = source.fetchStringArray("ServiceDelivery/VehicleMonitoringDelivery/VehicleActivity/MonitoredVehicleJourney/VehicleRef");
        int[] stopsAway = source.fetchIntArray("StopsFromCall");
        String[] busName = source.fetchStringArray("PublishedLineName");
        String[] destination = source.fetchStringArray("DestinationName");
        String[] originId = source.fetchStringArray("OriginRef");
        String[] destinationId = source.fetchStringArray("DestinationRef");
        String[] currentStopId = source.fetchStringArray("StopPointRef");
        String[] currentStop = source.fetchStringArray("StopPointName");
        double[] latitude = source.fetchDoubleArray("Latitude");
        double[] longitude = source.fetchDoubleArray("Longitude");
        for (int i = 0; i < busName.length; i++) {
            BusInfo businfo = new BusInfo(vehicleRef[i], DistanceFromCall[i], presentableDistance[i],stopsAway[i], busName[i]
                    , destination[i], originId[i], destinationId[i], currentStop[i], currentStopId[i], latitude[i], longitude[i]);
            this.put(businfo);
        }
    }
    public void put(BusInfo businformation){
        table.add(businformation);
    }

    public void clearBuses() {
        table.clear();
    }
    public String printBuses(){
        int length = table.size();
        String output =(String.format("%10s","Busname") + " | $" + 
                String.format("%10s", "stopsAway")+" | "+
                String.format("%25s", "Distance")+ " | "+ 
                String.format("%25s", "currentStop")+ " | "+
               // String.format("%10s",timeRemaining+" hours")+ " | "+
                String.format("%25s", "destination"));
        for(int i= 0; i< length; i++){
            BusInfo temp = table.remove();
            output += "/n" + temp.toString();
            table.add(temp);
        }
        return output;
    }
}
