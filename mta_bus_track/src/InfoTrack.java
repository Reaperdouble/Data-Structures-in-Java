
import big.data.DataSource;
import java.io.Serializable;
import java.util.Hashtable;

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
    Hashtable<String, BusInfo> table = new Hashtable<String, BusInfo>();
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
            this.put(id[i], businfo);
        }
    
    }
}
