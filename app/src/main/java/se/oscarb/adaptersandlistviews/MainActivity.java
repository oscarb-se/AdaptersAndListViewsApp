package se.oscarb.adaptersandlistviews;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
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

public class MainActivity extends AppCompatActivity implements CustomDialogFragment.CustomDialogClickListener {

    // Instansvariabel
    ArrayList<String> carStrings;
    MyCarArrayAdapter myCarArrayAdapter;
    private int lastClickedPosition;

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
       myCarArrayAdapter = new MyCarArrayAdapter(this, android.R.layout.simple_list_item_1, carsFromGarage);

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

                // Här fanns det kod för att skapa en dialogruta, men det är nu flyttat till en egen klass
                // Kolla i klassen CustomDialogFragment

                // Kom ihåg position (vilken man klickade på) tills senare
                lastClickedPosition = position;

                // Vilken bil ska tas bort?
                Car carToRemove = myCarArrayAdapter.getItem(lastClickedPosition);

                // Skapa en ny instans av vår egen klass CustomDialogFragment
                CustomDialogFragment dialog = new CustomDialogFragment();
                // Skicka med vilken bil som ska tas bort till dialogrutan
                dialog.setCar(carToRemove);
                // Visa dialogrutan
                dialog.show(getSupportFragmentManager(), "CustomDialogFragment");

                return false;
            }
        });


    }

    @Override
    public void onCancelClick(DialogFragment dialog) {
        // Kod som ska köras när man klickar på cancel i en dialogruta
        // Kod som körs när man klickar på Cancel
        Toast.makeText(this, "Phew, no car deleted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(DialogFragment dialog) {
        // Kod som ska köras när man klickar på Delete i en dialogruta

        // Ta bort en bil från listan
        Car carToRemove = myCarArrayAdapter.getItem(lastClickedPosition);
        ; // vilken bil ska vi ta bort?
        myCarArrayAdapter.remove(carToRemove); // Men vilken bil...?

    }
}
