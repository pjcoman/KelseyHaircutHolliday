package comapps.kelseyhaircutholliday.app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements
DatePickerDialog.OnDateSetListener {

	private static final String TAG = "DATEPICKERFRAGMENT";

@Override
public Dialog onCreateDialog(Bundle savedInstanceSateate) {

    final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    
	
	@SuppressWarnings("deprecation")
	public void onDateSet(DatePicker view, int year, int month, int day) {
		
		
		
		
		
		Date date = new Date(year-2000, month, day);
		String haircutdate = DateFormat.format("MM/dd/yy", date).toString();
		
		String[] datesplit = haircutdate.split("/");
		
		if (datesplit.length == 3) {
			
		
		Log.i (datesplit[0], "this is the month");
	    Log.i (datesplit[1], "this is the day");
	    Log.i (datesplit[2], "this is the year");
		
	    datesplit[2] =  "20" + datesplit[2];
	    Log.i (datesplit[2], "this is the year");
	    
	    int monthtocalendar = Integer.parseInt(datesplit[0]);
	    int daytocalendar = Integer.parseInt(datesplit[1]); 
	    int yeartocalendar = Integer.parseInt(datesplit[2]); 
		
	    monthtocalendar = monthtocalendar - 1;  // 0-11 so 1 less
	    
	    
	    Calendar thatDay = Calendar.getInstance();
		  	thatDay.set(Calendar.DAY_OF_MONTH,daytocalendar);
		  	thatDay.set(Calendar.MONTH,monthtocalendar); // 0-11 so 1 less
		  	thatDay.set(Calendar.YEAR,yeartocalendar);
		  	

		Calendar today = Calendar.getInstance();
		
		

		long diff = today.getTimeInMillis() - thatDay.getTimeInMillis(); //result in millis
		long days = diff / (24 * 60 * 60 * 1000);
		
		String dayssince = Long.toString(days);
		
		Log.i (haircutdate, "this is the haircut date");
	    Log.i (dayssince, "this is days since last haircut");
	
	    TextView setdayssince = (TextView)getActivity().findViewById(R.id.textViewSetDate);
		setdayssince.setText(dayssince);
		
		if ( days > -1 ) { 
	    	setdayssince.setText(dayssince);
	    	setdayssince.setTextColor(getResources().getColor(R.color.LightBlue));
	    	}
	    if ( days > 60 ) { 
	    	setdayssince.setText(dayssince);
	    	setdayssince.setTextColor(getResources().getColor(R.color.Yellow));
	    	}
	    if ( days > 100 ) { 
	    	setdayssince.setText(dayssince);
	    	setdayssince.setTextColor(getResources().getColor(R.color.Red));
	    	}
		
		TextView setHaircutDate = (TextView)getActivity().findViewById(R.id.dateDisplay);
		setHaircutDate.setText(haircutdate);




		}
	
}	
	
}
