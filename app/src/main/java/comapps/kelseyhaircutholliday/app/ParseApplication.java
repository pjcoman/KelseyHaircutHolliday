package comapps.kelseyhaircutholliday.app;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "QLPAKh7OtIzfVFz5twJ4IxUcfa0YizAEM6Y2ZH9c", "uPQb02tNXpRzWSyzXebyiO8JG5I6VuhED4FyTmHF");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}