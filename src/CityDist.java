/**
 * Created by : George Poulos
 * Class : CS342 HW1 ---- Fall 2016
 * Author Code Number : 655P
 *
 * This file holds the class that defines CityDist, this class holds
 * 3 variables, the from city index (src), the toCity index (dest),
 * and the distance (in miles) between the two cities.
 *
 */
public class CityDist {
    private int fromCity;
    private int toCity;
    private double dist;

    public CityDist(int fromCity, int toCity, int dist){
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.dist = dist;
    }

    public String toString(){
        return "Starting at city : " + this.fromCity + " Going to city : "+this.toCity + ", is " + this.dist + " miles away.";
    }

    public int getFromCity(){
        return this.fromCity;
    }

    public int getToCity(){
        return this.toCity;
    }

    public double getDist(){
        return this.dist;
    }
}

