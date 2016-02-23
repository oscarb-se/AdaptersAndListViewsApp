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
