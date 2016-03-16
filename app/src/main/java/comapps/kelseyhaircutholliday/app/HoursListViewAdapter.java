package comapps.kelseyhaircutholliday.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by me on 4/9/2014.
 */
public class HoursListViewAdapter extends ArrayAdapter<HoursObject> {


    private LayoutInflater inflater;
    private int mResource;



    @SuppressLint("Instantiatable")
    public HoursListViewAdapter(Context context, int resource, List<HoursObject> hourslist) {

        super(context, resource, hourslist);
        mResource = resource;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView == null ? inflater.inflate(mResource, parent, false) : convertView;

        TextView dayofweek = (TextView) view.findViewById(R.id.dayofweekTxt);
        TextView hours = (TextView) view.findViewById(R.id.hoursTxt);

        HoursObject item = getItem(position);

        dayofweek.setText(item.getDayofweek());
        hours.setText(item.getHours());


        return view;
    }

}