package nl.itsjaap.week3.models;

/**
 * Created by mjboere on 21-11-2017.
 */

public class Car {

    private String name;

    public Car(String name) {
        this.name  = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
