
import java.io.Serializable;


public class BusInfo implements Serializable{
    private String distanceFromCall;
    private String presentableDistance;
    private String vehicleRef;
    private int stopsAway;
    private String busName;
    private String destination;
    private String originId; 
    private String destinationId;
    private String currentStop;
    private String currentStopId;
    private double latitude;
    private double longitude;

    public BusInfo(String vehicleRef, String distanceFromCall, String presentableDistance, int stopsAway, String busName, String destination, String originId, String destinationId, String currentStop, String currentStopId, double latitude, double longitude) {
        this.vehicleRef = vehicleRef;
        this.distanceFromCall = distanceFromCall;
        this.presentableDistance = presentableDistance;
        this.stopsAway = stopsAway;
        this.busName = busName;
        this.destination = destination;
        this.originId = originId;
        this.destinationId = destinationId;
        this.currentStop = currentStop;
        this.currentStopId = currentStopId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public void updateLocation(String presentableDistance, String distanceFromCall, int stopsAway, String currentStop, String currentStopId, double latitude, double longitude){
        this.presentableDistance = presentableDistance;
        this.distanceFromCall = distanceFromCall;
        this.stopsAway = stopsAway;
        this.currentStop = currentStop;
        this.currentStopId = currentStopId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDistanceFromCall() {
        return distanceFromCall;
    }

    public String getPresentableDistance() {
        return presentableDistance;
    }

    public String getVehicleRef() {
        return vehicleRef;
    }

    public int getStopsAway() {
        return stopsAway;
    }
    public String getBusName() {
        return busName;
    }

    public String getDestination() {
        return destination;
    }
    public String getOriginId() {
        return originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public String getCurrentStop() {
        return currentStop;
    }

    public String getCurrentStopId() {
        return currentStopId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    
    @Override
    public String toString(){

        return(String.format("%10s",busName) + " | $" + 
                String.format("%10s", stopsAway)+" | "+
                String.format("%25s", presentableDistance)+ " | "+ 
                String.format("%25s", currentStop)+ " | "+
               // String.format("%10s",timeRemaining+" hours")+ " | "+
                String.format("%25s", destination));
        
    }
}
