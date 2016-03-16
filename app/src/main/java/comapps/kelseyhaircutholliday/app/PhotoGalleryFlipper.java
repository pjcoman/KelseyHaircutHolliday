package comapps.kelseyhaircutholliday.app;



import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class PhotoGalleryFlipper extends Activity implements OnTouchListener {

	private float downXValue;
	
	ViewFlipper flippy;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photogalleryflippy);

        // set font of action bar title
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/chalkdust.ttf");

        int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView actionbartitle = (TextView) findViewById(titleId);
        actionbartitle.setTypeface(tf);
        actionbartitle.setTextSize(12);


		
		Animation fingerswipe = AnimationUtils.loadAnimation(this, R.anim.photogalleryanimation);
		
	//	fingerswipe.reset();
		ImageView finger = (ImageView) findViewById(R.id.finger);
		finger.setVisibility(View.VISIBLE);
	//	fingerswipe.clearAnimation();
		finger.startAnimation(fingerswipe);
		
		finger.setVisibility(View.INVISIBLE);
		
		
		    			
    	    	    			
    		
				
    	
    	    	
    	Log.i("TapSlider", "ts1");
		
		flippy = (ViewFlipper) findViewById(R.id.viewFlipper1);
        flippy.setOnTouchListener((OnTouchListener) this);
        
        
    			
        Log.i("TapSlider", "ts2");
        
	}
	
		
		public boolean onTouch(View arg0, MotionEvent arg1) {

        // Get the action that was done on this touch event
        switch (arg1.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                // store the X value when the user's finger was pressed down
                downXValue = arg1.getX();
                break;
            }

            case MotionEvent.ACTION_UP:
            {
                // Get the X value when the user released his/her finger
                float currentX = arg1.getX();            

                // going backwards: pushing stuff to the right
                if (downXValue < currentX - 150 )
                {
                    // Get a reference to the ViewFlipper
                     // ViewFlipper flippy1 = (ViewFlipper) findViewById(R.id.viewFlipper1);  //
                     // Set the animation
                	flippy.setInAnimation(this,R.anim.pushinfromleft);
                	  flippy.setOutAnimation(this,R.anim.pushouttoright);
                        // Flip!
                        flippy.showPrevious();
                }

                // going forwards: pushing stuff to the left
                if (downXValue > currentX + 150 )
                {
                    // Get a reference to the ViewFlipper
                    // ViewFlipper flippy1 = (ViewFlipper) findViewById(R.id.viewFlipper1);  //
                     // Set the animation
                	flippy.setInAnimation(this,R.anim.pushinfromright);
              	  flippy.setOutAnimation(this,R.anim.pushouttoleft);
                      // Flip!
                     flippy.showNext();
                }
                break;
            }
        }

        // if you return false, these actions will not be recorded
        return true;
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

    private void CallKelsey() {

        Intent callIntent = new Intent(Intent.ACTION_VIEW);
        callIntent.setData(Uri.parse("tel:2146624213"));
        startActivity(callIntent);

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

    @Override
		public void onBackPressed() {
			
			Intent switchactivities = new Intent();
			switchactivities.setClass(this,MainActivity.class);
			startActivity(switchactivities);
			overridePendingTransition(R.anim.fadeinanimationgallery,R.anim.fadeoutanimationgallery);
			finish();
	
			
		}
		
		public void exit(View v)					{
			Intent pushedxbutton = new Intent();
			pushedxbutton.setClass(this,MainActivity.class);
			startActivity(pushedxbutton);
			overridePendingTransition(R.anim.fadeinanimationgallery,R.anim.fadeoutanimationgallery);
			finish();
			}
	}

