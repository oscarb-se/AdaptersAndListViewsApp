package se.oscarb.adaptersandlistviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter {

    // Instansvariabler
    Context context;
    ArrayList<String> thingsToPrint;

    public MyCustomAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);

        // Spara context till senare
        this.context = context;
        thingsToPrint = (ArrayList) objects;


    }

    int number = 0;


    // Ett sätt att optimera ListView ytterligare
    static class MyViewHolder {
        // Instansvariabler (i en statisk inre klass)
        ImageView icon;
        TextView topText;
        TextView bottomText;
    }

    // Vi överskuggar metoden getView för att använda vår komplexa layout
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




        // Skapa den View som visas för varje item i en ListView
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());


        // Deklarera varaibeln itemView som sen återanvänds
        View itemView;

        if(convertView == null) {
            // Det finns ingen View just nu som systemet återanvänder och byter innehåll i...
            // ...så vi skapar en.
            itemView = layoutInflater.inflate(R.layout.complex_item_layout, parent, false);
            //Toast.makeText(context, "position: " + position + " convertView är null", Toast.LENGTH_SHORT).show();

            // Lagra referenser till våra imageViews och TextViews (för varje item)
            MyViewHolder myViewHolder = new MyViewHolder();
            // Hämta det som finns inuti vår itemView
            //Toast.makeText(context, "Koden körs " +  number++ + " gånger", Toast.LENGTH_SHORT).show();

            myViewHolder.icon = (ImageView) itemView.findViewById(R.id.left_icon);
            myViewHolder.topText = (TextView) itemView.findViewById(R.id.top_text);
            myViewHolder.bottomText = (TextView) itemView.findViewById(R.id.bottom_text);

            // Spara information om vart allt detta finns
            itemView.setTag(myViewHolder);

        } else {
            // Det finns en View för ett item som kan återanvändas
            // Vi hittar den i argumentet convertView
            // Vi säger nu att vår itemView ska vara det som convertView är
            itemView = convertView;

            String carText = ((TextView) convertView.findViewById(R.id.top_text)).getText().toString();
            //Toast.makeText(context, "position: " + position + " convertView motsvarar " + carText, Toast.LENGTH_SHORT).show();
        }


        MyViewHolder returnedViewHolder = (MyViewHolder) itemView.getTag();

        // Sätt värden
        returnedViewHolder.topText.setText(thingsToPrint.get(position));
        returnedViewHolder.bottomText.setText("Position" + position);


        return itemView;
    }
}
