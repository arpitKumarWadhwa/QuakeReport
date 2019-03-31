package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class EarthquakeAdapter extends ArrayAdapter {

    private List<Earthquake> earthquakeList = new ArrayList<>();
    private Context context;

    private int getMagnitudeColor(double magnitude) {

        int mag = (int) Math.floor(magnitude);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            switch (mag) {
                case 0:
                case 1:
                    return context.getColor(R.color.magnitude1);
                case 2:
                    return context.getColor(R.color.magnitude2);
                case 3:
                    return context.getColor(R.color.magnitude3);
                case 4:
                    return context.getColor(R.color.magnitude4);
                case 5:
                    return context.getColor(R.color.magnitude5);
                case 6:
                    return context.getColor(R.color.magnitude6);
                case 7:
                    return context.getColor(R.color.magnitude7);
                case 8:
                    return context.getColor(R.color.magnitude8);
                case 9:
                    return context.getColor(R.color.magnitude9);
                default:
                    return context.getColor(R.color.magnitude10plus);
            }
        } else {
            return 0;
        }
    }

    public EarthquakeAdapter(@NonNull Context context, List<Earthquake> array) {
        super(context, R.layout.earthquake_list);
        earthquakeList = array;
        this.context = context;

        Log.e("sfdg", earthquakeList.get(0).getPlace());
    }

    @Override
    public int getCount() {
        return earthquakeList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.earthquake_list, parent, false);

        TextView mag = (TextView) v.findViewById(R.id.mag);
        TextView date = (TextView) v.findViewById(R.id.date);
        TextView time = (TextView) v.findViewById(R.id.time);
        TextView offsetTextView = (TextView) v.findViewById(R.id.offset);
        TextView locationTextView = (TextView) v.findViewById(R.id.location);

        String fullLocation = earthquakeList.get(position).getPlace();
        String offset; // Distance from the known location
        String location; //Location of Earthquake

        //Checking if the full location contains offset
        if (fullLocation.contains("km ") && fullLocation.contains(" of ")) {
            int pos = fullLocation.indexOf(" of ");
            offset = fullLocation.substring(0, pos) + " of";
            location = fullLocation.substring(pos + 4);
        } else {
            offset = "Near the";
            location = fullLocation;
        }

        //Formatting the magnitude to display values only upto 1 decimal place
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(earthquakeList.get(position).getMag());

        mag.setText(String.valueOf(output));
        date.setText(earthquakeList.get(position).getDate());
        time.setText(earthquakeList.get(position).getTime());

        offsetTextView.setText(offset);
        locationTextView.setText(location);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquakeList.get(position).getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return v;
    }

}
