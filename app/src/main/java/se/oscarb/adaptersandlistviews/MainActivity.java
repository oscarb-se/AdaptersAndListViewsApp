package se.oscarb.adaptersandlistviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Instansvariabel
    ArrayList<String> cars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiera vår ArrayList
        cars = new ArrayList<>();

        // Lägg till i cars...
        cars.add("Toyota");
        cars.add("Volvo");
        cars.add("Mercedes");
        cars.add("Ferrari");
        cars.add("BMW");
        cars.add("Lamborghini");
        cars.add("Saab");
        cars.add("Ford");
        cars.add("Chevrolet");
        cars.add("Cadillac");
        cars.add("Chrysler");
        cars.add("Dodge");
        cars.add("Jeep");
        cars.add("Fiat");
        cars.add("Renault");
        cars.add("Nissan");
        cars.add("Peugeot");
        cars.add("Honda");
        cars.add("Lada");




        // Hämta vår ListView
        ListView myListView = (ListView) findViewById(R.id.my_list_view);

        // Skapa adapter med egen TextView
        ArrayAdapter<String> myBuiltInArrayAdapter = new ArrayAdapter<>(this,
                R.layout.my_text_view,
                cars);

        // Eller

        // Skapa anpassad adapter med egen layout
        MyCustomAdapter myCustomAdapter = new MyCustomAdapter(this, R.layout.my_text_view, cars);

        // Använd vår adapter
        //myListView.setAdapter(myBuiltInArrayAdapter);

        myListView.setAdapter(myCustomAdapter);

    }
}
