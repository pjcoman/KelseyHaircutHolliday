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
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.TextView;

public class AboutKelsey extends Activity {
    private WebView webView;




    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.webview);

		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/chalkdust.ttf");



        // set font of action bar title

        int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView actionbartitle = (TextView) findViewById(titleId);
        actionbartitle.setTypeface(tf);
        actionbartitle.setTextSize(12);

        webView = (WebView) findViewById(R.id.webview1);
        webView.setWebViewClient(new MyWebViewClient());

        String url = "http://www.styleseat.com/kelseyholliday4hair";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    //    if (sv.getScrollY() == maxScrollY) {
	//        Toast.makeText(getApplicationContext(), "full down", Toast.LENGTH_SHORT).show(); 
	//    }



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
		switchactivities.setClass(this, MainActivity.class);
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



