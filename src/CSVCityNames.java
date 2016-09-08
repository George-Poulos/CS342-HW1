import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by : George Poulos
 * Class : CS342 HW1 ---- Fall 2016
 * Author Code Number : 655P
 *
 * This file contains the class that defines CSVCityNames which
 * is our main driver for reading in data from .txt or .csv
 * files separated by commas and spaces. It does this by reading
 * line by line, and parsing each line instead of just parsing
 * token after token. This file is also used for implementing a
 * few methods to clean up the main class. These methods have
 * been used just for the sake of simplifying the main method.
 * The best
 *
 *
 *
 */


public class CSVCityNames {

    /**
     * Used for reading files readCities reads a list of cities and sticks them into a City[]
     * This is used later for referencing Cities
     * @param printBool Option to either give output or not
     * @return returns a Structure of City names listed
     */
    private static City[] readCities(int printBool) {

        String csvFile = "CityNames.txt";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        City[] cities = new City[0];
        int total = 0;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            total = Integer.parseInt(br.readLine());
            System.out.println("Total :   " + total + " Cities Loaded");
            int counter = 1;
            cities = new City[total + 1];
            while ((line = br.readLine()) != null) {

                String[] city = line.split(cvsSplitBy);
                cities[counter] = new City(counter, city[0], city[1]);
                if (printBool == 1)
                    System.out.println(counter + ".  " + city[0] + ", " + city[1]);
                counter++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cities;
    }


    private static CityDist[] readCityDist(int printBool) {
        String csvFile = "CityDistances.txt";
        BufferedReader br = null;
        String line ;
        CityDist[] cityDistances = new CityDist[0];
        String cvsSplitBy = " ";
        int total = 0;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            total = Integer.parseInt(br.readLine());
            cityDistances = new CityDist[total+1];
            System.out.println("Total :   " + total + " Paths Loaded");
            int counter = 1;
            while ((line = br.readLine()) != null) {

                String[] city = line.split(cvsSplitBy);
                cityDistances[counter] = new CityDist(Integer.parseInt(city[0]),
                        Integer.parseInt(city[1]), Integer.parseInt(city[2]));
                if (printBool == 1)
                    System.out.println(counter + ".  " + "City : " + city[0] + " to  City : " + city[1] +
                            "  is  " + city[2] + " miles.");
                counter++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cityDistances;
    }

    private static Participants[] readParticipants (int printBool){
        String csvFile = "Participants.txt";
        BufferedReader br = null;
        String line;
        Participants [] allPartic = new Participants[0];
        String cvsSplitBy = " ";
        int total = 0;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            total = Integer.parseInt(br.readLine());
            System.out.println("Total :   " + total + " Participants loaded");
            allPartic = new Participants[total+1];
            int counter = 1;
            while ((line = br.readLine()) != null) {

                String[] city = line.split(cvsSplitBy);
                allPartic[counter] = new Participants(city[0],Integer.parseInt(city[1]));
                if (printBool == 1)
                    System.out.println(counter + ".  " + "Participant : " + city[0] + " lives in city :" + city[1]);
                counter++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allPartic;

    }

    /**
     *
     * @param allCities Structure of cities that were read in
     * @param totalCity Structure of edges that were read in
     * @param allPartic Create a map with paths and edges to all cities
     */
    private static void findBestCity(City[] allCities, CityDist[] totalCity, Participants[] allPartic){
        int particLen = allPartic.length;
        int citylen = allCities.length;
        double[] BestCity = new double[citylen];
        for(int j = 1; j<particLen; j++) {
            Map g = startList(allCities, totalCity);
            Dijkstra obj = new Dijkstra();
            obj.calculateAll(g.getVertex(allPartic[j].getHometown()-1), BestCity);
        }

        for(int i = 1; i<citylen; i++){
            BestCity[i] =BestCity[i]/(particLen-1);
        }
        int BestCityAvg = findMinIdx(BestCity);
        System.out.println("\n" + BestCityAvg + ".  " + allCities[BestCityAvg].getCityName() + ","
                + allCities[BestCityAvg].getState() + " is the closest city on average, and on average is\n\t "
                + BestCity[BestCityAvg] + " miles away from all of the participants!");
    }

    /**
     * This method starts a list/map of the cities and distances of all of the inputted
     * cities and distances from other cities
     * @param allCities Structure of cities that were read in
     * @param totalDist Structure of edges that were read in
     * @return Create a map with paths and edges to all cities
     */
    private static Map startList(City[] allCities, CityDist[] totalDist){
        Map g = new Map(allCities.length, allCities);

        for(int i = 1; i < totalDist.length; i++){
            g.addEdge(totalDist[i].getFromCity()-1,totalDist[i].getToCity()-1,totalDist[i].getDist());
        }
        return g;
    }

    /**
     * Method that does all the work for me so I can just make one function call in main.
     * Makes for a cleaner end result
     */
    public static void DoPartOneTwoThree(int city){
        City[] allCities = readCities(0);
        Participants[] totalParticipants = readParticipants(0);
        CityDist[] totalDist = readCityDist(0);
        Dijkstra findPath = new Dijkstra();
        Map g = startList(allCities,totalDist);
        g.PrintAdjList();
        findPath.calculate1(g.getVertex(city-1));
        System.out.println("\n\nFrom "+ g.getVertex(city).getName() + " to :");
        g.sort();
        g.PrintDistList();
        findBestCity(allCities,totalDist,totalParticipants);
    }

    /**
     * This method is just used to find the index of the minimum value in an
     * Integer array
     * @param BestCityArr - Array being evaluated
     * @return returning the index of the minimum value in an array
     */
    private static int findMinIdx(double[] BestCityArr) {
        if (BestCityArr == null || BestCityArr.length == 0) return -1;
        double minVal = BestCityArr[1];
        int indexMin = 0;
        for(int idx=1; idx<BestCityArr.length; idx++) {
            if(BestCityArr[idx] <= minVal) {
                minVal = BestCityArr[idx];
                indexMin = idx;
            }
        }
        return indexMin;
    }

}