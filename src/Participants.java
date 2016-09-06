/**
 * Created by : George Poulos
 * Class : CS342 HW1 ---- Fall 2016
 * Author Code Number : 655P
 *
 * This file holds the class that defines Participants, and this
 * class holds a String variable that holds the Participant's name
 * and holds an Integer that holds the participant's hometown
 * as a number.
 *
 */
public class Participants {
    private String name;
    private int hometown;

    public Participants(String name, int hometown){
        this.name = name;
        this.hometown = hometown;
    }
    public String toString(){
        return (this.name + " is from " + this.hometown);
    }
    public String getName(){
        return this.name;
    }

    public int getHometown(){
        return this.hometown;
    }
}
