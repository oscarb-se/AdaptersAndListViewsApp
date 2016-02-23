package se.oscarb.adaptersandlistviews;


import java.util.ArrayList;

/*
    Klass för att skapa bil-objekt i
 */
public class Garage {
    // Instansvariabler
    ArrayList<Car> cars;

    // Konstruktor
    public Garage() {
        cars = new ArrayList<>();

        addCars();
    }

    // Instansmetoder

    public void addCars() {
        // Lägg till bilar
        cars.add(new Car("BMW", R.drawable.bmw));
        cars.add(new Car("Ferrari", R.drawable.ferrari));
        cars.add(new Car("Lamborghini", R.drawable.lamborghini));
        cars.add(new Car("Mclaren", R.drawable.mclaren));
        cars.add(new Car("Mercedes", R.drawable.mercedes));
        cars.add(new Car("Mini Cooper", R.drawable.mini_cooper));
        cars.add(new Car("Nissan", R.drawable.nissan));
        cars.add(new Car("Peugeot", R.drawable.peugeot));
        cars.add(new Car("Porsche", R.drawable.porsche));
        cars.add(new Car("Suzuki", R.drawable.suzuki));
        cars.add(new Car("Toyota", R.drawable.toyota));
        cars.add(new Car("Volkswagen", R.drawable.volkswagen));
        cars.add(new Car("Volvo", R.drawable.volvo));
    }

    // Returnera alla bilar som en ArrayList<Car>
    public ArrayList<Car> getCars() {
        return cars;
    }



}
