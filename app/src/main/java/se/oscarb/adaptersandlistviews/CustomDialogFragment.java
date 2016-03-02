package se.oscarb.adaptersandlistviews;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

// En egen klass för dialoger
public class CustomDialogFragment extends DialogFragment {

    // Instansvariabler
    // Instansen av klassen MainActivity hamnar här
    // Vi undersöker senare om den implementerar vårt interface nedan
    // Om den gör det så kan vi säga att kod för knapptryck finns
    // definierad i klassen activity pekar på (i vårt fall MainActivity)
    CustomDialogClickListener activity;

    // Instansvariabel
    private Car car;

    /*
        Ett fragment är som en "activity inuti en activity"
        När man "placerar ut" ett fragment "attachar" man det på en activity
        I det här fallet
        Fragment: CustomDialogFragment
        Activity: MainActivity
     */

    // Instansmetod
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Verifiera att vår Activity implementerar vårt interface
        // som heter CustomDialogClickListener
        this.activity = (CustomDialogClickListener) activity;
    }

    /*
            Ett interface med metoder som måste implementeras
            Två metoder:
            - en för om man klickar på "Cancel"
            - en för om man klickar på "Delete"
            Detta är alltså en "lyssnare" (listener)
         */
    public interface CustomDialogClickListener {
        // Metoden för cancel-knappen
        public void onCancelClick(DialogFragment dialog);
        // Metoden för delete-knappen
        public void onDeleteClick(DialogFragment dialog);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Innan vi tar bort bilen, gör en dialogruta och fråga om det är ok!
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        // Anpassa dialogrutan så som vi önskar
        alertDialogBuilder.setMessage("Do you want to delete the " + car.getName() + "?");

        // Bestäm text och funktion för vår cancel-knapp
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Kör metoden onCancelClick som finns i MainActivity
                activity.onCancelClick(CustomDialogFragment.this);

            }
        });

        // Text och funktion för vår Delete-knapp
        alertDialogBuilder.setPositiveButton("Delete away!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Kod som ska köras när man klickar på "Delete away"!
                activity.onDeleteClick(CustomDialogFragment.this);

            }
        });

        // Sätt samman dialogruta och returnera den
        return alertDialogBuilder.create();
    }
}
