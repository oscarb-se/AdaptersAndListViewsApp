package se.oscarb.adaptersandlistviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyCarArrayAdapter extends ArrayAdapter<Car> {

    // Instansvariabler
    Context context;
    ArrayList<Car> carsToPrint;

    // Konstruktor

    public MyCarArrayAdapter(Context context, int resource, List<Car> objects) {
        super(context, resource, objects);

        // Spara context till senare
        this.context = context;
        carsToPrint = (ArrayList) objects;
    }

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

            //String carText = ((TextView) convertView.findViewById(R.id.top_text)).getText().toString();
            //Toast.makeText(context, "position: " + position + " convertView motsvarar " + carText, Toast.LENGTH_SHORT).show();
        }

        // Hämta nuvarande "viewHolder"
        MyViewHolder returnedViewHolder = (MyViewHolder) itemView.getTag();

        // Hämta bilens namn och ID:t som pekar på bilden (dvs R.drawable.bmw etc.)
        String carName = carsToPrint.get(position).getName();
        int carLogotypeId = carsToPrint.get(position).getLogotypeResourceId();

        // Sätt värden
        returnedViewHolder.topText.setText(carName);
        returnedViewHolder.bottomText.setText("Position" + position);
        returnedViewHolder.icon.setImageResource(carLogotypeId);

        return itemView;
    }
}
