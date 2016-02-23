package se.oscarb.adaptersandlistviews;

public class Car {

    // Instansvariabler
    private String name;
    private int logotypeResourceId;

    // Konstruktor
    public Car() {
        this("Default car", R.mipmap.ic_launcher);
    }

    // Konstruktor
    public Car(String name, int logotypeResourceId) {
        this.name = name;
        this.logotypeResourceId = logotypeResourceId;
    }

    // Instansmetoder
    public int getLogotypeResourceId() {
        return logotypeResourceId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
