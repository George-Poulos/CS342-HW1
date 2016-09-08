/**
 * Created by : George Poulos
 * Class : CS342 HW1 ---- Fall 2016
 * Author Code Number : 655P
 *
 * This file holds a class that defines what an edge (a path) is.
 * An edge holds the target Vertex (The destination city) and also
 * holds the mileage (weight).
 */

public class cityEdge{
    private final cityVertex destCity;
    private final double Distance;

    public cityEdge(cityVertex target, double distance){
        this.destCity = target;
        this.Distance = distance;
    }

    /**
     * Getters
     * @return
     */
    public cityVertex getDestCity(){
        return destCity;
    }

    public double getDistance(){
        return Distance;
    }
}