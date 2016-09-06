import java.util.*;

/**
 * Created by : George Poulos
 * Class : CS342 HW1 ---- Fall 2016
 * Author Code Number : 655P
 *
 * This file contains the Class that defines Map. Map is the
 * outermost object of this data structure, it holds one variable
 * that holds an ArrayList of 'Cities' entered. This outer-shell
 * allows us to access all Cities, all paths, all neighbors of cities
 * in one variable.
 *
 */

public class Map {
    private ArrayList<cityVertex> Map;
    public Map(int numberVertices, City[] allCities){
        Map = new ArrayList<cityVertex>(numberVertices);
        for(int i=1;i<numberVertices;i++){
            Map.add(new cityVertex(allCities[i].getCityName() + "," + allCities[i].getState(), i ));
        }
    }

    /**
     * This method adds an edge in both directions, from the start city to the end
     * city and the end city to the start city...
     * @param fromCity is the city index in which the edge starts at
     * @param toCity is the city index in which the edge ends at
     * @param distance is the distance between the two cities
     */
    public void addEdge(int fromCity, int toCity, double distance){
        cityVertex s = Map.get(fromCity);
        cityEdge new_edge = new cityEdge(Map.get(toCity),distance);
        s.neighbours.add(new_edge);
        s = Map.get(toCity);
        new_edge = new cityEdge(Map.get(fromCity),distance);
        s.neighbours.add(new_edge);
    }

    public ArrayList<cityVertex> getMap() {
        return Map;
    }

    public cityVertex getVertex(int vert){
        return Map.get(vert);
    }

    public void PrintAdjList(){
        System.out.println("\n\nPrinting Adjacency List : ");
        for(cityVertex v:this.getMap()){
            System.out.print("City : ("+ v + ")");
            for(cityEdge pathvert:v.neighbours) {
                System.out.print(" -> " + pathvert.destCity);
            }
            System.out.println("");
        }
    }
    public void PrintDistList(){
        for(cityVertex v:this.getMap()){
            System.out.print(v.getName()+ ": "+ v.minDistance);
            System.out.println("");
        }
    }

    public  void sort(){
        Collections.sort(this.getMap(), new CityNameComparator());
    }

    public class CityNameComparator implements Comparator<cityVertex> {
        public int compare(cityVertex p1, cityVertex p2) {
            return p1.getName().compareTo(p2.getName());
        }
    }

}