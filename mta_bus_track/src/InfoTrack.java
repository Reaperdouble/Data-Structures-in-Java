
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
    private final String URL = "http://bustime.mta.info/api/siri/vehicle-monitoring.xml?key=fecc346b-a5c9-4e90-83cb-7616c2287641";
   // Queue<BusInfo> table = new Queue<BusInfo>();
    private Queue<BusInfo> table = new LinkedList<BusInfo>();
    InfoTrack info;
    
    public void buildFromUrl(String busNumber){
        DataSource source = DataSource.connectXML(URL+"&OperatorRef=MTA&MonitoringRef=502100&PublishedLineName="+busNumber).load();
        String[] DistanceFromCall = source.fetchStringArray("VehicleActivity/MonitoredVehicleJourney/MonitoredCall/Extensions/Distances/DistanceFromCall");
        String[] presentableDistance = source.fetchStringArray("VehicleActivity/MonitoredVehicleJourney/MonitoredCall/Extensions/Distances/PresentableDistance");
        String[] vehicleRef = source.fetchStringArray("VehicleActivity/MonitoredVehicleJourney/VehicleRef");
        int[] stopsAway = source.fetchIntArray("VehicleActivity/MonitoredVehicleJourney/MonitoredCall/Extensions/Distances/StopsFromCall");
        String[] busName = source.fetchStringArray("VehicleActivity/MonitoredVehicleJourney/PublishedLineName");
        String[] destination = source.fetchStringArray("VehicleActivity/MonitoredVehicleJourney/FramedVehicleJourneyRef/DestinationName");
        String[] originId = source.fetchStringArray("VehicleActivity/MonitoredVehicleJourney/FramedVehicleJourneyRef/OriginRef");
        String[] destinationId = source.fetchStringArray("VehicleActivity/MonitoredVehicleJourney/FramedVehicleJourneyRef/DestinationRef");
        String[] currentStopId = source.fetchStringArray("VehicleActivity/MonitoredVehicleJourney/MonitoredCall/Extensions/StopPointRef");
        String[] currentStop = source.fetchStringArray("VehicleActivity/MonitoredVehicleJourney/MonitoredCall/Extensions/StopPointName");
        double[] latitude = source.fetchDoubleArray("VehicleActivity/MonitoredVehicleJourney/VehicleLocation/Latitude");
        double[] longitude = source.fetchDoubleArray("VehicleActivity/MonitoredVehicleJourney/VehicleLocation/Longitude");
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
