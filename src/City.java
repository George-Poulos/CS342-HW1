/**
 * Created by : George Poulos
 * Class : CS342 HW1 ---- Fall 2016
 * Author Code Number : 655P
 *
 * This file holds the class that defines City, this class holds
 * an Integer variable that holds the city index, a String that
 * holds the name of the city, and another string that holds the
 * name of the state that city belongs to.
 *
 */
public class City {
    private int city;
    private String cityName;
    private String State;

    public City (int city, String cityName, String State){
        this.city = city;
        this.cityName = cityName;
        this.State = State;
    }

    public String toString(){
        return "The city : " + this.city + ", "+this.cityName + " in the state : " + this.State;
    }
    public int getCity (){
        return this.city;
    }

    public String getCityName(){
        return this.cityName;
    }

    public String getState(){
        return this.State;
    }


}

