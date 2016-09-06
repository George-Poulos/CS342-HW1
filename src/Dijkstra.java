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

    public void calculate1(cityVertex fromCity){


        PriorityQueue<cityVertex> queue = new PriorityQueue<cityVertex>();
        fromCity.minDistance = 0;
        queue.add(fromCity);

        while(!queue.isEmpty()){

            cityVertex u = queue.poll();

            for(cityEdge neighbour:u.neighbours){
                Double newDist = u.minDistance+neighbour.Distance;

                if(neighbour.destCity.minDistance>newDist){
                    // Remove the node from the queue to update the distance value.
                    queue.remove(neighbour.destCity);
                    neighbour.destCity.minDistance = newDist;

                    // Take the path visited till now and add the new node.s
                    neighbour.destCity.path = new LinkedList<cityVertex>(u.path);
                    neighbour.destCity.path.add(u);

                    //Reenter the node with new distance.
                    queue.add(neighbour.destCity);
                }
            }
        }
    }

    public void calculateAll(cityVertex fromCity, double[] arr){

        PriorityQueue<cityVertex> queue = new PriorityQueue<cityVertex>();
        fromCity.minDistance = 0;
        queue.add(fromCity);
        int i = 1;
        while(!queue.isEmpty()){

            cityVertex u = queue.poll();

            for(cityEdge neighbour:u.neighbours){
                Double newDist = u.minDistance+neighbour.Distance;
                i = u.index;
                if(neighbour.destCity.minDistance>newDist){

                    queue.remove(neighbour.destCity);
                    neighbour.destCity.minDistance = newDist;

                    neighbour.destCity.path = new LinkedList<cityVertex>(u.path);
                    neighbour.destCity.path.add(u);

                    queue.add(neighbour.destCity);

                }

            }
            arr[i] =arr[i] + u.minDistance;
        }

    }



}