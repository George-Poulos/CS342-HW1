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
 *
 */


public class CSVCityNames {

    public static City[] readCities(int printBool) {

        String csvFile = "CityNames.txt";
        BufferedReader br = null;
        String line = "";
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


    public static CityDist[] readCityDist(int printBool) {
        String csvFile = "CityDistances.txt";
        BufferedReader br = null;
        String line = "";
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

    public static Participants[] readParticipants (int printBool){
        String csvFile = "Participants.txt";
        BufferedReader br = null;
        String line = "";
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

                // use comma as separator
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

    public static void findBestCity(City[] allCities, CityDist[] totalCity, Participants[] allPartic){
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
                + allCities[BestCityAvg].getState() + " is the closest city on average, and on average is  "
                + BestCity[BestCityAvg] + " miles away from all of the participants!");
    }

    public static Map startList(City[] allCities, CityDist[] totalDist){
        Map g = new Map(allCities.length, allCities);

        for(int i = 1; i < totalDist.length; i++){
            g.addEdge(totalDist[i].getFromCity()-1,totalDist[i].getToCity()-1,totalDist[i].getDist());
        }
        return g;
    }

    public static void DoPartOneTwoThree(){
        City[] allCities = readCities(0);
        Participants[] totalParticipants = readParticipants(0);
        CityDist[] totalDist = readCityDist(0);
        Dijkstra obj = new Dijkstra();
        Map g = startList(allCities,totalDist);
        g.PrintAdjList();
        obj.calculate1(g.getVertex(57));
        g.sort();
        System.out.println("\n\nFrom Chicago to :");
        g.PrintDistList();
        findBestCity(allCities,totalDist,totalParticipants);
    }

    public static int findMinIdx(double[] BestCityArr) {
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