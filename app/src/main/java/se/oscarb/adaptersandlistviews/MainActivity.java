package se.oscarb.adaptersandlistviews;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Instansvariabel
    ArrayList<String> carStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiera vår ArrayList carStrings med strängar
        carStrings = new ArrayList<>();
        carStrings.add("Volvo"); // Notera att Volvo är en String, inte ett Car-objekt...


        // En bättre lista över bilar (med objekt av typen Car)
        Garage myGarage = new Garage();
        ArrayList<Car> carsFromGarage = myGarage.getCars();


        // Hämta vår ListView
        final ListView myListView = (ListView) findViewById(R.id.my_list_view);

        // Skapa adapter med egen TextView
        ArrayAdapter<String> myBuiltInArrayAdapter = new ArrayAdapter<>(this,
                R.layout.my_text_view,
                carStrings);

        // Eller

        // Skapa anpassad adapter med egen layout
        MyCustomAdapter myCustomAdapter = new MyCustomAdapter(this, R.layout.my_text_view, carStrings);


        // Skapa en anpassad adapter för en egen layout och en samling Cars
        final MyCarArrayAdapter myCarArrayAdapter = new MyCarArrayAdapter(this, android.R.layout.simple_list_item_1, carsFromGarage);

        // Använd vår adapter
        //myListView.setAdapter(myBuiltInArrayAdapter);
        //myListView.setAdapter(myCustomAdapter);
        myListView.setAdapter(myCarArrayAdapter);

        // Det ska hända något när man klickar på ett item i listan
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Position: " + position + " id: " + id, Toast.LENGTH_LONG).show();



            }
        });

        // Vad som ska se vid långa knapptryck
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(MainActivity.this, "Long click!", Toast.LENGTH_SHORT).show();

                // Innan vi tar bort bilen, gör en dialogruta och fråga om det är ok!
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                // Anpassa dialogrutan så som vi önskar
                alertDialogBuilder.setMessage("Do you want to delete the car?");

                // Bestäm text och funktion för vår cancel-knapp
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Kod som körs när man klickar på Cancel
                        Toast.makeText(MainActivity.this, "Phew, no car deleted!", Toast.LENGTH_SHORT).show();
                    }
                });

                // Text och funktion för vår Delete-knapp
                alertDialogBuilder.setPositiveButton("Delete away!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Kod som ska köras när man klickar på "Delete away"!

                        // Ta bort en bil från listan
                        Car carToRemove = myCarArrayAdapter.getItem(position);
                        ; // vilken bil ska vi ta bort?
                        myCarArrayAdapter.remove(carToRemove); // Men vilken bil...?
                    }
                });

                // Sätt samman dialogruta och visa upp den
                alertDialogBuilder.create();
                alertDialogBuilder.show();


                return false;
            }
        });


    }
}
