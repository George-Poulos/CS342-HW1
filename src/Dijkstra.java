import java.util.*;

/**
 * Created by : George Poulos
 * Class : CS342 HW1 ---- Fall 2016
 * Author Code Number : 655P
 *
 * This file holds the Dijkstra Class, which holds the method that uses
 * Dijkstra's Algorithm to find the shortest path to every city from a
 * given a starting point. There are two methods with the same algorithm,
 * except one algorithm incorporates an Integer array that holds the sum
 * of miles to each city, each index of the array represents the city's
 * index number. That array later has each index divided by the
 * number of participants.
 */

public class Dijkstra{

    public void calculateAll(cityVertex fromCity, double[] arr){

        PriorityQueue<cityVertex> mapQueue = new PriorityQueue<cityVertex>();
        fromCity.shortestPath = 0;
        mapQueue.add(fromCity);
        int i = 1;
        while(!mapQueue.isEmpty()){
            cityVertex u = mapQueue.poll();

            for(cityEdge neighbour:u.getNeighbours()){
                Double newDist = u.shortestPath+neighbour.getDistance();
                i = u.getIndex();
                if(neighbour.getDestCity().shortestPath>newDist){

                    mapQueue.remove(neighbour.getDestCity());
                    neighbour.getDestCity().shortestPath = newDist;

                    neighbour.getDestCity().route = new LinkedList<cityVertex>(u.route);
                    neighbour.getDestCity().route.add(u);

                    mapQueue.add(neighbour.getDestCity());

                }

            }
            arr[i] =arr[i] + u.shortestPath;
        }
    }

    public void calculate1(cityVertex fromCity){


        PriorityQueue<cityVertex> mapQueue = new PriorityQueue<cityVertex>();
        fromCity.shortestPath = 0;
        mapQueue.add(fromCity);

        while(!mapQueue.isEmpty()){

            cityVertex u = mapQueue.poll();

            for(cityEdge neighbour:u.getNeighbours()){
                Double newDist = u.shortestPath+neighbour.getDistance();

                if(neighbour.getDestCity().shortestPath>newDist){

                    mapQueue.remove(neighbour.getDestCity());
                    neighbour.getDestCity().shortestPath = newDist;

                    neighbour.getDestCity().route = new LinkedList<cityVertex>(u.route);
                    neighbour.getDestCity().route.add(u);

                    mapQueue.add(neighbour.getDestCity());
                }
            }
        }
    }
}


