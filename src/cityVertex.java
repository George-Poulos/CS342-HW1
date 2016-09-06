import java.util.*;

/**
 * Created by : George Poulos
 * Class : CS342 HW1 ---- Fall 2016
 * Author Code Number : 655P
 *
 * This file holds the class that defines a cityVertex. The Vertex Class
 * is used to represent a City on the Graph, it holds the name of the
 * city, the index to that city, an ArrayList of neighbors, and a Linked
 * List of the paths available from that city.
 *
 */

public class cityVertex implements Comparable<cityVertex>{
    public LinkedList<cityVertex> path;
    public ArrayList<cityEdge> neighbours;
    public final String name;
    public final int index;
    public double minDistance = Double.POSITIVE_INFINITY;

    public int compareTo(cityVertex other){
        return Double.compare(minDistance,other.minDistance);
    }
    public cityVertex(String name, int n){
        this.name = name;
        neighbours = new ArrayList<cityEdge>();
        path = new LinkedList<cityVertex>();
        this.index = n;
    }
    public String toString(){

        return Integer.toString(index);
    }
    public String getName(){
        return name;
    }
}