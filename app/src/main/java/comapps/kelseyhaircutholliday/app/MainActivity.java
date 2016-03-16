package comapps.kelseyhaircutholliday.app;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Calendar;


public class MainActivity extends Activity implements View.OnClickListener,
        View.OnLongClickListener {

    private static final String TAG = "MAINACTIVITY";

    private TextView mDateDisplay;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);

        String appVersion = "v1";
        Backendless.initApp(this, "F443F0E9-D3E9-2FA5-FFBB-AA6A88DE2D00", "9BFA71A7-5EB7-7CD3-FF0D-66505F241C00", appVersion);

        Backendless.Messaging.registerDevice("558912555197", new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Log.i(TAG, "Device has been registered");
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.i(TAG, "Server reported an error " + fault.getMessage());

            }
        });



        setContentView(R.layout.activity_main);



        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/chalkdust.ttf");

        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);

        SharedPreferences settings = getSharedPreferences("myFile", 0);
        mDateDisplay.setText(settings.getString("myFile", ""));

        mDateDisplay.setTypeface(tf);

        // set font of action bar title

        int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView actionbartitle = (TextView) findViewById(titleId);
        actionbartitle.setTypeface(tf);
        actionbartitle.setTextSize(12);

        // top line

        TextView Dayssinceyourlastvisit = (TextView) findViewById(R.id.textView1);
        Dayssinceyourlastvisit.setTypeface(tf);

        // date of last visit

        String datefromtextview = mDateDisplay.getText().toString();
        if (datefromtextview == "") {
            datefromtextview = "1/1/14";
        }


        // days since last visit

        TextView setdayssince = (TextView) findViewById(R.id.textViewSetDate);
        setdayssince.setTypeface(tf);

        // set button text font

        Button hoursbutton = (Button) findViewById(R.id.hoursbutton);
        hoursbutton.setTypeface(tf);
        Button servicesbutton = (Button) findViewById(R.id.servicesbutton);
        servicesbutton.setTypeface(tf);
        Button apptbutton = (Button) findViewById(R.id.setapptbutton);
        apptbutton.setTypeface(tf);
        Button gallerybutton = (Button) findViewById(R.id.gallerybutton);
        gallerybutton.setTypeface(tf);
        Button akbutton = (Button) findViewById(R.id.akbutton);
        akbutton.setTypeface(tf);


        setdayssince.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast instructions = Toast.makeText(MainActivity.this,
                        " Press and hold number\n "
                                + "to set date of last visit.",
                        Toast.LENGTH_SHORT);
                instructions.setGravity(Gravity.CENTER_VERTICAL, -200, -250);
                instructions.show();

            }
        });

        setdayssince.setOnLongClickListener(new TextView.OnLongClickListener() {
                                                public boolean onLongClick(View v) {
                                                    DialogFragment newFragment = new DatePickerFragment();
                                                    newFragment.show(getFragmentManager(), "date Picker");

                                                    return true;

                                                }

                                            }

        );

        // get datefromtextview into a Calendar variable

        String[] datesplit = datefromtextview.split("/");

        if (datesplit.length == 3) {

            Log.i(datesplit[0], "this is the month");
            Log.i(datesplit[1], "this is the day");
            Log.i(datesplit[2], "this is the year");

            datesplit[2] = "20" + datesplit[2];
            Log.i(datesplit[2], "this is the year");

            int monthtocalendar = Integer.parseInt(datesplit[0]);
            int daytocalendar = Integer.parseInt(datesplit[1]);
            int yeartocalendar = Integer.parseInt(datesplit[2]);

            monthtocalendar = monthtocalendar - 1; // 0-11 so 1 less

            Calendar thatDay = Calendar.getInstance();
            thatDay.set(Calendar.DAY_OF_MONTH, daytocalendar);
            thatDay.set(Calendar.MONTH, monthtocalendar); // 0-11 so 1 less
            thatDay.set(Calendar.YEAR, yeartocalendar);

            Calendar today = Calendar.getInstance();

            long diff = today.getTimeInMillis() - thatDay.getTimeInMillis(); // result
            // in
            // millis
            long days = diff / (24 * 60 * 60 * 1000);

            String dayssince = Long.toString(days);

            Log.i(datefromtextview, "this is the haircut date");
            Log.i(dayssince, "this is days since last haircut");

            if (days > -1) {
                setdayssince.setText(dayssince);
                setdayssince.setTextColor(getResources().getColor(
                        R.color.LightBlue));
            }
            if (days > 60) {
                setdayssince.setText(dayssince);
                setdayssince.setTextColor(getResources().getColor(
                        R.color.Yellow));
            }
            if (days > 100) {
                setdayssince.setText(dayssince);
                setdayssince.setTextColor(getResources().getColor(R.color.Red));
            }

        }

    }

    public interface OnDatePicked {

        void onDatePicked();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

            case R.id.action_call:
                CallKelsey();
                return true;

            case R.id.action_map:
                Map();
                return true;

            case R.id.action_gmail:
                Gmail();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Map() {

        // TODO Auto-generated method stub

        Intent intent4 = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/place/6465+E+Mockingbird+Ln/@32.837057,-96.7524881,17z"));
        intent4.setComponent(new ComponentName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"));
        startActivity(intent4);

    }

    private void Gmail() {
        // TODO Auto-generated method stub

        // The following code is the implementation of Email client
        Intent intent3 = new Intent(android.content.Intent.ACTION_SEND);
        intent3.setType("text/plain");
        String[] address = { "Kelsey4hair@gmail.com" };

        intent3.putExtra(android.content.Intent.EXTRA_EMAIL, address);
        intent3.putExtra(android.content.Intent.EXTRA_SUBJECT,
                "appointment request");
        intent3.putExtra(android.content.Intent.EXTRA_TEXT,
                "Could I get an appointment ....");

        startActivityForResult((Intent.createChooser(intent3, "Email")), 1);

    }

    private void CallKelsey() {

        Intent callIntent = new Intent(Intent.ACTION_VIEW);
        callIntent.setData(Uri.parse("tel:2146624213"));
        startActivity(callIntent);

    }

    public void hours(View v) {
        Intent hours = new Intent();
        hours.setClass(this, HoursListActivity.class);
        startActivity(hours);
        overridePendingTransition(R.anim.fadeinanimationgallery,R.anim.fadeoutanimationgallery);
        finish();

    }

    public void services(View v) {
        Intent intent1 = new Intent();
        intent1.setClass(this, ServicesListActivity.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.fadeinanimationgallery,R.anim.fadeoutanimationgallery);
        finish();

    }

    public void appointment(View v) {
        // The following code is the implementation of Email client
        Intent intent3 = new Intent(android.content.Intent.ACTION_SEND);
        intent3.setType("text/plain");
        String[] address = { "Kelsey.Studio4Salon@gmail.com" };

        intent3.putExtra(android.content.Intent.EXTRA_EMAIL, address);
        intent3.putExtra(android.content.Intent.EXTRA_SUBJECT,
                "appointment request");
        intent3.putExtra(android.content.Intent.EXTRA_TEXT,
                "Could I get an appointment ....");

        startActivityForResult((Intent.createChooser(intent3, "Email")), 1);
    }

    public void setevent(View v) {
        // The following code is the implementation of event/calendar client
        Intent intentsetevent = new Intent(Intent.ACTION_EDIT);
        intentsetevent.setType("vnd.android.cursor.item/event");
        intentsetevent.putExtra("title", "hair appointment with Kelsey");
        intentsetevent.putExtra("eventLocation",
                "6465 E Mockingbird Ln  Dallas, TX  75214");

        startActivity(intentsetevent);

    }

    public void photogallery(View v) {
        Intent intent5 = new Intent();
        intent5.setClass(this, PhotoGalleryFlipper.class);
        startActivity(intent5);
        overridePendingTransition(R.anim.fadeinanimationgallery,
                R.anim.fadeoutanimationgallery);
        finish();
    }

    public void aboutkelsey2(View v) {
        Intent intent5 = new Intent();
        intent5.setClass(this, AboutKelsey.class);
        startActivity(intent5);
        overridePendingTransition(R.anim.fadeinanimationgallery,
                R.anim.fadeoutanimationgallery);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences finalSettings = getSharedPreferences("myFile", 0);
        SharedPreferences.Editor editor = finalSettings.edit();
        editor.putString("myFile", mDateDisplay.getText().toString());
        editor.commit();

    }

    @Override
    public void onBackPressed() {

        SharedPreferences finalSettings = getSharedPreferences("myFile", 0);
        SharedPreferences.Editor editor = finalSettings.edit();
        editor.putString("myFile", mDateDisplay.getText().toString());
        editor.commit();

        finish();
        System.exit(0);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onLongClick(View v) {
        // TODO Auto-generated method stub
        return false;
    }

}
