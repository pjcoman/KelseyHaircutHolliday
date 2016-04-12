package comapps.kelseyhaircutholliday.app;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.DeviceRegistration;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.DeliveryOptions;
import com.backendless.messaging.MessageStatus;
import com.backendless.messaging.PublishOptions;

import java.util.Calendar;

import comapps.kelseyhaircutholliday.app.login.LoginActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends Activity implements View.OnClickListener,
        View.OnLongClickListener {

    private static final String TAG = "MAINACTIVITY";
    String userId;
    static String device_id = "";

    private TextView loggedInTextView;




    private TextView mDateDisplay;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Chalkduster.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        String appVersion = "v1";
        Backendless.initApp(this, "F443F0E9-D3E9-2FA5-FFBB-AA6A88DE2D00", "9BFA71A7-5EB7-7CD3-FF0D-66505F241C00", appVersion);

        Backendless.Messaging.registerDevice("558912555197", new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Log.i(TAG, "Device has been registered");

                Backendless.Messaging.getRegistrations(new AsyncCallback<DeviceRegistration>() {

                    @Override

                    public void handleResponse(final DeviceRegistration devReg) {

                        device_id = devReg.getDeviceId();

                        Log.i(TAG, "messaging deviceID is " + device_id);


                    }

                    @Override

                    public void handleFault(BackendlessFault arg0) {

                    }

                });


            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.i(TAG, "Server reported an error " + fault.getMessage());

            }
        });




   //     userId = UserIdStorageFactory.instance().getStorage().get();



        setContentView(R.layout.activity_main);

        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.i(TAG, "ANDROID_ID is " + android_id);


      //  Typeface tf = Typeface.createFromAsset(getAssets(),
       //         "fonts/Chalkduster.ttf");

        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);

        SharedPreferences settings = getSharedPreferences("myFile", 0);
        mDateDisplay.setText(settings.getString("myFile", ""));

      //  mDateDisplay.setTypeface(tf);

        // set font of action bar title

        int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView actionbartitle = (TextView) findViewById(titleId);
      //  actionbartitle.setTypeface(tf);
        actionbartitle.setTextSize(12);

        loggedInTextView = (TextView) findViewById(R.id.textViewLoggedIn);

        TextView Dayssinceyourlastvisit = (TextView) findViewById(R.id.textView1);
     //   Dayssinceyourlastvisit.setTypeface(tf);

        // date of last visit

        String datefromtextview = mDateDisplay.getText().toString();
        if (datefromtextview == "") {
            datefromtextview = "1/1/14";
        }


        // days since last visit

        TextView setdayssince = (TextView) findViewById(R.id.textViewSetDate);

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
            final Integer int_dayssince = Integer.valueOf(dayssince);



            Log.i(TAG, datefromtextview + " this is the haircut date");
            Log.i(TAG, dayssince + " this is days since last haircut");

            userId = Backendless.UserService.loggedInUser();
            Log.i(TAG, "Current user is " + userId);

            if ( userId != "") {
                loggedInTextView.setVisibility(View.VISIBLE);
            } else {
                loggedInTextView.setVisibility(View.INVISIBLE);
            }


        //    userId = settings.getString("userId", "");

        //    Log.i(TAG, "Current user is " + userId);



            Backendless.Data.of(BackendlessUser.class).findById(userId, new AsyncCallback<BackendlessUser>() {

                public void handleResponse(BackendlessUser response) {

                    if (response != null) {

                        Backendless.UserService.setCurrentUser(response);


                        Log.i(TAG, "deviceID is " + device_id);

                        response.setProperty( "days_since_last_visit", int_dayssince );
                        response.setProperty( "deviceID", device_id);


                        Backendless.UserService.update( response, new AsyncCallback<BackendlessUser>()
                        {
                            public void handleResponse( BackendlessUser user ) {
                                Log.i(TAG, user.toString() + "days since and device id has been updated");

                                if ( int_dayssince > 99  ) {

                                    Log.i(TAG, "deviceID inside is " + device_id);

                                    DeliveryOptions deliveryOptions = new DeliveryOptions();
                                    deliveryOptions.addPushSinglecast(device_id);
                                    PublishOptions publishOptions = new PublishOptions();
                                    publishOptions.putHeader("android-ticker-text", "You just got a push notification from Kelsey!");
                                    publishOptions.putHeader("android-content-title", "It has been 100 days.");
                                    publishOptions.putHeader("android-content-text", "Let's set up an appointment. :)");


                                    Backendless.Messaging.publish( "daysplus100   ", publishOptions, deliveryOptions, new AsyncCallback<MessageStatus>() {
                                        @Override
                                        public void handleResponse(MessageStatus messageStatus) {
                                        }
                                        @Override
                                        public void handleFault(BackendlessFault backendlessFault) {
                                            String error = backendlessFault.getMessage();

                                            Log.i(TAG, "messaging error is " + error);
                                        }
                                    } );

                               //     subscribeChannel100Plus(device_id);






                                }
                            }

                            public void handleFault( BackendlessFault fault )
                            {
                                Log.i(TAG, fault.toString() + "US update failed");
                            }
                        });

                    }

                }

                @Override

                public void handleFault(BackendlessFault fault) {

                }

            });



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

            case R.id.action_login:
                Login();
                return true;

            case R.id.action_logout:
                Logout();
                return true;


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

    private void Login() {

        Intent login = new Intent();
        login.setClass(this, LoginActivity.class);
        startActivity(login);
        overridePendingTransition(R.anim.fadeinanimationgallery,R.anim.fadeoutanimationgallery);
        finish();

    }

    private void Logout() {

        Log.i(TAG, "user logged out1 " + Backendless.UserService.CurrentUser());

        Backendless.UserService.logout( new AsyncCallback<Void>()
        {
            public void handleResponse( Void response )
            {

                Log.i(TAG, "user logged out2 " + Backendless.UserService.CurrentUser());
                loggedInTextView.setVisibility(View.INVISIBLE);
                // user has been logged out.
            }

            public void handleFault( BackendlessFault fault )
            {
                // something went wrong and logout failed, to get the error code call fault.getCode()
            }
        });
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

   /* public void appointment(View v) {
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
    }*/



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

    @Override
    protected void attachBaseContext (Context newBase){

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }




}
