package se.oscarb.adaptersandlistviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


        // Hämta vår ListView
        ListView myListView = (ListView) findViewById(R.id.my_list_view);

        // Skapa vår adapter
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                cars);

        // Använd vår adapter
        myListView.setAdapter(myArrayAdapter);


    }
}
