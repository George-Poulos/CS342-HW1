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
    public final cityVertex destCity;
        public final double Distance;
    public cityEdge(cityVertex target, double weight){
        this.destCity = target;
        this.Distance = weight;
    }
}