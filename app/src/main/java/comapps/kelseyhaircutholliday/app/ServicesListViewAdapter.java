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
public class ServicesListViewAdapter extends ArrayAdapter<ServicesObject> {


    private LayoutInflater inflater;
    private int mResource;



    @SuppressLint("Instantiatable")
    public ServicesListViewAdapter(Context context, int resource, List<ServicesObject> serviceslist) {

        super(context, resource, serviceslist);
        mResource = resource;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView == null ? inflater.inflate(mResource, parent, false) : convertView;

        TextView service = (TextView) view.findViewById(R.id.serviceTxt);
        TextView price = (TextView) view.findViewById(R.id.priceTxt);

        ServicesObject item = getItem(position);

        service.setText(item.getService());
        price.setText(item.getPrice());


        return view;
    }

}