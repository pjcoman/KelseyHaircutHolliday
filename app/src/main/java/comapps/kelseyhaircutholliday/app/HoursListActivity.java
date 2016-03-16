package comapps.kelseyhaircutholliday.app;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by me on 4/9/2014.
 */




public class HoursListActivity extends ListActivity {
    // Declare Variables



    private BackendlessCollection<HoursObject> hourscollection;
    private List<HoursObject> hourslist = new ArrayList<>();

    private boolean isLoadingItems = false;
    private HoursListViewAdapter adapter;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/chalkdust.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());


        setContentView(R.layout.hourslist);

        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/chalkdust.ttf");

        int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView actionbartitle = (TextView) findViewById(titleId);
        actionbartitle.setTypeface(tf);
        actionbartitle.setTextSize(12);

        adapter = new HoursListViewAdapter(HoursListActivity.this, R.layout.hoursrow, hourslist);
        setListAdapter(adapter);





        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addSortByOption("sort ASC");
        queryOptions.setPageSize(10);
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);

       /* String whereClause = "price != ''";
        // Log.i(TAG, whereClause);
        query.setWhereClause(whereClause);*/

        Backendless.Data.of( HoursObject.class ).find(query, new LoadingCallback<BackendlessCollection<HoursObject>>
                (this, "loading schedule...", true) {

            @Override
            public void handleResponse(BackendlessCollection<HoursObject> hoursBackendlessCollection) {
                hourscollection = hoursBackendlessCollection;
                addMoreItems(hoursBackendlessCollection);
                super.handleResponse(hoursBackendlessCollection);
            }

        });

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, final int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if(needToLoadItems(firstVisibleItem, visibleItemCount, totalItemCount)) {
                    isLoadingItems = true;
                    hourscollection.nextPage(new LoadingCallback<BackendlessCollection<HoursObject>>(HoursListActivity.this) {
                        @Override
                        public void handleResponse(BackendlessCollection<HoursObject> nextPage) {
                            hourscollection = nextPage;
                            addMoreItems(nextPage);
                            isLoadingItems = false;
                        }
                    });
                }

            }
        });

    }

    private boolean needToLoadItems(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        return !isLoadingItems && totalItemCount != 0 && totalItemCount - (visibleItemCount + firstVisibleItem) < visibleItemCount/2;
    }


    private void addMoreItems( BackendlessCollection<HoursObject> nextPage) {
        hourslist.addAll(nextPage.getCurrentPage());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {

        Intent switchactivities = new Intent();
        switchactivities.setClass(this, MainActivity.class);
        startActivity(switchactivities);
        overridePendingTransition(R.anim.fadeinanimationgallery,R.anim.fadeoutanimationgallery);
        finish();


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
        Intent intent3 = new Intent(Intent.ACTION_SEND);
        intent3.setType("text/plain");
        String[] address = { "Kelsey4hair@gmail.com" };

        intent3.putExtra(Intent.EXTRA_EMAIL, address);
        intent3.putExtra(Intent.EXTRA_SUBJECT,
                "appointment request");
        intent3.putExtra(Intent.EXTRA_TEXT,
                "Could I get an appointment ....");

        startActivityForResult((Intent.createChooser(intent3, "Email")), 1);

    }

    @Override
    protected void attachBaseContext (Context newBase){

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }






}