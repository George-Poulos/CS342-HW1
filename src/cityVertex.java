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
    public LinkedList<cityVertex> route;
    private ArrayList<cityEdge> neighbours;
    public double shortestPath = Double.POSITIVE_INFINITY;
    private final String name;
    private final int index;

    /**
     * Setter for city Vertex
     * @param name user defined name for a city
     * @param n user defined int for the index of that city.
     */
    public cityVertex(String name, int n){
        this.name = name;
        neighbours = new ArrayList<cityEdge>();
        route = new LinkedList<cityVertex>();
        this.index = n;
    }

    public int compareTo(cityVertex other){
        return Double.compare(shortestPath,other.shortestPath);
    }

    /**
     * Getter classes
     * @return Returns the String chosen
     */
    public String getName(){

        return name;
    }

    public String toString(){

        return Integer.toString(index);
    }

    public int getIndex(){
        return index;
    }

    public ArrayList<cityEdge> getNeighbours(){
        return neighbours;
    }

}